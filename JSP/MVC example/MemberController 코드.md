## MemberController 
- servlet 을 가져와서 상속 받아서 servlet 파일이 됨
- HttpServlet 서블릿(처리담당자) 동작원리 => service(), doGet(), doPost() 메서드 자동으로 호출
- doGet(), doPost() 재정의 => 메서드 오버라이딩
- 프로젝트를 우클릭해서 Run as로 실행해서 url에 ' .me '로 끝나는 주소를 입력하면 콘솔에 출력이 나온다 
- 예시 주소 : http://localhost:8080/MVCProject/insert.me
```java
package com.itwillbs.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		...
	}
}
```
- 가상 주소 가져오기  /insert.me
- 뽑아온 주소와 예상하는 가상 주소 비교
- 가상 주소를 유지하면서 이동
- 현재 클래스에선 가상 주소 매핑만 하면서 실제 기능을 하는 파일로 넘김
```java
protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("doProcess");
	String sPath = request.getServletPath();
	System.out.println("가상 주소 : " + sPath);
	if(sPath.equals("/insert.me")) {
		// 가상주소 => 실제 주소 연결  (member/insert.jsp) 이동
		// 이동방식
		// 1) 하이퍼링크(웹방식) : 주소가 변경되면서 이동 
		//response.sendRedirect("member/insert.jsp");
		// 2) 주소가 변경없이 이동(request, response 가지고 이동) 
		// => 자바파일 이용해서 이동
		// 자바파일 객체생성
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/insert.jsp");
		// 메서드 호출
		dispatcher.forward(request, response);
	}//if insert.me
	if(sPath.equals("/insertPro.me")) {
		System.out.println("insertPro.me 가상 주소 일치 ");
		MemberService memberService = new MemberService();
		response.sendRedirect("insert.me");
		// MemberService 클래스에서 실제 DB 연결하는 클래스로 정보를 넘김 
		if(memberService.insertMember(request)) {
			response.sendRedirect("login.me");
		}
	}//if insertPro.me

	if(sPath.equals("/login.me")) {
		System.out.println("login.me 가상 주소 일치 ");
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/login.jsp");
	}
}//doProcess()
```