## HTTP란?
HTTP란 HyperText(HTML) Transfer Protocol의 약자로 **웹 상에서 네트워크로 서버끼리 통신을 할 때 어떠한 형식으로 서로 통신을 하자고 규정해 놓은 "통신 형식" 혹은 "통신 구조"**를 말한다.

HTTP의 통신 방식은 클라이언트 측에서 요청(Request)을 보내면 서버에서 응답(Response)을 하는 방식이다.

HTTP는 Stateless이고 State(상태)를 저장하지 않는다. www.naver.com을 요청했고 새로고침 해서 www.naver.com을 다시 요청 했다면 둘은 각각 독립적인 요청과 응답이다.

**HTTP Requset**와 **HTTP Response**는 비슷한 구조이지만 내용에 차이가 있다.

### HTTP 메시지의 구조
- start Line
- header
- empty Line
- body
### HTTP Requset
#### 1. start line
어떤 요청을 하는지에 대한 내용을 기술한다
- HTTP METHOD: `GET`, `POST`, `PATCH`, `PUT`, `DELETE`와 같은 http method를 적는 부분이다. Server에서 요청을 Routing할때 사용되며 주로 각각의 Method는 차례대로 `READ` `CREATE` `UPDATE(part)` `UPDATE(all)` `DELETE`를 의미한다.
- URL: 리소스를 요청하는 하는 주소를 의미한다. 
- protocol version: http의 버전을 의미한다. HTTP/1.1을 가장 많이 사용하며 최근에는 HTTP/2의 사용이 늘고 있다.
#### 2. header
Header는 `요청에 대한 정보`, `응답에 대한 요청`, `인증 정보`, `접속 정보`등 요청에서 필요한 다양한 요소를 담을 수 있는 공간이고 key, value로 구성되어 있다
- **Content-Type**: Body에 들어가는 요청 전문의 Type을 의미한다.
- **Accept**: 응답 받을 메세지 타입을 명시한다.
- **connection** : 주로 keep-alive로 셋업 한다. keep-alive로 셋업 하면 매 요청 시 커넥션을 다시 맺지 않고 커넥션을 유지하기 때문에 성능 향상을 기대할 수 있다.
- **User-Agent**: 사용자의 기기를 식별할 수 있다. **기기/OS/브라우저 별 예외를 처리**할 때 많이 사용되며 사용자 통계를 수집하기 위해서도 사용된다.
- **Authorization**: 인증 정보를 담을때 사용하는 Header이다. 주로 인증 토큰을 Authorization Header에 담아보낸다.
- **Cookie**: 개인 브라우저에 저장되는 Cookie 정보 보낼 때 사용하는 Header이다.
- **session-id**: Session에 대한 id값을 지정하는 부분이다.
#### 3.empty-line
Header 와 body를 구분하는 부분이다.
#### 4. body
메세지 전문이 들어간다. 모든 요청에 본문이 들어가지는 않는다. `GET`, `HEAD`, `DELETE` , `OPTIONS`처럼 리소스를 가져오는 요청은 보통 본문이 필요가 없다. 일부 요청은 업데이트를 하기 위해 서버에 데이터를 전송한다. 보통 (HTML 폼 데이터를 포함하는) `POST` 요청일 경우에 그렇다. Header의 `Content-Type`과 꼭 Type을 맞춰줘야 한다.  

### HTTP Response
#### 1. start-line
응답의 start-line에는 `protocol version`, `Http Status code`와 `Http Status`가 담겨있다.  
`Http Status`는 요청에 대한 응답 상태로 200번대는 성공, 400번대는 사용자 요청 에러, 500번대는 서버에러를 의미한다.  

#### 2. header
요청과 유사하나 서버에 대한 정보를 건내 준다. 사용자의 cookie나 session을 초기화 하는데도 이용된다.

empty-line과 body는 요청과 동일

>출처
>https://jminc00.tistory.com/24
>https://velog.io/@couchcoding/HTTP%EC%97%90-%EB%8C%80%ED%95%B4%EC%84%9C-%EC%95%8C%EC%95%84%EB%B3%B4%EC%9E%90%EC%A0%95%EC%9D%98%EC%99%80-%EA%B5%AC%EC%A1%B0
>https://developer.mozilla.org/ko/docs/Web/HTTP/Messages