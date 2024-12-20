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