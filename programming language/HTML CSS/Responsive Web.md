# Responsive Web
웹 요소 디바이스의 화면 크기에 맞게 재배치하고 각 요소의 표시 방법만 바꾸어서 구현하는 것

1. viewport : 웹 페이지의 가시 영역을 의미하고 사용자가 웹 페이지를 볼 수 있게하는 실제 화면 영역을 말한다.
2. 뷰포트 메타 태그 작성 : 각각의 디바이스들을 크기에 맞게 인식하도록 할 때 사용하는 태그이다
3. 형식 
	- name : "viewport" -> 디바이스 화면이 표시되는 실제 화면 영역 
	- content : "페이지 크기와 배율을 제어하는 방법에  대한 브라우저의 지침을 지정"
```html
<meta name="viewport" content="width=device-width, initital-scale=1.0">
```
