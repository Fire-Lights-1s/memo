# event listener
html 속성에 익명 함수로 event를 사용하면 여러 이벤트를 적용할 수 없다
```html
<button class="loginButton">로그인</button>

<script type="text/javascript">
let btn = document.querySelector('.loginButton');

btn.onclick = function(){
	alert("1번째 로그인");
};

btn.onclick = function(){
	alert("2번째 로그인");
};
</script>
```

addEventListener를 사용하면 여러 이벤트를 같이 적용 할 수 있다
```html
<button class="loginButton1">로그인1</button>

<script type="text/javascript">
let btn1 = document.querySelector('.loginButton1');

btn1.addEventListener('click', function(){
	alert("3번째 로그인");
});

btn1.addEventListener('click', function(){
	alert("4번째 로그인");
});

btn1.addEventListener('click', function(){
	alert("5번째 로그인");
});
</script>
```