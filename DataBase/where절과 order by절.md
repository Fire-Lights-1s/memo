### 1.where절(조건절)
```sql
SELECT 컬럼명1, 컬렴명2,...
FROM 테이블명
WHERE 좌변 = 우변;
(컬렴명) (비교연산자) (값) -> 숫자,'문자', '날짜'
```
#### 비교연산자
- 단일행 비교 연산자 : =, !=, <, <=, >, >=
- 추가 비교 연산자 : between, in, like, is null
##### between A and B
- between : A값과 B값 사이의 값을 비교하는 연산
```sql
select EMPLOYEE_ID, LAST_NAME, SALARY  
from employees  
where SALARY between 2500 and 3500; 
== where SALARY >= 2500 and SALARY <= 3500;
```
##### in (A, B, ...)
- in : (=, or)의 성격을 내포한 비교연산자
```sql
select EMPLOYEE_ID, LAST_NAME, manager_id, department_id
from employees
where MANAGER_ID in (100, 101, 201);
```
##### like "\_\_a%"
- like : 패턴 일치 여부를 비교해 주는 연산자
- % : 0개 또는 여러개의 문자가 올 수 있음
- _ : 갯수 만큼의 문자가 올 수 있음
```sql
select EMPLOYEE_ID, LAST_NAME, SALARY, JOB_ID 
from employees
where JOB_ID like "%it%";
```
##### is null
- 값이 null인지 비교하는 연산자
```sql
select EMPLOYEE_ID, LAST_NAME, SALARY, COMMISSION_PCT
from employees
where COMMISSION_PCT is null;
```
#### 논리연산자
- 종류 : and, or, not
##### and
- where 절에 여러 조건문 작성 시 모두 만족해야 되는 경우 사용
```sql
select EMPLOYEE_ID, LAST_NAME, SALARY, JOB_ID 
from employees
where SALARY >= 10000 and JOB_ID like '%man%';

```
##### or
- where 절에 여러 조건문 작성 시 하나 이상 만족하면 되는 경우 사용
```sql
select EMPLOYEE_ID, LAST_NAME, SALARY, JOB_ID 
from employees
where SALARY >= 10000 or JOB_ID like '%man%';
```
##### not
- 부정(반대)을 의미하는 논리연산자로 비교연산자와 조합으로 활용됨
- 예시
- between A and B : A이상 B이하 <--> not between A and B A미만 B초과
- in : (=, or) <--> not in : (!=, and)
- like <--> not like
- is null <--> is not null


### 2.order by 절(정렬)
```sql
SELECT 컬럼명1, 컬럼명2,...
FROM 테이블명
WHERE 조건문
ORDER BY 컬럼명 [asc | desc];
```
- order by 절에 colum alias 사용 가능함
```sql
select employee_id, LAST_NAME, SALARY*12 as annsal
from employees
order by annsal;
```
- order by 절에 위치 표기법 사용 가능함
```sql
select employee_id, LAST_NAME, SALARY*12 as annsal
from employees
order by 3;
```
- 여러 컬럼을 기준으로 정렬하기
```sql
select employee_id, LAST_NAME, SALARY, DEPARTMENT_ID
from employees
order by DEPARTMENT_ID, SALARY desc;
```
