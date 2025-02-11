# tablespace 란?

오라클 데이터베이스에서 관리하는 데이터는 실제로 **'.dbf'** 확장자를 가진 데이터 파일로 저장되며, 이러한 파일은 데이터가 저장되는 **물리적 공간**으로 볼 수 있다.

테이블스페이스(tablespace)란 데이터가 저장되는 **논리적인 공간**으로 오라클 데이터베이스의 저장 단위 중 최상위 개념의 데이터 공간이다.

- 하나의 테이블 스페이스는 하나 이상의 데이터 파일로 구성된다
- 업무 단위나 사용 용도에 따라 하나의 데이터베이스 안에서 여러 개의 테이블 스페이스를 분리할 수 있다
- 테이블 스페이스 분리는 관리적 측면의 이점과 더불어 성능 최적화에도 영향을 줄 수 있다

## tablespace 구조

- 오라클의 논리적 저장 계층은 **'Data Blocks' -> 'Extents' -> 'Segments' -> 'Tablespace'** 단계로 이루어져 있다

**데이터 블록(Data Blocks)**
- 오라클 데이터베이스에서 '가장 작은 논리적 데이터 저장 단위' 이면서 '가장 기본적인 단위'
- 하나 이상의 OS Block(물리적 저장 단위)로 구성
- OS Block Size를 고려하여 데이터 블록의 크기를 지정 가능

**익스텐트(Extents)**
- 연속된 데이터 블록들(Data Blocks)의 집합
- 연속된 블록들은 같은 테이블스페이스 내에서 할당되며, 데이터가 저장될 공간을 제공
- 데이터가 증가함에 따라 더 많은 익스텐트가 할당되어 상위 계층인 세그먼트의 크기가 확장

**세그먼트(Segments)**
- 하나 이상의 익스텐트(Extents)로 구성
- 세그먼트의 종류
	- 테이블 데이터를 저장하는 '**테이블 세그먼트**', 
	- 인덱스를 저장하는 '**인덱스 세그먼트**',
	- LOB_(Large Object)_ 데이터를 저장하는 '**LOB 세그먼트**'
	- 트랜잭션의 롤백을 위한 '**UNDO 세그먼트**'
- 하나의 테이블을 생성한다는 것은 하나의 테이블 세그먼트를 만드는 것과 동일
- 각각의 인덱스 역시 별도의 세그먼트를 가진다

# Tablespace 명령어

## Tablespace 생성

```sql
CREATE TABLESPACE 테이블스페이스명
        DATAFILE '/경로/테이블스페이스파일명.dbf'
        SIZE 초기용량(100m,1g 등)
        AUTOEXTEND ON NEXT 자동증가용량(10M 등)
        MAXSIZE UNLIMITED;
```

```sql
CREATE [BIGFILE | SMALLFILE(기본값)] 
TABLESPACE <테이블스페이스명> 
DATAFILE '<경로>' SIZE <크기> 
[EXTENT MANAGEMENT 
 [DICTIONARY | LOCAL(기본값) [AUTOALLOCATE(기본값) | UNIFORM SIZE <크기>]] 
] 
[SEGMENT SPACE MANAGEMENT [AUTO(기본값) | MANUAL]]
```
[옵션 설명](<Tablespace 옵션 설명.md>)
## Tablespace 조회
```sql
    SELECT * FROM dba_data_files;  
```
- 데이터가 저장되는 물리경로 및 테이블스페이스 정보

```sql
    SELECT * FROM dba_tablespaces;  
```
- 설정된 테이블 스페이스 확인

```sql
    SELECT * FROM user_users; 
```
- 현재 유저의 default_tablespace 확인

## Tablespace 변경

```sql
    ALTER USER [유저명] DEFAULT TABLESPACE [테이블 스페이스명]; 
```
- 유저의 default tablespace 변경

```sql
    ALTER TABLE [테이블명] MOVE TABLESPACE [테이블 스페이스명]; 
```
- 테이블의 tablespace 변경

```sql
    ALTER TABLESPACE [테이블 스페이스명] ONLINE; 
    ALTER TABLESPACE [테이블 스페이스명] OFFLINE; 
```
- 테이블 스페이스 수정이나 삭제 시 설정

```sql
    ALTER TABLESPACE RENAME A TO B'
```
- 테이블 스페이스 물리적인 파일의 이름 또는 위치변경 

```sql
    ALTER DATABASE DATAFILE 'C:\경로\DB.dbf' RESIZE 10M; 
```
- 테이블스페이스 공간관리

```sql
    ALTER TABLESPACE [테이블 스페이스명] ADD DATAFILE 'C:\경로\DB.dbf' SIZE 10M; 
```
- 공간이 가득찬 경우 증설

```sql
    ALTER TABLESPACE [테이블 스페이스명] ADD DATAFILE 'C:\경로\DB.dbf' 
    SIZE 10M  AUTOEXTEND ON NEXT 10M MAXSIZE 10M; 
```
- 10M 씩 자동증가

## Tablespace 삭제
```sql
 DROP TABLESPACE <테이블스페이스명> INCLUDING CONTENTS AND DATAFILES;
```
- contents : 모든 세그먼트를 삭제
- datafiles : 모든 데이터 파일까지 삭제


# 실습
```sql
create tablespace test_tablespace
DATAFILE '/app/oracle/tableSpace/test_tablespace.dbf' 
SIZE 100M 
AUTOEXTEND ON NEXT 10M MAXSIZE UNLIMITED;
```

```sql
SELECT * FROM dba_tablespaces where upper(tablespace_name)='TEST_TABLESPACE';
```
