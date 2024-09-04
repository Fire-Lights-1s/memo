
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

## servlet-context.xml
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