## 트랜잭션이란?
- **하나의 논리적인 작업 단위**로 여러 개의 DML이 하나의 트랜잭션을 구성할 수 있음.
- 트랜잭션 명령어
	- 작업을 저장하는 명령어 - commit
	- 작업을 취소하는 명령어 - rollback
	- 트랜잭션 작업 중 되돌아갈 지점을 생성하는 명령어 - savepoint
#### MySQL Workbench에서 트랜잭션 설정하기→ [Query] 메뉴 
1. [Auto-Commit Transactions] 
	- 기능 할성화 : 자동 커밋 기능 설정, 즉 DML 실행 시 바로 저장됨. 
		- 장점 : 알아서 자동 커밋되므로 편함
		- 단점 : 작업 실수 시 되돌릴 수 없음. 
	- 기능 비활성화 : 수동 커밋, 수동 롤백 가능함, 즉 DML 실행 후 커밋, 롤백 결정해야 함. 
		- 장점 : 작업 실수 시 되돌릴 수 있음. 
		- 단점 : 작업 후 수동으로 커밋, 롤백을 실행하여 작업을 마무리를 해줘야 함
1. [Commit Transaction] : 수동으로 커밋하는 메뉴 
2. [Rollback Transaction] : 수동으로 롤백하는 메뉴 
### 트랜잭션 코드
- commit 예시
```sql
update employees
set salary = 6500
where employee_id = 200; -- 임시  data 상태

select EMPLOYEE_ID, LAST_NAME, DEPARTMENT_ID
from employees
where EMPLOYEE_ID = 200; -- 미리보기

commit;-- 저장
```
- rollback 예시
```sql
delete from copy_emp; -- 임시 data 상태
select * from copy_emp; -- 미리보기
rollback; -- 취소
```
- savepoint 예시
- 사용 이유 commit은 실행 시간이 길기 때문
```sql
update copy_emp set salary = 28000
where employee_id = 101; -- 임시 data 상태

select EMPLOYEE_ID, LAST_NAME, salary
from copy_emp
where EMPLOYEE_ID = 101; -- 미리보기

savepoint test1; -- savepoint 생성

update copy_emp set salary = 32000
where employee_id = 102; -- 임시 data 상태

select EMPLOYEE_ID, LAST_NAME, salary
from copy_emp
where EMPLOYEE_ID = 102; -- 미리보기

rollback to test1; -- savepoint 지점으로 롤백

select EMPLOYEE_ID, LAST_NAME, salary
from copy_emp
where EMPLOYEE_ID in (101,102); -- 부분 취소 확인

commit; -- 저장
```