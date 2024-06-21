### pageContext 내장 객체
- 현 페이지 정보를 저장하는 내장 객체(기억 장소)
- 페이지가 변경되면 기억 장소 삭제, 새로운 페이지가 만들어지면 기억 장소가 만들어짐
```jsp
페이지 정보
<%=pageContext.getRequest()%>
<%=pageContext.getResponse()%>
<%=pageContext.getPage()%>
<%=pageContext.getSession()%>
```