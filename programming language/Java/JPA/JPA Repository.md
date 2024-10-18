# JPARepository

package: org.springframework.data.jpa.repository

  

> JPARepository란

Spring Data JPA에서 제공하는 인터페이스 중 하나로, JPA를 사용하여 데이터베이스를 조작하기 위한 메서드들을 제공합니다.

JPARepository 인터페이스를 상속받는 인터페이스를 정의하면, 해당 인터페이스를 구현하는 클래스는 JPA에서 제공하는 메서드들을 사용할 수 있습니다.

데이터베이스의 추가, 조회, 수정, 삭제의 findAll(), findById(), save() 등의 메서드들을 사용할 수 있습니다. 제공되는 메서드들 이용하여 쉽고 간편하게 CRUD 조작을 할 수 있습니다.  
즉, JpaRepository를 사용하면, 복잡한 JDBC(Java DataBase Connectivity) 코드를 작성하지 않아도 간단하게 DB와의 데이터 접근 작업을 처리할 수 있습니다.

JPARepository 인터페이스는 제네릭 타입을 사용하여 Entity클래스와 복합키를 사용하고 있다면 해당 Entity의 ID클래스를 명시합니다. 이를 통해 해당 인터페이스를 상속받는 구현체는 Entity클래스와 ID클래스에 대한 정보를 알고 있어서, 런타임 시점에 적절한 쿼리를 생성하고 실행할 수 있습니다.

### JPARepository Method
>지원하는 기본 메서드
- `save(Entity)` : 엔티티 저장 및 수정
- `void delete(Entity)` : 엔티티 삭제
- `count` : 엔티티 총 개수 반환
- `List<Entity> findAll()` : 모든 엔티티 조회
- `Entity findById(id)` : id에 대한 엔티티 조회

>쿼리 메서드 정의

쿼리 메소드는 Repository 인터페이스에 간단한 네이밍 룰을 이용하여 메소드를 작성하면 원하는 쿼리를  실행할 수 있습니다.

>아이디 비밀번호 조회
- `findByIdAndPass(id, pass)` -> where id=? and pass=?
- `findByIdOrPass(id, pass)` -> where id=? or pass=?
- `findByNumBetween()` -> where num between ? and ?

|                    | method name                                             | 쿼리                                                             |
| ------------------ | ------------------------------------------------------- | -------------------------------------------------------------- |
| Distinct           | findDistinctByLastnameAndFirstname                      | select distinct …​ where x.lastname = ?1 and x.firstname = ?2  |
| And                | findByLastnameAndFirstname                              | … where x.lastname = ?1 and x.firstname = ?2                   |
| Or                 | findByLastnameOrFirstname                               | … where x.lastname = ?1 or x.firstname = ?2                    |
| Is, Equals         | findByFirstname,findByFirstnameIs,findByFirstnameEquals | … where x.firstname = ?1                                       |
| Between            | findByStartDateBetween                                  | … where x.startDate between ?1 and ?2                          |
| LessThan           | findByAgeLessThan                                       | … where x.age < ?1                                             |
| LessThanEqual      | findByAgeLessThanEqual                                  | … where x.age <= ?1                                            |
| GreaterThan        | findByAgeGreaterThan                                    | … where x.age > ?1                                             |
| GreaterThanEqual   | findByAgeGreaterThanEqual                               | … where x.age >= ?1                                            |
| After              | findByStartDateAfter                                    | … where x.startDate > ?1                                       |
| Before             | findByStartDateBefore                                   | … where x.startDate < ?1                                       |
| IsNull, Null       | findByAge(Is)Null                                       | … where x.age is null                                          |
| IsNotNull, NotNull | findByAge(Is)NotNull                                    | … where x.age not null                                         |
| Like               | findByFirstnameLike                                     | … where x.firstname like ?1                                    |
| NotLike            | findByFirstnameNotLike                                  | … where x.firstname not like ?1                                |
| StartingWith       | findByFirstnameStartingWith                             | … where x.firstname like ?1 (parameter bound with appended %)  |
| EndingWith         | findByFirstnameEndingWith                               | … where x.firstname like ?1 (parameter bound with prepended %) |
| Containing         | findByFirstnameContaining                               | … where x.firstname like ?1 (parameter bound wrapped in %)     |
| OrderBy            | findByAgeOrderByLastnameDesc                            | … where x.age = ?1 order by x.lastname desc                    |
| Not                | findByLastnameNot                                       | … where x.lastname <> ?1                                       |
| In                 | findByAgeIn(Collection<Age> ages)                       | … where x.age in ?1                                            |
| NotIn              | findByAgeNotIn(Collection<Age> ages)                    | … where x.age not in ?1                                        |
| True               | findByActiveTrue()                                      | … where x.active = true                                        |
| False              | findByActiveFalse()                                     | … where x.active = false                                       |
| IgnoreCase         | findByFirstnameIgnoreCase                               | … where UPPER(x.firstname) = UPPER(?1)                         |