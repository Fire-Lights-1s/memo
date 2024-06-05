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

​