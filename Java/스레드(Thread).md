# 스레드(Thread) 란?
하나의 프로세스 내에서 실행되는 흐름의 단위를 말한다. 한 프로그램은 하나의 스레드를 가지고 있고, 둘 이상의 스레드를 동시에 실행하는 경우 이 방식을 멀티스레드라고 한다.

## 프로세스(Process)
- 프로세스란 실행중인 프로그램을 말한다
- 프로그램은 기억장치(SSD)에 존재하며 실행되기를 기다리는 명령어와 정적인 데이터 묶음
- 운영체제로부터 자원을 할당받는 작업 단위이다.

```java
class One extends Thread{
	@Override
	public void run() {
		for(int i=0; i < 300; i++) {
			System.out.print("*");
		}
		System.out.println("One 스레드 종료");
	}
}

class Two implements Runnable{
	@Override
	public void run() {
		for(int i=0; i < 300; i++) {
			System.out.print("$");
		}
		System.out.println("Two 스레드 종료");
	}
}

public class Thread2 {
	public static void main(String[] args) {
	Runnable runnable1 = new MyThread("+");
	Runnable runnable2 = new MyThread("-");
	
	for(int i=0; i < 300; i++) {
	Thread thread1 = new Thread(runnable1);
	thread1.start();
	Thread thread2 = new Thread(runnable2);
	thread2.start();
	}
	
	System.out.println("종료");
	
	One threadOne = new One();
	threadOne.start();
	
	Thread threadTwo = new Thread(new Two());
	threadTwo.start();
	
	}
	  
	
	static class MyThread implements Runnable {
		private String string;
		public MyThread(String s) {
			this.string = s;
		}
		
		@Override
		public void run() {
			System.out.print(string);
		}
	}
}
```


## 스레드 동기화
**스레드 동기화**는 멀티스레드 환경에서 여러 스레드가 **하나의 공유자원에 동시에 접근하지 못하도록 막는것**

공유데이터가 사용되어 동기화가 필요한 부분을 임계영역(critical section)이라고 부르며, 자바에서는 이 임계영역에 **synchronized 키워드**를 사용하여 여러 스레드가 동시에 접근하는 것을 금지함으로써 동기화를 할 수 있다

- 메소드에 synchronized 설정하기
```java
synchronized void increase() {
	count++;
	System.out.println(count);
}
```
- 코드블럭에 synchronized 설정하기
```java
void increase() {
	synchronized(this) {
		count++;
	}
	System.out.println(count);
}
```

- 예시 코드
```java
class Ticketing{
	
	int ticketNumber = 1;
	
	public synchronized void ticketing() {
		if(ticketNumber > 0) {
			System.out.println(Thread.currentThread().getName() + "가 티케팅 성공");
			ticketNumber--;
		}else {
			System.out.println(Thread.currentThread().getName() + "가 티케팅 실패");
		}
		System.out.println(Thread.currentThread().getName() + "가 티케팅 시도 후 남은 티켓 수 : " + ticketNumber);
	}
}

class ThreadB implements Runnable{
	Ticketing ticketing = new Ticketing();
	
	@Override
	public void run() {
		ticketing.ticketing();
	}
}

public class Thread8 {
	public static void main(String[] args) {
		ThreadB threadB = new ThreadB();
		
		for(int i=0; i < 10; i++) {
			Thread th1 = new Thread(threadB);
			th1.start();
		}
	}
}
```