# javascript에서 id값 활용
document.getElementById('id') 로 요소를 가져와서 사용할 수 있지만 html 파일에 있는 id속성에 있는 값으로 바로 가져와서 사용하는 것도 가능하다.

```html
<form action="#" name="formThree">
	<input type="text" name="jsBefore" maxlength="6"> -
	<input type="text" name="jsAfter" maxlength="1" style="width: 10px;">******
	<br>
	<label for="male">
		<input type="radio" name="gender" id="male" value="male">남
	</label>
	<label for="female">
		<input type="radio" name="gender" id="female" value="female">여
	</label>
</form>
<p id="text"></p>

<script>

let box = document.formThree.jsAfter;
// let male = document.getElementById('male');
// let female = document.getElementById('female');

box.onkeyup = function(){
	let result =this.value;
	text.innerText = result
	if(result == 1 || result == 3){
		male.checked = true;
		female.checked = false;
		alert(`${result} : 남자`);
	}else if(result == 2 || result == 4){
		male.checked = false;
		female.checked = true;
		alert(`${result} : 여자`);
	}else{
		male.checked = false;
		female.checked = false;
		alert(`${result} : 1~4 사이의 숫자를 입력하세요`);
	}
}

</script>
```