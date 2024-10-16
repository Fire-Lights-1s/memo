# 스트림
자료가 모여있는 배열이나 컬렉션등 에대해 요소를 특정 기준에 따라 정렬하거나(sorting), 요소 중 특정 값은 제외하고 출력하는(filter) 등의 여러 자료의 처리에 대한 기능을 구현해 놓은 클래스가 스트림(stream)이다.
## 스트림의 특징
- 자료의 대상과 관계없이 동일한 연산을 수행한다
- 한 번 생성하고 사용한 스트림은 재사용할 수 없다
- 스트림의 연산은 기존 자료를 변경하지 않는다
- 스트림의 연산은 중간 연산과 최종 연산이 있다

```java
//스트림(Stream)
//람다식(Lambda Expression)을 이용한 기술중에 하나로 데이터 소스(컬렉션, 배열, 난수, 파일등)를
//조작 및 가공, 변환을 시켜서 원하는 값으로 반환해주는 인터페이스이다.

public class StreamTest {
	public static void main(String[] args) {
	
	//정수 배열에 스트림을 생성하고 사용하기
	int [] arr = { 1, 2, 3, 4, 5 };
	
	for(int i = 0 ; i < 5 ; i++) {
		System.out.println("arr [" + i + "] = " + arr[i]);
	}
	
	System.out.println("---------------");
	
	//기본 타입형 스트림 (정수형 스트림)
	//forEach() : 메서드 안으로 for문을 넣은 것과 같으며, 수행할 작업을 매개변수로 받아와서 반복시킴
	IntStream intstream1 = Arrays.stream(arr); //배열 스트림 생성
	intstream1.forEach( s -> System.out.println(s)); //최종 연산 (배열 스트림 내의 요소값 가져오기(출력))
	
	System.out.println("---------------");
	
	// 스트림 생성 . 중간 연산 없음. 최총 연산
	int sumVal = Arrays.stream(arr).sum();
	System.out.println("배열의 합계 sumVal = " + sumVal);
	
	//count() 최종연산 메서드는 long형으로 결과값을 돌려줌 -> int형으로 형변환
	int countVal = (int)Arrays.stream(arr).count();
	System.out.println("배열의 개수 countVal = " + countVal);
	
	System.out.println("---------------");
	
	//정수형 데이터를 넣어주고 스트림을 생성 : of() 메서드 ->데이터값으로 정수 스트림을 바로 생성할 때 사용
	IntStream intstream2 = IntStream.of(arr);
	intstream2.forEach(a -> System.out.println(a));
	
	System.out.println("---------------");
	
	IntStream intstream3 = IntStream.of(1,2,3,4,5,6,7,8,9,10);
	intstream3.forEach(a -> System.out.println(a));
	
	System.out.println("---------------");
	
	IntStream intstream4 = IntStream.range(1, 10); //1 ~ 9까지 정수 스트림 생성
	intstream4.forEach(a -> System.out.println(a));
	
	System.out.println("---------------");
	
	IntStream intstream5 = IntStream.rangeClosed(1, 10); // 1~ 10까지 정수 스트림 생성
	intstream5.forEach(a -> System.out.println(a));
	
	System.out.println("---------------");
	
	//문자열 스트림 생성 : forEach 메서드는 한번만 사용 !!! (한번 꺼내온 데이터는 스트림에 남아 있지 않음!!!)
	String str = "자바 스트림 생성";
	IntStream isr = str.chars();
	isr.forEach(s -> System.out.println(s));
	//isr.forEach(s -> System.out.println((char)s));

	List<String> list = Arrays.asList("동해물과", "백두산이", "마르고", "닳도록");
	list.stream()
		.mapToInt(s -> s.length())
		.forEach(len -> System.out.println("len = " + len));
	//람다식 메서드 참조
	list.stream()
		.mapToInt(String::length)
		.forEach(System.out::println);
	}
}
```

- 중복 제거, 필터
```java
List<String> list = Arrays.asList("홍길동", "김유신", "홍길동", "이순신", "홍길동", "유관순");
list.stream()
	.distinct().forEach(s -> System.out.println(s));
//list.stream()
//	.distinct().forEach(System.out::println);

System.out.println("--------------------------------");
list.stream()
	.filter(h -> h.startsWith("홍")).forEach(System.out::println);

System.out.println("--------------------------------");
list.stream()
	.distinct()
	.filter(h -> h.startsWith("홍")).forEach(System.out::println);
```

- 정렬
```java
TravelCustomer customerLee = new TravelCustomer("이순신", 40, 100);
TravelCustomer customerKim = new TravelCustomer("김유신", 20, 100);
TravelCustomer customerHong = new TravelCustomer("홍길동", 16, 50);

List<TravelCustomer> customerList = new ArrayList<>();

customerList.add(customerLee);
customerList.add(customerKim);
customerList.add(customerHong);

System.out.println("오름차순 정렬");
customerList.stream()
	.map(c->c.getName())
	.sorted()
	.forEach(s -> System.out.println(s));

System.out.println("\n내림차순 정렬");
customerList.stream()
	.map(c->c.getName())
	.sorted(Comparator.reverseOrder())
	.forEach(s -> System.out.println(s));
```