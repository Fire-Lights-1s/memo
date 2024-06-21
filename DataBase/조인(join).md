### 조인(join)이란?
- 여러 테이블의 데이터를 함께 출력하는 구문
```sql
SELECT 컬럼명1, 컬럼명2,...
FROM 테이블1 JOIN 테이블2
ON 테이블1.컬럼명 = 테이블2.컬럼명
[WHERE 조건문]
[ORDER BY 컬럼명];
```
- 예시 
```sql
select employee_id, LAST_NAME, SALARY, e.DEPARTMENT_ID, DEPARTMENT_NAME
from employees e join departments d
on e.DEPARTMENT_ID = d.DEPARTMENT_ID
order by EMPLOYEE_ID;
```
- 두 테이블에 공통으로 존재하는 컬럼은 어느 테이블 컬럼인지 명시해야함
- 모든 컬럼에 어느 테이블 컬럼인지 명시하는게 성능에서 유리함
- 테이블 alias를 활용해서 테이블 명을 별칭으로 만들 수 있음

#### 셀프 조인
- 하나의 테이블을 마치 다른 테이블 처럼 alias를 다르게 부여해서 조인하는 유형
- 아래는 직원의 매니저 이름을 추가해서 출력하는 예시
```sql
select e1.employee_id, e1.LAST_NAME, e1.JOB_ID, e1.SALARY, e1.MANAGER_ID, e2.LAST_NAME
 from employees e1 join employees e2
 on e1.MANAGER_ID = e2.EMPLOYEE_ID
 order by e1.EMPLOYEE_ID, e1.MANAGER_ID;
```
#### N개 테이블 조인
- 테이블 수(N) | 조인 조건수(N-1)
- 아래는 직원, 부서, 위치 테이블을 조인한 예시
```sql
select e.EMPLOYEE_ID, e.LAST_NAME, e.SALARY, e.DEPARTMENT_ID, d.DEPARTMENT_NAME, d.LOCATION_ID, l.CITY, l.STREET_ADDRESS 
 from employees e join departments d 
 on e.DEPARTMENT_ID = d.DEPARTMENT_ID
 join locations l
 on d.LOCATION_ID = l.LOCATION_ID;
```

### 추가 조인 문법
#### 내부 조인(inner join)
- 조인조건을 만족하는 행만 반환하는 조인 유형
- on절 join(기본조인유형)
#### 외부 조인(outer join)
- 조인 조건을 만족하는 행과 조인 조건을 만족하지 않는 행까지 모두 반환하는 조인 유형
- left outer join : 왼쪽 테이블이 기준
- right outer join : 오른쪽 테이블이 기준
- 기준 테이블A에  테이블B에서 조인 조건을 만족하지 않으면 NULL값으로 행을 만듬