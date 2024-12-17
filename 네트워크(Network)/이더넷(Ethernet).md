# 이더넷(Ethernet)
- LAN 구간에서 사용되는 네트워킹 방식 중 하나
- 다른 방식으로는 Token ring, FDDI 방식이 있다
- LAN에서 사용되는 protocol, 1980년에 DEC, 인텔, 제록스가 공동 개발한 Ethernet 1을 기반으로 1985년 IEEE에서 IEEE 802.3이라는 표준을 발표
- 네트워크 방식에 맞춰서 네트워크 장비들을 구입해야 한다
(우리나라의 경우 90% 이상이 Ethernet 방식으로 네트워킹)

## CSMA/CD
- Ethernet의 가장 큰 특징은 CSMA/CD 방식으로 통신한다는 것
- Ethernet이 Frame을 전송하는 방식은 full-duplex와 half-duplex에 따라 다르다
- CSMA/CD는 half-duplex로 동작하는 링크에서 Ethernet이 Frame을 전송하는 방식
	1. 호스트가 Frame을 전송하기 전에 네트워크 상에 다른 Frame이 전송되는지 확인 -> Carrier Sense (네트워크 신호가 있는지 감지)
	2. Ethernet에 연결된 장비들은 네트워크 상에 Frame의 흐름이 없을 때 서로 동시에 Frame을 전송할 수 있다 -> Multiple Access(다중 접근)
	3. Ethernet은 복수의 장비가 동시에 Frame을 전송할 수 있고, 이 경우 충돌이 일어날 수 있기 때문에 전송 후 충돌 발생 여부를 확인 -> Collision Detection(충돌 감지)
- Carrier Sense Multiple Access/Collision Detection
- 충돌이 발생하면 Frame을 전송한 장비들은 서로 랜덤한 시간을 대기했다 다시 재전송
- half-duplex 네트워크에서는 데이터 전송량이 많을 때 Frame 충돌이 많이 발생한다
- Ethernet 장비들은 충돌 발생 시 최대 15회까지 재전송을 시도, 그래도 실패하면 Frame 전송을 포기한다

- full duplex로 동작하는 링크는 Frame의 송신과 수신이 서로 다른 채널을 통해 이루어지기 때문에 충돌이 일어나지 않는다
- 때문에 충돌 감지도 하지 않는다
> full duplex 모드에서 Ethernet 동작 방식이 CSMA/CD가 아니다
- 송수신 트래픽 양이 돌일하다면 half duplex 보다 full duplex 속도가 2배 더 빠르다

## 이더넷 Frame 구조
![이더넷 Frame 구조](<./images/이더넷_Frame_구조.png>)
1. Preamble
	- Frame 전송의 시작을 나타내는 필드, 10101010이 반복되는 7byte 길이의 필드
	- 수신 측에 Frame이 전송된다는 것을 알리고 0과 1을 제대로 구분할 수 있게 Synchronization(동기)신호를 제공하는 역할( 때문에 clock을 사용하지 않는다)
2. SOF(SFD)
	- 10101011의 값을 가지며 Frame의 시작을 알리는데 사용
	>Ethernet Frame 크기를 나타낼 때 Preamble과 SOF를 합친 8 byte는 제외
3.  목적지 주소
	- Destination MAC address 즉, 수신지의 MAC address
4. 출발지 주소
	- Source MAC address 즉, 출발지의 MAC address
5. 타입 or 길이
	- 상위 계층 
