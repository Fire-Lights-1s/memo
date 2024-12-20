# application.properties 설정
- spring boot 3버전 기준
- DB는 MySQL 사용

```properties
# 에플리케이션 포토 설정
server.port=80

#MySQL connect
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/springdb?serverTimezone=Asia/Seoul
spring.datasource.username=root
spring.datasource.password=1234

#실행되는 쿼리 콘솔 출력
spring.jpa.properties.hibernate.show_sql=true

#콘솔에 출력되는 쿼리를 가독성 좋게 포맷팅
spring.jpa.properties.hibernate.format_sql=true

#쿼리에 물음표로 출력되는 바인드 파라미터 출력
logging.level.org.hibernate.type.descriptor.sql=trace

#데이터베이스 초기화 전략 - DDL AUTO 옵션
#none 사용하지 않음, create 기존 테이블 삭제 후 테이블 생성
#create-drop 기준 테이블 삭제 후 테이블 생성, 종료 시점에 테이블 삭제
#update 변경된 스키마 적용
#validate : 엔티티와 테이블 정상 매핑 확인
spring.jpa.hibernate.ddl-auto=create

#데이터베이스 SQL구문 설정
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
```