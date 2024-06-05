### application 내장 객체
- 서버 정보를 저장
- 서버 start하면 기억장소가 만들어지고
- 서버 stop하면 기억장소 삭제 되어짐
- 웹 어플리케이션당 오직 하나의 객체만 생성됨
```
서버정보 : <%=application.getServerInfo()%>
서버 물리적인 경로 : <%=application.getRealPath("/")%>
application 내장객체에 값을 저장 <%application.setAttribute("appName", "appValue"); %>
application 내장객체에 저장된 값 가져와서 출력 : <%=application.getAttribute("appName")%>
```