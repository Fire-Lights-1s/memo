## Spring framework
- 자바로 만든 프레임워크
- 전자정부표준 프레임워크
- 장정
	- 코드 간결(경량 프레임워크)
	- 기본 자바 이해
	- 개발도구
	- 개발생산성 높아짐

### 개념
1. MVC
2. 객체생성방식(수정최소화, 반복 줄여줌) : 의존관계주입(DI), 제어의역전(IoC)
3. JDBC (마이바티스)
4. 파일업로드 , ajax(rest서비스)
5. AOP

### 톰캣 9버전 설치
https://tomcat.apache.org/download-90.cgi
32-bit/64-bit Windows Service Installer
apache-tomcat-9.0.91.exe  설치

### 설치
1) 기존이클립스 + Eclipse Marketplace 
spring 검색
Spring Tool => install
2) Spring Tool 다운받아서 압축풀어서 사용
spring.io
https://github.com/spring-attic/toolsuite-distribution/wiki/Spring-Tool-Suite-3

Spring Tool Suite 3.9.18
다운로드
https://download.springsource.com/release/STS/3.9.18.RELEASE/dist/e4.21/spring-tool-suite-3.9.18.RELEASE-e4.21.0-win32-x86_64.zip
c:드라이버  압축풀기
C:\sts-bundle\sts-3.9.18.RELEASE
STS.exe 바탕화면으로 바로가기 만들기 => 프로그램

D:workspace_sts 폴더 만들기 => 작업파일 저장공간

### 환경설정
1) 한글처리 => 세계표준 한글 설정 UTF-8
   >작업공간 한글처리 
Window - Preferences- General -  Workspace -
Text file encoding - Other - UTF-8

   >파일 한글처리
Window - Preferences- Web
CSS Files - ISO 10646/Unicode(UTF-8)
HTML Files- ISO 10646/Unicode(UTF-8)
JSP Files- ISO 10646/Unicode(UTF-8)
Apply and Close

2) 서버 가져오기
>File - New - Other - Server - Server -Next
Apache - Tomcat 9.0 - Next
Name : Apache Tomcat v9.0
directory :
C:\Program Files\Apache Software Foundation\Tomcat 9.0
Next - Finish

3) 프로젝트 만들기(작업공간,폴더)
>File - New -Other -  Spring - Spring Legacy Project -Next
Project  name : SpringProject
Spring MVC Project 선택
Next 
패키지  com.itwillbs.myweb
Finish

4) 프로젝트와 서버 연결
>Servers 탭 - Tomcat Server 오른쪽버튼 - Add and Remove
프로젝트  선택 - Add
Finish
