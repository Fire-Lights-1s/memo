# Data Dictionary 구성요소
- Base Tables 
 DB의 모든 내용을 DBMS가 보기 쉬운 형태로 기록해 놓은 테이블

- Data Dictionary Views
 Base table을 사용자가 보기 쉬운 형태로 만들어 놓은 뷰 읽기전용(select만 가능)

## 객체(Object) 사용을 위한 주요 Data Dictionary Views
1. DICTIONARY(DICT) : 각 Dictionary에 대한 정보 
2. USER_CATALOG(CAT) : 사용자 소유의 테이블, 뷰, 동의어 시퀀스 목록을 보여줌 
3. USER_OBJECTS(OBJ) : 사용자 소유의 모든 오브젝트에 대한 정보를 보여줌 
4. USER_TABLES(TABS) : 사용자 소유의 모든 테이블에 대한 정보를 보여줌 
5. USER_TAB_COLUMNS(COLS) : 사용자 소유의 모든 테이블을 구성하고 있는 컬럼에 대한 정보를 보여줌 
6. USER_VIEWS : 사용자 소유의 모든 뷰에 대한 정보를 보여줌 
7. USER_SYNONYMS(SYN) : 사용자 소유의 모든 동의어에 대한 정보를 보여줌 
8. USER_SEQUENCES(SEQ) : 사용자 소유의 Sequence에 대한 정보를 보여줌 
9. USER_CONSTRAINTS : 사용자 소유의 모든 table의 제약조건에 대한 정보를 보여줌 
10. USER_CONS_COLUMNS : 사용자 소유의 모든 table의 제약사항이 지정된 column에 대한 정보를 보여줌 
11. USER_TAB_COMMENTS : 사용자 소유의 모든 테이블의 주석을 보여줌. 
12. USER_COL_COMMENTS : table의 column에 대한 주석을 보여줌 
13. USER_INDEXES(IND) : 사용자 소유의 모든 인덱스에 대한 정보를 보여줌 
14. USER_IND_COLUMNS : 어떤 column에 인덱스가 생성되어 있는지에 대한 정보를 보여줌