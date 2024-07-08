## 커넥션 풀 (Connection Pool)
- 연결 정보를 저장하는 기억 장소
- 데이터 베이스와 연결된 Connection 연결 정보를 미리 웹 서버 실행할 때 만들어 놓음(생성)
- 풀(Pool) 속에 저장해 두고 필요할 때 마다 이 풀에 접근하여 Connection 연결 정보를 가져옴
- 작업이 끝나면 반환
- 장점
	- 성능 향상(속도 높아짐)
	- DB연결 정보 수정 최소화

### 과정
- 톰캣(Tomcat)에서 제공하는 DBCP(DataBaseConnection Pool) 프로그램 사용
1. webapp - META-INF 폴더 - context.xml(1,2단계 연결 => 자원 이름 정의)
2. DAO에서 자원의 이름을 불러서 사용
3. 작업을 완료하면 그 관련 기억 장소 해제 작업

xml파일 내용
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Context>
	<Resource name="jdbc/MysqlDB"
		auth="Container"
		type="javax.sql.DataSource"
		driverClassName="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/jspdb?serverTimezone=Asia/Seoul"
		username="root"
		password="1234"/>
</Context>
```
java 파일 내용
```java
Context init = new InitialContext();
// init.lookup("자원 위치/자원 이름")
DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MysqlDB");

Connection conn = ds.getConnection();
```