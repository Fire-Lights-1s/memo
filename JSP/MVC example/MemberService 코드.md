## MemberService
- DAO와 DTO는 따로 작성 해야함
- DAO는 DB와 연결하는 클래스
- DTO는 객체 모델
```java
public class MemberService {
	public boolean insertMember(HttpServletRequest request) {
		boolean result = false;
		System.out.println("MemberService inserMember()");
		
		MemberDTO member = new MemberDTO();
		member.setId(request.getParameter("id"));
		member.setPass(request.getParameter("pass"));
		member.setName(request.getParameter("name"));
		member.setDate(new Timestamp(System.currentTimeMillis()));
		
		MemberDAO memberDAO = new MemberDAO();
		try {
			memberDAO.insertMember(member);
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("유저 생성 실패");
			result = false;
		}
		return result;
	}// insertMember()
}
```