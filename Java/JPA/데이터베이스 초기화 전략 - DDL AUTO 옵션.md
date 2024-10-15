## spring.jpa.hibernate.ddl-auto

**데이터베이스 ddl 자동 생성 옵션**으로

**Entity 설정을 참고**하여 **애플리케이션 실행시점**에 **Hibernate에서 자동으로 DDL을 생성**하여, **필요한 Database의 Table 설정들을 자동으로 수행해주는 기능**이다.

### ddl-auto 옵션 종류

- create: 기존테이블 삭제 후 다시 생성 (DROP 후에 CREATE)
- create-drop: create와 같으나 종료시점에 테이블 DROP (테스트 코드 실행 시 주로 사용)
- update: 변경분만 반영, 추가만 반영되고 삭제는 반영되지 않는다. (DB Lock을 방지하기 위해 운영DB에서는 사용하면 안된다.)
- validate: 엔티티와 테이블이 정상 매핑되었는지만 확인
- none: ddl 자동생성 기능을 사용하지 않음, ddl-auto 옵션을 주석처리 한 것과 같다. (사실상 없는 값이지만 관례상 none이라고 한다.) 

---
## ddl 자동 생성 기능 사용 시 주의 사항

JPA의 ddl-auto 옵션은 개발 환경에서 편리하게 데이터베이스 ddl을 관리할 수 있지만 **몇 가지 주의 사항**이 있다.

> **실제 운영 환경에서는 none 또는 validate 옵션을 사용**

**create, create-drop, update** 옵션을 사용하면 **테이블 변경이 자동으로 반영되어 데이터 손실 위험**이 있기 때문이다.

> **개발 초기 단계는 create 또는 update 옵션을 사용**

개발 초기 단계의 특성상 **코드와 데이터베이스 테이블이 자주 변경**된다.

따라서 개발 초기 단계에서 create 또는 update 옵션을 사용하여 테이블을 빠르게 반영하고 관리해 **개발 효율성을 높일 수 있다.**

> **테스트 서버는 update 또는 validate 옵션을 사용**

테스트 서버에서는 개발 과정에서의 오류를 찾기 위해 **실제 상황과 가까운 환경을 구축**하므로

자동으로 데이터베이스 테이블을 관리하면서도 **실제 데이터 손실 위험을 최소화**하여 **Entity와 데이터베이스 스키마 간의 무결성을 유지**하고 **테스트 환경에서 문제를 빠르게 발견**해야 한다.

>출처
>[https://hstory0208.tistory.com/entry/JPA-데이터베이스-초기화-전략-ddl-auto-옵션](https://hstory0208.tistory.com/entry/JPA-%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4-%EC%B4%88%EA%B8%B0%ED%99%94-%EC%A0%84%EB%9E%B5-ddl-auto-%EC%98%B5%EC%85%98) [< Hyun / Log >:티스토리]