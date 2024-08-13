# 미디어 쿼리(media queries)
사용자가 어떤 디바이스를 사용하는 지에 따라서 사이트의 레이아웃의 형태가 바뀌도록 CSS를 작성하는 방법이다.
특정 조건이 true인 경우 코드 블록{  }내부의 CSS코드를 실행해주는 구문으로 작성한다.
## 미디어쿼리 선언

- css에 @media를 선언한다. >> `@media screen (max-width:992px){ 스타일 지정 }`
- 미디어타입 설정 : screen, tv, print, speech, all(기본값)
- 조건 설정
    - max-width : 데스크탑 기준으로 작업한 창을 다른 기기의 스크린으로 변경할 때 / 지정한 최대너비 이하는 볼 수 없다.
    - min-width : 모바일 기준으로 작업한 창을 다른 기기의 스크린으로 변경할 때 / 지정한 최소너비 이상은 볼 수 없다.
```css
@media screen and (max-width:779px){

	body{
		background-color: orange;
	}
	
	#container{
		width:700px;
		margin:50px auto;
	}


}
```