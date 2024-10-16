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