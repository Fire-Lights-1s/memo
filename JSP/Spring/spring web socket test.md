# spring web socket 필요한 코드

## pom.xml
```xml
	<!-- websocket -->
	<dependency>
	   <groupId>org.springframework</groupId>
	   <artifactId>spring-websocket</artifactId>
	   <version>${org.springframework-version}</version>
	</dependency>
			
	<!--  JSON라이브러리 -->
	<dependency>
		<groupId>com.fasterxml.jackson.core</groupId>
		<artifactId>jackson-databind</artifactId>
		<version>2.11.3</version>
	</dependency>
	
	<!-- GSON 라이브러리 -->
	<!-- https://mvnrepository.com/artifact/com.google.code.gson/gson -->
	<dependency>
		<groupId>com.google.code.gson</groupId>
		<artifactId>gson</artifactId>
		<version>2.9.0</version>
	</dependency>
```

## servlet-context.xml에서 config 설정
```xml
<!-- web socket handler -->
    <beans:bean id="socketTextHandler" class="com.itwillbs.websocket.SocketTextHandler"/>
	<websocket:handlers>
        <websocket:mapping path="/chatting" handler="socketTextHandler"/>
        <websocket:sockjs websocket-enabled="true"/>
    </websocket:handlers>
```

## java 파일에서 config 설정

servlet-context.xml
```xml
<!-- web socket configer 클래스 파일이 있는 패키지 등록-->
<context:component-scan base-package="com.itwillbs.websocket"/>
```

com.itwillbs.websocket.WebSocketConfig.java
```java
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new SocketTextHandler(), "/chatting")
        		.setAllowedOrigins("*")
                .withSockJS();
    }
}
```

com.itwillbs.websocket.SocketTextHandler.java
```java
public class SocketTextHandler extends TextWebSocketHandler {

    private final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();
//    private Set<WebSocketSession> sessions= Collections.synchronizedSet(new HashSet<>());
    // websocket 연결 성공 시
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    // websocket 메세지 수신 및 송신
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        
        // Payload : 통신 시 탑재된 데이터(메세지)
 		System.out.println("전달받은내용:"+message.getPayload());
 		
 		// /chatting으로 연결된 객체를 만든 클라이언트들(sessions)
 		// 전달 받은 내용을 보냄
 		for(WebSocketSession s : sessions) {
 			s.sendMessage(message);
 		}
    }

    // websocket 연결 종료 시
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }
}
```

## javascript 
```javascript
// 웹소켓 테스트
// 아무 jsp 파일에 링크 시키고 그 파일에서 브라우저 콘솔에서 sendMessage로 테스트
// 1. SockJS라이브러리 추가 
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
//2. SockJS를 이용해서 클라리언트용 웹소켓 객체 생성
// 경로 문제가 생겨서 프로젝트 명을 앞에 추가함
let testSock = new SockJS("/secondhand4989/chatting");

function sendMessage(name,str){
    //매개변수 JS객체에 저장 
    let obj = {}; // 비어있는 객체 

    obj.name = name;  // 객체에 일치하는 key가 없다면 자동으로 추가 
    obj.str = str;

    console.log(obj);

    // 웹 소켓 연결된 곳으로 메세지를 보냄
    testSock.send(JSON.stringify(obj));
                // JS객체 -> JSON으로 보냄! 

            
}
// 웹소켓 객체(testSock)가 서버로 부터 전달 받은 메세지가 있을경우
testSock.onmessage = e => {
    // e: 이벤트 객체
    // onmessage: 이벤트가 오는걸 감지
    // e.data : 전달 받은 메세지(JSON)
    let obj = JSON.parse(e.data); // JSON -> JS객체 
    console.log(`보낸사람 : ${obj.name} / ${obj.str}`);

}
```