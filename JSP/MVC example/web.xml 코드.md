## web.xml 코드
- 가상주소 http://localhost:8080/MVCProject/insert.me 서버요청
	1) 가상주소 \*.me 끝나는 주소가 요청 들어오면 => 서블릿 이름 MemberController 찾음
	2) MemberController 서블릿 이름을 찾아서 실제 서블릿 자바 파일com.itwillbs.controller.MemberController에 연결
```xml
<servlet-mapping>
	<servlet-name>MemberController</servlet-name>
	<url-pattern>*.me</url-pattern>
</servlet-mapping>
<servlet>
	<servlet-name>MemberController</servlet-name>
	<servlet-class>com.itwillbs.controller.MemberController</servlet-class>
</servlet>
```