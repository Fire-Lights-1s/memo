## box model
- box model의 전체 크기 : content 크기 + `padding` 값 + `border` 값 + `margin` 값

>box model에 따라 레이아웃 배치를 하다보면, 사이즈를 조절할 때 불편한 경우가 발생

### box-sizing 속성
- 요소의 너비와 높이를 계산하는 방법을 지정하는 속성

> content-box
> 설정한 `width`와 `height` 값이 곧 **요소 내부의 콘텐츠 크기**
> 내가 설정한 크기 = 내부 content 크기 

> border-box
> 설정한 `width`와 `height` 값이 **안쪽 여백과 테두리까지 포함**
> 내가 설정한 크기 = content 크기 + padding 값 + border 값 