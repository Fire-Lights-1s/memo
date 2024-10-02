## FileInputStream

자바에서는 다양한 타입의 확장자를 가진 파일을 읽기 위해 FileInputStream 클래스를 이용합니다.

이 클래스를 이용하면 파일에서 바이트 단위로 입력이 가능해 다른 입력 클래스들과 연결해서 데이터를 읽어올 수 있습니다.

## FileReader
FileReader는 FileInputStream과 거의 유사하지만 사소한 차이가 있습니다.

FileInputStream은 InputStream을 상속받아 구현되어 바이트 단위로 처리하고, FileReader는 InputStreamReader->Reader 클래스로부터 구현되어 문자 단위로 처리합니다.

대부분의 메소드가 비슷하기 때문에 동일한 방식으로 사용이 가능합니다.


>출처
>https://lasbe.tistory.com/65