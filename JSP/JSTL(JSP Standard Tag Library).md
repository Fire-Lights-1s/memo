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
- 조건 - <c:if>, <c:choose>, <c:when>, <c:otherwise>
- 반복	- <c:forEach>, <c:forTokens>
- 이동	- <c:redirect>
#### JSTL 형식화(숫자,날짜모양) JSTL fmt
```jsp
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
```
- 날짜 - <fmt:formatDate>
- 숫자 - <fmt:formatNumber>


