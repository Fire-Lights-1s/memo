### Request 내장 객체
- http가 요청하러 서버에 갈때 (태그정보,서버정보,사용자정보,세션정보,쿠키정보,http헤더정보)
- 정보를 들고 서버에 전달 => 서버 request 내장객체(기억장소)에 저장
- request에 다양한 정보가 있음
```jsp
서버 이름 : <%=request.getServerName() %><br>
서버 포트 : <%=request.getServerPort() %><br>
요청 URL(요청 주소) :  <%=request.getRequestURL() %><br>
요청 URL(요청 주소에서 서버 뺀 나머지 주소) : <%=request.getRequestURI() %><br>
요청 프로젝트 : <%=request.getContextPath() %><br>
요청 프로젝트 뺀 나머지 주소 : <%=request.getServletPath() %><br>
프로토콜(통신규약) 정보 : <%=request.getProtocol() %> <br>
데이터 전송 방식 : <%=request.getMethod() %><br>

사용자 IP주소 : <%=request.getRemoteAddr() %><br>

http헤더정보("accept-language") : <%=request.getHeader("accept-language") %><br>
http헤더정보("user-agent") : <%=request.getHeader("user-agent") %><br>
http헤더정보("host") : <%=request.getHeader("host") %><br>

```
### Response 내장 객체
1. http가 요청하러 오면 서버=> request(요청 정보 저장), response(응답 정보 저장) 기억 장소 만들어지면서 => request에 요청 값 저장
2. 처리 담당자 처리하면서 => 결과 response에 응답 정보 값 저장
3. response를 웹 서버 전달 
4. response를 http에 전달
5. http가 사용자 전달
6. request,response, 처리 담당자 기억 장소 해제, 연결 종료
```java
// 사용자의 http header 정보 변경 명령

response.setHeader("accept-language", "ko-KR");

//사용자의 내용타입 변경 명령

response.setContentType("text/html; charset=UTF-8");

//사용자의 컴퓨터에 쿠키값을 저장하는 명령

Cookie cookie = new Cookie("cookieID", "values");

response.addCookie(cookie);

//사용자에게 다른 페이지로 이동하게 만드는 명령(하이퍼링크)

response.sendRedirect("http://192.168.1.200:8080/JspProject/jsp1/request.jsp");
```