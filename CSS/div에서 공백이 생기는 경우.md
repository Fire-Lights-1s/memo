## img 태크와 div 태그 사이의 공백

```html
<article>
	</img>
    <div></div>
</article>
```
- 위 코드에서는 img태그와 div태그 사이에 공백이 생김
- inline속성인 img태그에서 개행 문자를 인식해 저렇게 폰트 크기만큼 여백이 생긴 것
### 1. display: block

inline속성을 가진 img태그에서 display: block를 설정해주면 자식태그 모두 block속성이 되므로 여백이 사라진다.

### 2. display: flex

마지막으로 부모태그에 display: flex를 설정해주면 된다. 물론 위의 사진처럼 세로로 배열해야하는 경우에는 flex-direction: column도 같이 해줘야한다.