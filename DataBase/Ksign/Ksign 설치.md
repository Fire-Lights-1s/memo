# 환경
- OS : Rocky Linux 8.10
- DB : Oracle 19c

# 설치 전 유의사항

- KSignSecureDB Agent 의 이전 버전 : 없음 (신규설치)

- Java 설치 여부 (JRE 1.4.2 이상) 
```shell
java –version
javac -version
```

- JAVASYSPRIV 권한 확인
```sql
select * from dba_roles where role like ‘%JAVA%’;
select object_name, procedure_name from all_procedures where object_name='DBMS_JAVA';
```
JAVASYSPRIV 권한이 확인 되지 않거나 dbms_java 패키지 정보가 보이지 않으면 `$ORACLE_HOME/javavm/install/initjvm.sql` 실행하여 설치

- JAVA_POOL_SIZE 조정 : 세션당 약 2MB 정도 필요
```sql
select component, 
current_size/1048576||'M' current_size, 
min_size/1078576||'M' min_size 
from v$sga_dynamic_components 
where component in ('shared pool', 'large pool', 'java pool', 'DEFAULT buffer cache');

show parameter JAVA_POOL_SIZE;
```

```sql
alter system set java_pool_size=10m scope=both;
```

# Oracle19c - Ksign 설치 시 주의사항

1. 설치할 때 에러나면 dbsec계정 날리고 Tablespace 다 날리고 하기

2. 12C over 폴더 내에 sdb12_java_table.sql 파일 있어야함

<dbesc 삭제 쿼리>
DROP USER dbsec CASCADE

<tablespace 삭제 쿼리>
DROP TABLESPACE SECUREDATA INCLUDING CONTENTS AND DATAFILES;
DROP TABLESPACE SECUREINDEX INCLUDING CONTENTS AND DATAFILES;

** 테이블, 인덱스 존재하면 삭제가 안됨

<INDEX 조회 쿼리>
SELECT INDEX_NAME, TABLE_NAME, TABLESPACE_NAME
FROM DBA_INDEXES WHERE TABLESPACE_NAME= 'SECUREDATA';

** 키 설정 제거해줘야 테이블 삭제됨 (쿼리)
ALTER TABLE DOMAIN_ENCOBJ DROP PRIMARY KEY;

<테이블 삭제 쿼리>  --테이블 삭제 시 인덱스 자동 삭제됨
DROP TABLE DOMAIN_ENCOBJ;

3. SDBFilter.jar 파일이 bin폴더에 없기 때문에 /home/dbsec/KSignSecureDB/Filter/JavaFilter/Oracle/bin 에서 복사해서 bin에 넣어줘야함

4. Server 먼저 실행 해준 뒤 Agent 실행 전에 admin으로 등록해주고 실행해줘야함

5. 오라클 19c의 경우 admin에서 도메인 등록할 때 11g로 해줘야 함 -> 버그성 오류로, 상위버전 선택이 있지만 안먹힘

# 설치
Agent/jscript 폴더의 3.3. sdb01_java_dbsec.sql 파일 실행
sqlplus 접속
```shell
sqlplus '/as sysdba'
```

```sql
@/[절대경로]/sdb01_java_dbsec.sql
```


### 이슈
- KSignSecureDB Agent DB Setup 과정에서 ORA-12154: TNS:could not resolve the connect identifier specified 오류 발생


1. 오라클 데이타베이스명을 확인하는 방법  
  
```sql
SELECT NAME, DB_UNIQUE_NAME FROM v$database;  
```
  
2. 오라클 SID를 확인하는 방법  
  
```sql
SELECT instance FROM v$thread;
```
