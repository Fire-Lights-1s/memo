## 데이터 정의어(DDL - Data Definition Language)
- 데이터베이스 구조/ 제약 조건 정의
- create, alter, drop, truncate
	=> autocommit 내포함.
### 테이블 생성(create table)
- 문법
```sql
CREATE TABLE 테이블명
(컬럼명1 데이터타입(컬럼사이즈),
 컬럼명2 데이터타입(컬럼사이즈) [제약조건],
 컬럼명3 데이터타입(컬럼사이즈) [default 기본값],
 컬럼명n 데이터타입(컬럼사이즈)
);
```
#### 데이터 타입
- 문자 
	- vachar : 가변 형 (공간 효율 우수)
	- char : 고정 형 (성능 우수)
	- 참고 : null값이 많이 들어오는 컬럼인 경우 vachar를 권장함
- 숫자 
	- 정수 형 : smallint, int, bigint
	- 실수 형 : float, double
- 날짜
	- 년/월/일 : date
	- 년/월/일/시/분/초 : datetime
#### [제약 조건](<제약 조건>)
- 제약 조건 유형 : NOT NULL, UNIQUE, PRIMARY KEY, FOREIGN KEY, CHECK
- 제약 조건 목적 : 컬럼에 부적합한 데이터가 삽입/수정/삭제 되는 것을 막기 위함
- 제약조건 문법 
	- 컬럼 레벨 문법 : nn(★), pk, uk, ck, fk
	- 테이블 레벨 문법 : pk, uk, ck, fk
- NOT NULL
	- 컬럼에 null값을 허용하지 않는 제약 조건
- UNIQUE
	- 컬럼에 중복 데이터가 삽입/수정되는 것을 막아주는 제약 조건
	- 고유한 값만 들어와야 하는 컬럼에 사용됨
- PRIMARY KEY
	- 기본 키 제약 조건으로 NOT NULL + UNIQUE의 성격을 모두 가지는 제약 조건
	- 테이블 당 한 번만 선언 가능
- FOREIGN KEY
	- 외래 키 제약 조건으로 자기 자신 테이블이나 다른 테이블의 특정 컬럼을 참조하는 제약 조건
	- PRIMARY KEY 또는 UNIQUE 제약 조건이 선언된 컬럼만 참조 할 수 있음
- CHECK
	- 해당 컬럼이 만족해야 하는 조건문을 자유롭게 지정할 수 있는 제약 조건
#### 제약 조건 조회
- 제약조건 정보는 information_schema 안에 테이블들에 저장되어 있다
- sql예시
```sql
show databases;
use information_schema;
show tables;
-- 테이블의 제약조건 테이블
select *
from table_constraints
where table_name = 'test5';
-- key 제약 조건 테이블
select *
from key_column_usage
where table_schema = 'hr'
and table_name = 'employees';
-- check 제약 조건 테이블
select *
from check_constraints;
```
#### 서브쿼리 활용한 테이블 생성
- 서브쿼리를 사용해서 테이블 생성 시 원본 테이블의 구조 및 데이터 모두 복사된 테이블이 생성됨. 
- 단, 제약조건은 not null 제약조건만 복사됨. 
- 테이블 백업 또는 테스트용 테이블 생성 시 주로 활용함.
```sql
CREATE TABLE emp2
AS SELECT *
FROM employees;
```

### 테이블 수정(alter table)
1. 컬럼 추가
	- 테이블 생성 후 컬럼 추가 시 기본적으로 마지막 컬럼으로 추가됨
	- 초기 값은 null값이 삽입되어 있음
	- default값 지정 시 자동으로 default 값 삽입
	- first 또는 after 컬럼명으로 추가 위치 지정 가능
```sql
ALTER TABLE 테이블 이름
ADD 컬럼명 데이터타입 [NOT NULL] [DEFAULT default값] [first OR after 컬럼명];

ALTER TABLE dept80
ADD job_id VARCHAR(10);

ALTER TABLE dept80
ADD email VARCHAR(50) DEFAULT '미입력';
```
2. 컬럼 수정
	- 데이터 타입, 컬럼 사이즈, default 값, not null 변경 가능
	- 데이터 타입 변경 시, 데이터가 이미 존재할 때 변경 할 수 없는 경우가 있음
	- 컬럼 사이즈를 줄일 시, 줄일 데이터보다 큰 데이터가 있으면 불가능
```sql
ALTER TABLE 테이블명
MODIFY 컬럼명 데이터 타입(사이즈) [NOT NULL] [DEFAULT default값];

ALTER TABLE dept80
MODIFY job_id int;

ALTER TABLE dept80
MODIFY salary int NOT NULL DEFAULT 500;

-- 컬럼명 변경
ALTER TABLE 테이블명 
RENAME COLUMN 기존 컬럼명 TO 변경할 컬럼명

-- 테이블명 변경
ALTER TABLE 기존 테이블명
RENAME TO 변경할 테이블명명
```
3. 컬럼 삭제
	- foreign key 제약조건이 참조하는 부모 컬럼인 경우에는 제약조건을 먼저 삭제해야함
```sql
ALTER TABLE 테이블명
DROP 컬럼명;
ALTER TABLE dept80
DROP job_id;
```
4. 제약 조건 추가
	- pk, uk, ck, fk 제약 조건 추가
```sql
-- primary key와 unique 제약조건 추가 문법 동일함. 
ALTER TABLE dept80 
ADD primary key(employee_id); 

-- not null 제약조건 추가 문법 
ALTER TABLE dept80 
MODIFY annsal double(22,0) not null; 

-- check 제약조건 추가 문법 
ALTER TABLE dept80 
ADD check (salary > 100); 

-- foreign key 제약조건 추가 문법 
ALTER TABLE dept80 
ADD mgr_id int default 150;

desc dept80; 
select * from dept80; 

ALTER TABLE dept80 
ADD foreign key(mgr_id) references dept80(employee_id);
```
5. 제약 조건 삭제
	- pk, uk, ck, fk 제약 조건 삭제
	- primary key 외의 제약 조건은 제약 조건 명으로 삭제해야 함
	- primary key 제약 조건 삭제 시 오류 발생됨.
	- foreign key 제약 조건이 선언된 컬럼이 참조하고 있는 부모 컬럼에 선언된 primary key 또는 unique 제약 조건은 삭제할 수 없음. 
	- 삭제를 원하는 경우 foreign key 제약 조건을 먼저 삭제해야 함.
```sql
use information_schema; 
select * from table_constraints 
where table_name = 'dept80';

alter table dept80 
drop primary key; 

-- foreign key, check, unique 제약조건 
ALTER TABLE dept80 
DROP 제약조건 제약조건명; 

-- not null 제약조건 삭제 문법 
alter table dept80 
modify annsal double(22,0) null;
```

### 테이블 삭제(drop table) 
- 데이터베이스로부터 테이블을 삭제함. 
- 테이블 구조, 데이터, 제약조건 등 모두 삭제됨. 
```sql
DROP TABLE dept80;
``` 
### 테이블 절단(truncate table) 
- 테이블로부터 모든 행을 삭제함. 
```sql
TRUNCATE TABLE dept80;
```
