# Object 유형
1. Table
- 정의방법 : create table, alter table, drop table, truncate table
- 사용방법 : select, insert, update, delete

2. View
- 정의방법 : create [or replace] view, drop view
- 사용방법 : select, insert, update, delete

3. Sequence
- 자동으로 고유한 번호를 반환해 주는 번호생성기와 같은 Object
- 정의방법 : create sequence, alter sequence, drop sequence
- 사용방법 : 시퀀스명.nextval, 시퀀스명.currval
```sql
CREATE SEQUENCE dept_depid_seq
	INCREMENT BY 10
	START WITH 120
	MAXVALUE 9999
	NOCACHE
	NOCYCLE;
INSERT INTO departments (department_id,
						department_name, location_id)
values (dept_depid_seq.NETVAL, 'Support', 2500);
```

4. Index
- Oracle 서버에서 포인터를 사용하여 행의 검색 속도를 높이기 위해 사용하는 Object
- 정의방법 : create index, alter index, drop index
- 사용방법 : X
```sql
CREATE INDEX emp_last_name_idx
on employees(last_name);
DROP INDEX emp_last_name_idx;
```
5. Synonym
- 정의방법 : create synonym, drop synonym
- 사용방법 : X