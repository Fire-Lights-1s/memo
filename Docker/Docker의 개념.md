# Docker
Docker란 Go언어로 작성된 리눅스 **컨테이너 기반**으로하는 **오픈소스 가상화 플랫폼**이다.

현재 Docker 0.9버전 부터는 직접 개발한 libcontainer 컨테이너를 사용하고 있다.

## 가상화를 사용하는 이유는?

이제는 향상된 컴퓨터의 성능을 더욱 효율적으로 사용하기 위해 가상화 기술이 많이 등장하였다.

서버 관리자 입장에서 CPU사용률이 10%대 밖에 되지 않는 활용도가 낮은 서버들은 리소스 낭비일 수밖에 없다.  그렇다고 모든 서비스를 한 서버안에 올린다면 안정성에 문제가 생길수도 있다. 
그래서 안정성을 높이며 리소스도 최대한 활용할 수 있는 방법으로 나타난게 서버 가상화입니다.


> 출처
> https://khj93.tistory.com/entry/Docker-Docker-%EA%B0%9C%EB%85%90