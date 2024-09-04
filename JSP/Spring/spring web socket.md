
# spring web socket 필요한 코드

## pom.xml
```
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
```
<!-- web socket handler -->
    <beans:bean id="socketTextHandler" class="com.itwillbs.websocket.SocketTextHandler"/>
	<websocket:handlers>
        <websocket:mapping path="/chatting" handler="socketTextHandler"/>
        <websocket:sockjs websocket-enabled="true"/>
    </websocket:handlers>
```