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
#### 제약 조건
- 제약 조건 유형 : NOT NULL, UNIQUE, PRIMARY KEY, FOREIGN KEY, CHECK
- 제약 조건 목적 : 컬럼에 부적합한 데이터가 삽입/수정/삭제 되는 것을 막기 위함
- 제약조건 문법 
	- 컬럼 레벨 문법 : nn(★), pk, uk, ck, fk
	- 테이블 레벨 문법 : pk, uk, ck, fk
- NOT NULL
	- 컬럼에 null값을 허용하지 않는 제약 조건
```sql
create table test1
(id int not null,
name varchar(30) not null,
jumin varchar(13) not null,
job varchar(20),
email varchar(20),
phone varchar(20) not null,
start_date date);
```
- UNIQUE
	- 컬럼에 중복 데이터가 삽입/수정되는 것을 막아주는 제약 조건
	- 고유한 값만 들어와야 하는 컬럼에 사용됨
```sql
create table test2
(id int not null unique,
name varchar(30) not null,
jumin varchar(13) not null unique,
job varchar(20),
email varchar(20) unique,
phone varchar(20) not null unique,
start_date date);
```
- PRIMARY KEY
	- 기본 키 제약 조건으로 NOT NULL + UNIQUE의 성격을 모두 가지는 제약 조건
	- 테이블 당 한 번만 선언 가능
```sql
create table test3
(id int primary key,
name varchar(30) not null,
jumin varchar(13) not null unique,
job varchar(20),
email varchar(20) unique,
phone varchar(20) not null unique,
start_date date);
```
- FOREIGN KEY
	- 외래 키 제약 조건으로 자기 자신 테이블이나 다른 테이블의 특정 컬럼을 참조하는 제약 조건
	- PRIMARY KEY 또는 UNIQUE 제약 조건이 선언된 컬럼만 참조 할 수 있음
```sql
create table tset4
(t_num int primary key,
t_id int,
title varchar(20) not null,
story varchar(100) not null,
foreign key(t_id) references test3(id)
);
```
- CHECK
	- 해당 컬럼이 만족해야 하는 조건문을 자유롭게 지정할 수 있는 제약 조건
```sql
create table test5
(...
salary int check (salary > 0),
level int check (level between 1 and 3),
jumin char(13) check (length(jumin) = 13),
gender varchar(10) check (gender in ('남', '여')),
email varchar(50) check (email like '%@%'),
start_date date check (start_date >= '2005-01-01'),
...);
```