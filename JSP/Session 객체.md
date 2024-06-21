### Session 내장 객체
- session 내장 객체 : 연결 정보를 저장하는 내장 객체(기억 장소) => 세션 ID부여
- session 내장 객체(기억 장소 해제)
	1. 사용자 브라우저의 세션 ID삭제
	2. 30분 동안 작업(요청)이 없을 때 session 내장 객체 삭제
	3. session 내장 객체 삭제하는 명령 session.invalidate() => 로그아웃
- 연결 정보를 저장하는 session 내장 객체(기억 장소)에 (이름, 값) 형태로 값을 저장
```jsp
세션 ID : <%=session.getId()%>
세션 생성 시간 : <%= session.getCreationTime()%>
세션 최근접근 시간 : <%= session.getLastAccessedTime()%>
세션 유지 시간 : <%=session.getMaxInactiveInterval()%> 초
세션 유지 시간 변경 : <%session.setMaxInactiveInterval(1800);%>
session에 정보 저장(이름, 값) : <%session.setAttribute("sName", "session values");%>
session에 저장된 값 출력 : <%=session.getAttribute("sName") %>
세션 연결정보 삭제 : <%session.invalidate();%>
```