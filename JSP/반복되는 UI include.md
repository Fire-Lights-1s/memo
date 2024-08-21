# 반복되는 UI를 파일로 분리

사이트 화면에서 반복되는 메뉴나 footer 부분을 jsp 파일로 분리 해서 반복되는 코드를 줄인다.

반복되는 코드를 가져올 때 사용할 코드
```jsp
<jsp:include page="반복되는 UI 파일 주소"/>
```

사이트의 헤더 부분이 반복된다면 아래와 같이 다른 파일로 분리하고
```jsp
<!-- 파일 이름 : header.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
	<header id="header">
	...
	</header>
```
적용할 jsp 파일에서 적용할 위치에 아래의 코드를 작성한다
```jsp
<jsp:include page="경로/header.jsp"/>
```