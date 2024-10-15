### root-context

- root-context에 등록되는 빈들은 모든 컨텍스트에서 사용할 수 있습니다. (공유 가능)
- service나 dao를 포함한, 웹 환경에 독립적인 빈들을 담아둡니다.
- 서로 다른 servlet-context에서 공유해야 하는 빈들을 등록해놓고 사용할 수 있습니다.
- **servlet-context 내 빈들은 이용이 불가능합니다.**

### servlet-context

- servlet-context에 등록되는 빈들은 해당 컨테스트에서만 사용할 수 있습니다.
- DispatcherServlet이 직접 사용하는 컨트롤러를 포함한 웹 관련 빈을 등록하는 데 사용합니다.
- **독자적인 컨텍스트들을 가지며, root-context 내 빈 사용이 가능합니다.**

>출저
>https://kingofbackend.tistory.com/77#article-1-0-4--%3Ccontext-param%3E-%ED%83%9C%EA%B7%B8-%EC%95%88%EC%97%90-%EC%9E%88%EB%8A%94-%EC%84%A4%EC%A0%95%EC%9D%B4-root-context-%EA%B4%80%EB%A0%A8%ED%95%9C-%EA%B2%83%EB%93%A4%EC%9D%B4%EA%B3%A0-%3Cservelet%3E-%ED%83%9C%EA%B7%B8-%EC%95%88%EC%97%90-%EC%9E%88%EB%8A%94-%EC%84%A4%EC%A0%95%EC%9D%B4-servlet-context-%EA%B4%80%EB%A0%A8-%EC%84%A4%EC%A0%95%EC%9E%85%EB%8B%88%EB%8B%A4-