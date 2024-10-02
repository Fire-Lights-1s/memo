## FileInputStream

자바에서는 다양한 타입의 확장자를 가진 파일을 읽기 위해 FileInputStream 클래스를 이용합니다.

이 클래스를 이용하면 파일에서 바이트 단위로 입력이 가능해 다른 입력 클래스들과 연결해서 데이터를 읽어올 수 있습니다.

```java
import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStream2 {
	public static void main(String[] args) throws IOException {
		// 파일 주소의 스트림 생성
		FileInputStream fis = new FileInputStream("C:\\javaBasic\\workspace\\javaStudy\\src\\chapter15\\SystemInTest.java");
		int data; //스트림에서 가져온 데이터를 저장해 놓는 변수

		//FileInputStream은 파일내의 데이터를 모두 다 읽어와서 더 이상 읽어올 데이터가 없는 경우에는 -1을 리턴한다.
		
		while( (data = fis.read()) != -1) {
			System.out.write((char)data); //바이너리 데이터
		}
		// 아래의 방법은 한번에 10 byte 씩 읽어옴
		int i;
		byte[] bs = new byte[10];
		
		while( (i = fis.read(bs)) != -1) {
			System.out.println("Number of bytes read : " + i);
			for(byte b : bs) {
				System.out.write((char)b);
			}
			System.out.println();
		}
	}
}
```
## FileReader
FileReader는 FileInputStream과 거의 유사하지만 사소한 차이가 있습니다.

FileInputStream은 InputStream을 상속받아 구현되어 바이트 단위로 처리하고, FileReader는 InputStreamReader->Reader 클래스로부터 구현되어 문자 단위로 처리합니다.

대부분의 메소드가 비슷하기 때문에 동일한 방식으로 사용이 가능합니다.


>출처
>https://lasbe.tistory.com/65