### 받은 정보 처리
- 사용자 입력한 학생번호,학생이름을 => 서버에 전달 => request 기억장소 => 저장
- request에서 저장된 num, name 가져와서 => 변수에 저장

```
String num = request.getParameter("num");
String name = request.getParameter("name");
```
### MySQL 연결 및 실행
##### [[MySQL 연동|1단계 JDBC 프로그램 가져오기]]

```
Class.forName("com.mysql.cj.jdbc.Driver");
```

##### 2단계 JDBC 프로그램 이용해서 데이터베이스 접속

```
String dbUrl = "jdbc:mysql://localhost:3306/jspdb?serverTimezone=Asia/Seoul";

String dbUser = "root";

String dbPass = "1234";

Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
```

##### 3단계 sql구문을 만들어서 실행할 준비

```
String sql = "insert into student(num,name) values(1,'김길동')";

PreparedStatement pstmt = con.prepareStatement(sql);

// 또는 미완선 구문 작성
String sql = "insert into student(num,name) values(?,?)";
PreparedStatement pstmt = con.prepareStatement(sql);

pstmt.setInt(1, num);
pstmt.setString(2, name);

```

##### 4단계 sql구문 실행 => Query OK =>insert,update,delete

```
pstmt.executeUpdate();
```