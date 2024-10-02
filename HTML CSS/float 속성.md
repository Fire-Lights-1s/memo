## float 속성
- 웹페이지에서 **이미지**를 어떻게 띄워서 텍스트와 함께 배치할 것인가에 대한 속성
- 원래 이미지와 텍스트 **배치** 용도로 등장했지만, 요즘에는 **레이아웃용**으로 많이 사용
- float 속성을 가진 태그들 사이에 공간이 있을 때 원하지 않은 태그가 그 사이 공간에 들어감
- float속성을 clear로 해제해야 자신의 크기와 위치를 유지 할 수 있음
```css
div{
	margin: 20px;
	padding: 10px;
	background-color: #fa0;
}
#box1 {
	background-color: red;
	float: left;
}
#box2 {
	background-color: green;
	float: right;
}
```
```html
<div id="box1">박스1</div>
<div id="box2">박스2</div>
<div id="box3">박스3</div>
<div id="box4">박스4</div>
```
>※web 화면
>
>![float.png](images/float.png)
