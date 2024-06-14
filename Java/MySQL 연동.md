### 1단계 JDBC 프로그램 가져오기

- JDBC 프로그램 => com폴더 mysql폴더 cj폴더 jdbc폴더 => Driver.class 실행파일 => 불러오기
- C:\Program Files\ojdkbuild\java-11-openjdk-11.0.15-1\lib => 자바프로그램 원본 src.zip
=> src\java.base\java\lang 폴더 => Class.java 자바파일

- Java 프로그램(JDK 설치 할때 => 자동으로 설치)
- java폴더 lang폴더 => Class.java (Class.class) => Java 프로그램 사용 => 기본폴더

>Class 파일 관리하는 명령 모음
```
Class.forName("com.mysql.cj.jdbc.Driver");
```
  

### 2단계 JDBC 프로그램 이용해서 데이터베이스 접속

- java폴더 sql폴더 => DriverManager 자바 프로그램 => JDBC Driver 관리하는 프로그램


>import="java.sql.DriverManager" 자바프로그램 위치 표시
>
>DriverManager.getConnection(url, user, password) => 데이터베이스 연결 명령
>
>url : 데이터베이스 서버 웹 주소,
>user : 데이터베이스 서버에 접속할 수 있는 user
>password : 데이터베이스 서버 접속 비밀번호
```
String dbUrl = "jdbc:mysql://localhost:3306/jspdb?serverTimezone=Asia/Seoul";

String dbUser = "root";

String dbPass = "1234";
```
>import="java.sql.Connection" 자바프로그램 위치 표시
>
>Connection : 데이터베이스 연결 정보를 저장 프로그램
```
Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
```
