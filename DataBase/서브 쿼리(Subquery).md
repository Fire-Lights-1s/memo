### 서브 쿼리(Subquery)란? 
- 쿼리 구문 안에 또 다시 쿼리 구문이 포함되어 있는 형태 
- Group by 절을 제외한 쿼리 구문에 사용 가능 
- 서브 쿼리 유형 : 단일 행 서브 쿼리, 다중 행 서브 쿼리
### 서브 쿼리 문법
```sql
SELECT LAST_NAME, JOB_ID, SALARY
FROM employees 
WHERE SALARY = (SELECT min(SALARY)
				FROM employees);
```
#### 단일 행 서브 쿼리(Single-row subquery)
- 서브 쿼리로부터 메인 쿼리로 한 행만 반환 되는 유형 
- 단일 행 서브 쿼리인 경우 메인 쿼리에 단일 행 비교 연산자를 사용해야 함.
#### 다중 행 서브 쿼리(Multiple-row subquery)
- 서브 쿼리로부터 메인 쿼리로 두 개 이상의 행을 반환 되는 유형 
- 다중 행 서브 쿼리인 경우 메인 쿼리에 다중 행 비교 연산자를 사용해야 함.
- 연산자
	- IN : 리스트의 임의 멤버와 같음
	- ANY : 관계가 TRUE인 subquery의 결과 집합에 요소가 1개 이상 있는 경우 TRUE를 반환
	- ALL : subquery 결과 집합의 모든 요소에 대한 관계가 TRUE인 경우 TRUE를 반환
	- ANY와 ALL은 =, !=, <, >, <=, >= 연산자가 앞에 있어야 함.
##### 주의 사항
- not in 사용 시 subquery에 is not null이 있어야 함
- subquery의 결과 집합에 null이 포함되어있기 때문이다.
```sql
SELECT department_id, department_name
FROM departments
WHERE DEPARTMENT_ID NOT IN (SELECT DISTINCT DEPARTMENT_ID 
							FROM employees 
                            WHERE DEPARTMENT_ID IS NOT null);
```
- 다중 컬럼 서브 쿼리는 양쪽이 모두 다중 컬럼이어야 한다.
```sql
SELECT employee_id, first_name, department_id, salary 
FROM employees 
WHERE (department_id, salary) IN (SELECT department_id, min(salary) 
								  FROM employees 
								  GROUP BY department_id) 
ORDER BY department_id;
```