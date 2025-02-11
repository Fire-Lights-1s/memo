**오라클에 접속**
```shell
sqlplus '/as sysdba'
```

**오라클 데이터베이스 'service name' 확인**
```sql
SELECT NAME, DB_UNIQUE_NAME FROM v$database;
```

**오라클 데이터베이스 'SID' 확인**
```sql
SELECT instance FROM v$thread;
```
