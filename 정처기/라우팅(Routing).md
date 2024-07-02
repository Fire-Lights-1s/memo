- IGP(Interior Gateway Protocol) : 하나의 AS(Autonomous System) 내 라우팅 정보 교환
	> RIP(Routing Information Protocol) 
		>- **거리 벡터 라우팅 프로토콜** / 최대 15홉 지원
		>- 최단 경로 탐색으로 **Bellman-Ford 알고리즘** 사용
		>- EGP 보다는 IGP에 해당 / **소규모 네트워크 환경** 적합

	> OSPF(Open Shortest Path First Protocol)
		>- RIP 단점 개선 목적 / **대규모 네트워크**에 널리 사용
		>- 실시간 노드 간 거리, 링크 상태 반영
		>- **다익스트라(Dijkstra) 알고리즘** 사용
- EGP(Exterior Gateway Protocol) : 서로 다른 AS(Autonomous System) 간 라우팅 정보 교환
	> BGP(Border Gateway Protocol)
		>- **대규모 네트워크** 적합/ Path Vector 기반 라우팅
		>- **다양한 경로 속성을 고려**한 최적의 경로 결정