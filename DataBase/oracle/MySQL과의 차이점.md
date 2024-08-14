# Select 
Column Alias : 오라클에선 대문자로만 출력
1. 컬럼명 AS alias 
2. 컬럼명 alias 
3. 컬럼명 \[AS] “Alias” => **대소문자 구분, 공백 포함, 특수문자 포함을 원하는 경우**

연결 연산자(||) : mysql에서의 concat()

```sql
select last_name || job_id as "Employees"
from employees;
```

리터럴 문자 
- 리터럴 문자란? 쿼리구문에 포함된 일반 문자, 숫자, 날짜 값 
- 문자나 날짜 리터럴은 작은 따옴표로 묶어서 작성해야함.
```sql
select last_name || ' is a ' || job_id as "Employee Details"
from employees;
```