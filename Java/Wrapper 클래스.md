# Wrapper Class
기본 자료타입(primitive type)을 객체로 다루기 위해서 사용하는 클래스들을 래퍼 클래스(wrapper class)라고 한다. 

# Wrapper Class 종류

| 기본타입(primitive type) | 래퍼클래스(wrapper class) |
| -------------------- | -------------------- |
| byte                 | Byte                 |
| short                | Short                |
| int                  | Integer              |
| long                 | Long                 |
| float                | Float                |
| double               | Double               |
| char                 | Character            |
| boolean              | Boolean              |
# wrapper 클래스 사용하는 이유

> wrapper 클래스를 사용하는 이유는 참조형 자료형을 사용하는 이유와 같다.
> 기본 자료형의 값을 단순히 값으로만 사용하지 않고 그 값에 대한 메서드를 사용 혹은 null값을 이용하기 위해 사용된다.
> 하지만 우리가 wrapper 클래스를 흔히 사용하는 이유는 밑의 두 가지 이유일 것이다.
> **첫째 제네릭  
> 둘째 기본 자료형의 값을 문자열로 변환 혹은 반대 경우**