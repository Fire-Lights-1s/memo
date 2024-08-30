# Select 구문
Column Alias : 오라클에선 대문자로만 출력
1. 컬럼명 AS alias 
2. 컬럼명 alias 
3. 컬럼명 \[AS] “Alias” => **대소문자 구분, 공백 포함, 특수문자 포함을 원하는 경우**

연결 연산자(||) : mysql에서의 concat()
※oracle에서 concat()은 매개변수를 2개만 받을 수 있다

```sql
select last_name || job_id as "Employees"
from employees;
```

리터럴 문자 
- 리터럴 문자란? 쿼리구문에 포함된 일반 문자, 숫자, 날짜 값 
- 문자나 날짜 리터럴은 작은 따옴표`''`로 묶어서 작성해야함.
```sql
select last_name || ' is a ' || job_id as "Employee Details"
from employees;
```

# Where 절
저장된 데이터의 문자를 비교할 때 대문자 소문자를 구분한다.
```sql
select employee_id, last_name, salary, department_id
from employees
where last_name = 'whalen';
```
저장된 데이터는 'Whalen' 이므로 아무것도 나오지 않는다