### out 내장객체
- 브라우저에 출력할 내용을 저장
```
out.print("web 출력<br>");
out.print("출력 기억장소 크기 : "+ out.getBufferSize()+"<br>");
out.print("남아있는 출력 기억장소 크기 : "+ out.getRemaining()+"<br>");

//출력 종료
out.close();
```