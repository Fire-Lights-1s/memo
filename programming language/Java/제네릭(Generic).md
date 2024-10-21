# 제네릭(Generic)
자바에서 제네릭(Generics)은 **클래스 내부에서 사용할 데이터 타입을 외부에서 지정하는 기법**을 의미한다. 객체별로 다른 타입의 자료가 저장될 수 있도록 한다.

## 제네릭 사용이유
### 잘못된 타입을 사용하는 것을 방지한다.
```java
ArrayList<Integer> list = new ArrayList<>();
list.add("Test"); /* 에러 */
```
위와 같은 경우 Integer타입으로 선언한 ArrayList에 Test라는 문자열 요소를 추가하려는 코드이며 에러가 발생한다.
이 처럼 미리 타입을 검사할 수 있으며 반환값에 대한 타입 변환 및 타입 검사에 들어가는 노력을 줄일 수 있다.

### 외부에서 타입이 지정되기 때문에 관리가 편하다.
```java
public interface List<E> extends Collection<E> { ... }
```
List인터페이스를 구현하는 ArrayList 등의 클래스를 생성할 때 타입을 지정해주므로  
List, ArrayList 등을 구현, 생성할 때 타입을 체크하거나 변환해줄 필요가 없어지게 된다. 
### 클래스의 재사용성이 높아진다.
```java
HashMap<String, List<Integer>> map1 = new HashMap<>();
HashMap<Integer, String> map2 = new HashMap<>();
HashMap<String, String> map3 = new HashMap<>();
```
제네릭으로 선언된 하나의 클래스를 구현기능의 알맞게 타입을 생성할 때 정해주므로 코드의 재사용성이 높아진다.
## 타입 파라미터 기호 네이밍
명명하고 싶은대로 아무 단어나 넣어도 문제는 없지만, 대중적으로 통하는 통상적인 네이밍이 있으면 개발이 용이해 지기 때문에 아래 표화 같은 암묵적인 규칙(convention)이 존재한다.

| 타입        | 설명                      |
| --------- | ----------------------- |
| \<T>      | 타입(Type)                |
| \<E>      | 요소(Element), 예를 들어 List |
| \<K>      | 키(Key), 예를 들어 Map<k, v> |
| \<V>      | 리턴 값 또는 매핑된 값(Variable) |
| \<N>      | 숫자(Number)              |
| <S, U, V> | 2번째, 3번째, 4번째에 선언된 타입   |

>출처
>[https://inpa.tistory.com/entry/JAVA-☕-제네릭Generics-개념-문법-정복하기#제네릭_generics_이란](https://inpa.tistory.com/entry/JAVA-%E2%98%95-%EC%A0%9C%EB%84%A4%EB%A6%ADGenerics-%EA%B0%9C%EB%85%90-%EB%AC%B8%EB%B2%95-%EC%A0%95%EB%B3%B5%ED%95%98%EA%B8%B0#%EC%A0%9C%EB%84%A4%EB%A6%AD_generics_%EC%9D%B4%EB%9E%80) [Inpa Dev 👨‍💻:티스토리]
>https://velog.io/@cv_/%EC%9E%90%EB%B0%94%EC%9D%98-%EC%A0%9C%EB%84%A4%EB%A6%ADGeneric-%EC%95%8C%EC%95%84%EB%B3%B4%EA%B8%B0