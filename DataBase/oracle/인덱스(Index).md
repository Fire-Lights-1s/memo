# Index
- Oracle 서버에서 포인터를 사용하여 행의 검색 속도를 높이기 위해 사용하는 Object 
- Oracle 서버가 자동으로 사용하고 유지 관리함. 
- WHERE절이나 조인조건에서 **자주 사용되는 컬럼**인 경우 인덱스 생성 시 성능에 도움이 됨. 
- 테이블이 작거나 자주 갱신되는 컬럼 또는 자주 사용하지 않는 컬럼에는 인덱스 생성 권장하지 않음
## 인덱스 생성
자동생성 
- Primary key 또는 Unique 제약조건이 정의된 컬럼에 자동 생성됨. 

 수동생성 
- 행에 액세스하는 속도를 높이기 위해 유저가 인덱스를 생성할 수 있음.