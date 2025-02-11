# OSI 7 Layer 모델과 TCP/IP 모델

## OSI 7 Layer 모델
국제 표준화 기구(ISO)가 1984년에 발표한 OSI 7 Layer는 통신이 일어나는 과정을 7단계로 구분해서 한 눈에 들어올 수 있도록 보여준다

> 컴퓨터 통신 구조의 모델과 앞으로 개발될  프로토콜의 표준적인 뼈대를 제공하기 위해서 개발된 참조 모델

## TCP/IP 모델
미국에서 개발한 인터넷의 기본 통신 프로토콜, DOD Model(미국방성 모델)을 기반으로 개발

>TCP : 연결지향형 프로토콜, 세션의 연결과 종료, 흐름제어, 패킷의 분할 및  재조립
>IP : 비 연결지향형 프로토콜, 데이터 전송

>OSI 7 Layer는 장비 개발자들이 어떻게 표준을 잡을지 결정할 때 사용하고 TCP/IP 는 실질적으로 사용되는 프로토콜이다

# OSI 7 Layer

## Application Layers
- Application 
	- 사용자 인터페이스
- Presentation
	- 데이터 변환, 복구
	- 암호화, 복호화
- Session
	- 응용 프로그램 구분, 세션 성립, 유지, 종료

## Data Flow Layers
- Transport
	- 신뢰성 있는 혹은 신뢰성 없는 전달
	- 에러 수정 후 재전송
- Network
	- Router가 경로 결정에 사용할 논리적 어드레싱 제공
- Data Link
	- Bit를 Byte로, Byte를 Frame으로 결합
	- MAC address를 사용하여 Access
	- Error를 탐지하지만 복구는 하지 않음
- Physical
	- 장비들 사이에서의 bit이동
	- 전압, 전선 속도, 핀 아웃 케이블 명시
