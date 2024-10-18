## MultipartRequest 활용

- MultipartRequest 를 이용하기 위해선 먼저 라이브러리 파일 필요
- http://www.servlets.com/ 
- cos.jar 파일을 다운받아서 lib폴더에 넣기
- MultipartRequest를 사용하게 되면 multi.getParameter();를 통해서 값을 받아와야만 함

- html 코드
```html
<!-- 멀티파일이 전송된다는 표시 enctype="multipart/form-data" -->
<form action="writePro.jsp" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td>첨부파일</td>
			<td><input type="file" name="file" ></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="파일 전송"></td>
		</tr>
	</table>
</form>

```
- jsp 코드
```java
String realFolder= request.getServletContext().getRealPath("/storage");
//storage 폴더의 실제 주소를 realFolder 라는 변수 안에 저장

MultipartRequest multi = new MultipartRequest(request,realFolder,5*1024*1024,"UTF-8", new DefaultFileRenamePolicy());
//MultipartRequest(request,저장 주소,파일 크기제한,인코딩, 중복이름 정책)
//fileForm이라는 form에서 전달한 업로드 파일을 request 형식으로 받은 후, 실제 주소인 realFolder 안에 넣어주는 작업
```
>  [javax패키지 명이 jakarta로 변경됨](<Java EE에서 Jakarta EE로의 전환.md>) 
> 2024-06-27일 기준 cos.jar 파일은 javax에 의존하고 있음
> tomcat 10버전의 servlet API 는 jakarta 를 사용해서 cos.jar 을 사용 불가