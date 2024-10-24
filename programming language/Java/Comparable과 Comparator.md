# Comparable과 Comparator
- 정렬을 위한 인터페이스이다
- 주로 사용하는 건 Comparable 인터페이스이다
## Comparable
 - 자기 자신과 매개변수를 비교한다.
- compareTo(T o) 추상 메소드
#### 자신과 매개변수를 비교
- 자신이 큰 경우 : 양수
- 같은 경우 : 0
- 자신이 작은 경우 : 음수
## Comparator
- 전달되는 2개의 객체의 매개변수를 비교한다.
- compare(T o1, T o2) 추상 메서드
#### 전달 받은 2개의 객체를 비교
- 첫 번째 객체가 큰 경우 : 양수
- 같은 경우 : 0
- 첫 번째 객체가 더 적은 경우 : 음수

String 클래스는 compareTo() 가 구현되어 있다.
Sring 클래스를 내림차순으로 정렬하기 위해 Comapre() 메서드를 구현했다.
```java
import java.util.Comparator;
import java.util.TreeSet;

class Descending implements Comparator<String>{
	@Override
	public int compare(String o1, String o2) {
		return o1.compareTo(o2) *(-1);
	}
}

public class TreeSetTest {
	public static void main(String[] args) {
		TreeSet<String> treeSet = new TreeSet<String>();
		
		treeSet.add("Cola");
		treeSet.add("Cider");
		treeSet.add("Bear");
		treeSet.add("Juice");
		treeSet.add("Juice");
		
		System.out.println(treeSet);
		System.out.println("------------------------------");
		
		TreeSet<String> treeSet1 = new TreeSet<String>(new Descending());
		
		treeSet1.add("Cola");
		treeSet1.add("Cider");
		treeSet1.add("Bear");
		treeSet1.add("Juice");
		treeSet1.add("Juice");
		
		System.out.println(treeSet1);
	}
}
```