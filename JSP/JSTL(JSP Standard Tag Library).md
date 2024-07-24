## EL(Expression Language) 
- JSP 2.0에서 새롭게 추가된 스크립트 언어
- JSP 출력 <%=출력내용 %> => ${출력내용}

## JSTL(JSP Standard Tag Library)
- 다운로드 주소 : https://tomcat.apache.org/download-taglibs.cgi
- JSP에서 사용하는 표준 태그 함수

### JSTL 기본 액션 JSTL core
```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
```
- 변수 설정 - <c:set>
```jsp
<c:set var="변수명" value="${저장할값}"/>
<c:set var="dto" value="${requestScope.memberDTO}"/>
```
- 조건 - <c:if>, <c:choose>, <c:when>, <c:otherwise>
```jsp
<c:set var="dto" value="${requestScope.memberDTO}"/>
<table>
<c:choose>
	<c:when test="${ ! empty dto }">
		<tr>
			<td>아이디 : ${dto.id}</td>
		</tr>
		<tr>
			<td>비밀번호 : ${memberDTO.pass}</td>
		</tr>
		<tr>
			<td>이름 : ${memberDTO.name}</td>
		</tr>
		<tr>
			<td>가입 날짜 : ${memberDTO.date}</td>
		</tr>
	</c:when>
	<c:otherwise>
		<tr>
			<td rowspan="2">로그인 정보 없음</td>
		</tr>
	</c:otherwise>
</c:choose>
</table>
```
- 반복	- <c:forEach>, <c:forTokens>
```jsp
<c:forEach items="${requestScope.memberList }" var="member" varStatus="st">
	<tr>
		<td>${member.getId()}</td>
		<td>${member.getPass()}</td>
		<td>${member.getName()}</td>
		<td>${member.getDate()}</td>
	</tr>
</c:forEach>
```
- 이동	- <c:redirect>
#### JSTL 형식화(숫자,날짜모양) JSTL fmt
```jsp
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
```
- 날짜 - <fmt:formatDate>
- 숫자 - <fmt:formatNumber>


