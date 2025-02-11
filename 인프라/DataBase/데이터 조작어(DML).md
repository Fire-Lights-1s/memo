## 데이터 조작어(DML - Data Manipulation Language)
- 데이터 삽입, 수정, 삭제하는 명령어
- DML 종류 : insert, update, delete
- 작업을 완료 후 commit을 해야함

### 데이터 삽입(insert)
- 문법
```sql
INSERT  INTO 테이블명[(컬럼명1, 컬렴명2,...)]
VALUES (값1, 값2,...);
```
- 테이블명 뒤에 컬럼리스트 생략 시에는 values절 뒤에 기본 컬럼 순서대로 모든 값 나열해야함.
- 테이블명 뒤에 컬럼리스트 작성 시에는 values절 뒤에 컬럼리스트와 순서, 개수 동일하게 값 나열해야함.
- 예시
```sql
insert into departments values(280, 'Java', 107, 1700);
insert into departments(department_name, manager_id, location_id, department_id) values('MySQL', 200, 1700, 290);
```
- insert + 서브쿼리 활용
- employees 테이블 구조 복사 후 데이터 삽입
```sql
create table copy_emp
as select *
   from employees
   where 1=2;
insert into copy_emp
	select *
    from employees;
```
### 데이터 수정(update)
- 문법
```sql
UPDATE 테이블명 SET 컬럼명 = 값[, 컬럼명2 = 값2, ...]
[WHERE 조건문];
```
- 예시
```sql
update copy_emp set salary =7500, department_id=10
where employee_id=203;
```
### 데이터 삭제(delete)
- 문법
```sql
DELETE FROM 테이블명
[WHERE 조건문];
```
