### <DB 생성 및 운영하기>
1. Database 생성
2. Table 생성
3. Table 데이터 삽입/수정/삭제
4. Table 데이터 검색

#### 1. Database 생성
```sql
CREATE schema shopdb;
```
#### 2. Table 생성
```sql
CREATE TABLE 테이블명
(컬럼명1 데이터타입(컬럼사이즈),
컬럼명2 데이터타입(컬럼사이즈) default 기본값,
컬럼명3 데이터타입(컬럼사이즈) 제약조건);
```

**auto_increment 속성**
- 컬럼에 1부터 시자개서 1씩 증가하는 고유값을 반환해 주는 속성
- 전제조건 : 컬럼이 숫자 데이터타입 
		컬럼에 primary key 또는 unique 제약조건 선언
		시작값, 증가값은 수정 가능함.

#### [3. 데이터 삽입/수정/삭제](<데이터 조작어(DML)>)
##### (1) 데이터 삽입(insert)
```sql
INSERT  INTO 테이블명[(컬럼명1, 컬렴명2,...)]
VALUES (값1, 값2,...);
```
##### (2) 데이터 수정(update)
```sql
UPDATE 테이블명 SET 컬럼명 = 값[, 컬럼명2 = 값2, ...]
[WHERE 조건문];
```
##### (3) 데이터 삭제(delete)
```sql
DELETE FROM 테이블명
[WHERE 조건문];
```

#### 4. [[데이터 조회(select)]]
```sql
SELECT * | 컬럼명1, 컬럼명2,...
FROM 테이블명
[WHERE 조건문]
[ORDER BY 컬럼명 [asc | desc]];
```
- 논리 연산자에도 우선순위가 있음

- 산술식에 null값이 포함된 경우 그 결과도 null이다
- column alias란? 출력되는 컬럼명(제목)을 재명명하는 문법
```sql
select EMPLOYEE_ID, LAST_NAME, SALARY, COMMISSION_PCT, 
	(12*SALARY)+(12*SALARY*coalesce(COMMISSION_PCT, 0)) as annual_income
from employees;
```
- distinct 키워드 : 중복값을 자동으로 제거해 주는 키워드
```sql
select distinct department_id
from employees;
```
- [[where절과 order by절]]
- [[조인(join)]]

