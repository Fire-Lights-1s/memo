# List 인터페이스
객체를 순서에 따라 저장하고 유지하는 데 필요한 메서드가 선언되어있다
## ArrayList
객체 배열을 구현한 클래스이며 컬렉션 인터페이스와 그 하위 List 인터페이스를 구현했다.
## LinkedList
linked list라는 자료 구조를 구현한 클래스이다.
linked list는 각 요소가 다음 요소를 가리키는 주소 값을 가진다.

## ArrayList & LinkedList
차이점
- ArrayList 는 요소에 접근할 때의 속도가 빠르고 새로운 요소를 추가할 때 속도가 느리다.
- LinkedList 는 요소에 접근할 때의 속도가 느리고 새로운 요소를 추가 할 때 속도가 빠르다.
```java
ArrayList<Integer> alist = new ArrayList<>();
LinkedList<Integer> myList1 = new LinkedList<>();

for(int i=0; i < 100000; i++) {
	alist.add(i);
	myList1.add(i);
}

System.out.println("ArrayList Access");

long start = System.currentTimeMillis();

for(int i=0; i < alist.size(); i++) {
	alist.get(i);
}

long end = System.currentTimeMillis();

System.out.println("시간 : "+ (end-start));
System.out.println("----------------------------------");

System.out.println("LinkedList Access");

start = System.currentTimeMillis();

for(int i=0; i < myList1.size(); i++) {
	myList1.get(i);
}

end = System.currentTimeMillis();

System.out.println("시간 : "+ (end-start));
System.out.println("----------------------------------");

System.out.println("ArrayList Access");

start = System.currentTimeMillis();

for(int i=0; i < 100000; i++) {
	alist.add(500, i);
}

end = System.currentTimeMillis();

System.out.println("시간 : "+ (end-start));
System.out.println("----------------------------------");

System.out.println("LinkedList Access");

start = System.currentTimeMillis();

for(int i=0; i < 100000; i++) {
	myList1.add(500, i);
}

end = System.currentTimeMillis();

System.out.println("시간 : "+ (end-start));
```