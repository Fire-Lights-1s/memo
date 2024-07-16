### MVC(Model View Controller)패턴
- Model : class는 데이터 json, xml 담당
- View : JSP는 ui만 담당
- Controller : Servlet은 로직 담당

1. 데이터베이스에 CRUD 작업은 model 에 담아서 처리.
※삭제(delete)는 예외가 있음.
2. 반드시 **Controller(서블릿)을 통해서 View(jsp)에 접근**해야 함.
⇒ 모든 링크, action, location.href, request.sendRedirect 에 servlet url 을 먼저 사용.
3. model 을 목록으로 출력할 때는, ArrayList 에 model 을 추가해 jsp로 전달.
⇒ jsp에서 for문으로 처리. 
#### 모델2방식,MVC (Model  View Controller) 장점
>- 자바 메서드 정의 : 코드 재사용, 코드 간결화, 코드 수정 작업 줄임 장점
>- 여러명이 동시에 작업을 하는 프로그램 방식(패턴) , 작업 분리
>- 가상 주소(보안 상 좋아짐)

​1단계 : JSP파일 (Model + View  + Controller)
insert.jsp => insertPro.jsp => login.jsp => loginPro.jsp

2단계 : JSP파일 (View + Controller) + JAVA파일(Model)
insert.jsp => insertPro.jsp <-> DB작업 JAVA파일 메서드 호출()
=> login.jsp => loginPro.jsp <-> DB작업 JAVA파일 메서드 호출()

3단계 : JSP파일 (View) + JAVA파일(Controller) +  JAVA파일(Model)
insert.jsp => 처리 작업 JAVA파일 메서드 호출() <-> DB작업 JAVA파일 메서드 호출()
=> login.jsp => 처리 작업 JAVA파일 메서드 호출() <-> DB작업  JAVA파일 메서드 호출()

>주소 매핑 : 웹페이지 이동을 위해서 가상으로 주소 => 실제 페이지 이동
>1. 가상 주소 insert.me => 실제 페이지 insert.jsp 이동 => 하이퍼 링크 insertPro.me
>2. 가상 주소 insertPro.me => 처리 작업 JAVA파일 메서드 호출() <-> DB작업 JAVA파일 메서드 호출()=> 하이퍼 링크 login.me
>3. 가상 주소 login.me => 실제 페이지 login.jsp 이동 => 하이퍼 링크 loginPro.me
>4. 가상 주소 loginPro.me => 처리 작업 JAVA파일 메서드 호출() <-> DB작업 JAVA파일 메서드 호출() =>하이퍼 링크 main.me


