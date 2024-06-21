```sql
select * | 컬럼명1, 컬럼명2,...
from 테이블명
[where 조건문]
[order by 컬럼명 [asc | desc]];
```
- 논리 연산자에도 우선순위가 있음
- 산술식에 null값이 포함된 경우 그 결과도 null이다
- column alias란? 출력되는 컬럼명(제목)을 재명명하는 문법
`select EMPLOYEE_ID, LAST_NAME, SALARY, COMMISSION_PCT,` 
	`(12*SALARY)+(12*SALARY*coalesce(COMMISSION_PCT, 0)) as annual_income`
`from employees;`
- distinct 키워드 : 중복값을 자동으로 제거해 주는 키워드
`select distinct department_id`
`from employees;`
- [[where절과 order by절]]
- [[조인(join)]]