# 네트워크
**네트워크란?**
- 무엇을 연결하는 것 

**컴퓨터 관점**
- 한 장비에서 다른 장비를 통신매체로 연결해서 정보나 자료를 전달하는 조직이나 망을 의미
- 정보와 자료, 자원의 공유가 훨씬 편해졌다

## LAN(Local Area Network)
- 근거리 통신망
- 한정된 좁은 지역에 구성된 네트워크(PC방, 사무실, 연구소 등)
- 초기 투자 비용이 높지만 유지 보수비는 낮다
- 관리는 사내 관리자가 담당
- LAN 구성 장비로는 Switch(Bridge), HUB 등이 있다.
## WAN(Wide Area Network)
- 원거리 통신망
- 넓은 지역을 연결하는 네트워크(LAN과 LAN을 서로 연결하는 광역 네트워크)
- 초기 투자 비용이 낮지만 유지 보수비가 LAN 보다 높다.
- 관리는 ISP 업체가 담당한다.
- WAN 구성 장비로는 Router가 있다.

## 네트워크 프로토콜(Network protocol)
- 서로 다른 네트워크가 통신을 하기 위한 언어 혹은 약속
- 네트워크 상에는 많은 규칙이 존재하는데 서로 연결된 네트워크는 같은 규칙을 사용해야 한다
- 대표적인 Network protocol 로 인터넷 환경에서 데이터를 전송하는 TCP/IP가 있다

**기타 네트워크 구성 protocol**
- LAN 구성 프로토콜 : Ethernet, Token Ring, FDDI, 무선
- WAN 구성 프로토콜 : SLIP, HDLC, PPP,Fraame-Relay 등

> 통신 protocol이란 연결된 네트워크 간에 서로 통신을 하기 위한 통신 규약, 서로간의 약속, 표준 규격

## 통신방식에 따른 네트워크 분류
### 유니캐스트  (Unicast)
- 일 대 일 전달 방식
- 수신 측이 한 곳으로 정해져 있는 경우
Destination IP address : 192.168.1.1
Destination MAC address : 00-12-65-32-3A-CC

### 브로드캐스트 (Broadcast)
- 일 대 전체 전달 방식
- 불특정 다수에게 전부 전송하는 경우
Destination IP address : 255.255.255.255
Destination MAC address : FF-FF-FF-FF-FF-FF
- 동일 Network에 연결된 모든 네트워크 장비에게 보내는 통신 
(즉, Broadcast Domain안의 모든 장비들에게 전송)

### 멀티캐스트 (Multicast)
- 일 대 그룹 전달 방식
- 정해진 특정 그룹으로 전송하는 경우
Destination IP address : 224.0.0.5
Destination MAC address : 01-00-5E-00-00-02
- 특정 다수에게 전송하는 방식 ex) 케이블 TV, 인터넷 방송

# 네트워크 주소 체계
- 각 장비들은 정확한 통신을 위해 네트워크 상에서 서로 구분 해야한다
- 이 역할을 하는 것이 바로 MAC(Media Access Control) address
- TCP/IP Protocol을 사용하는 네트워크에서는 IP address를 사용하여 통신하지만 최종적으로 MAC address를 사용하여 데이터를 전달
- 네트워크 장비의 인터페이스는 고유의 MAC address를 가지고 있다

### MAC(Media Access Control) address
- 네트워크에 연결된 장비들이 가지는 48bit(6 Octet)의 고유한 주소
- Physical address, 즉 물리적 주소라고 부른다
- 이진수로 48bit인 주소이지만 16진수로 표현
- 이진수 4개를 묶어 16진수 한 자리로 표현한다

>EX)
>0000 0000.0110 0000.1001 0111.1000 1111.0100 1111.1000 0110
>↓
>00-60-97-8F-4F-86
>00:60:97:8F:4F:86
>0060.978F.4F86

- 앞의 24bits (6개의 16진수)는 생산자(생산 회사)를 나타내는 코드로 OUI라고 한다
- OUI를 보면 어느 회사에서 생산했는지 알 수 있다
- 뒤의 24bits 는 회사에서 각 장비에 분배하는 Host identifier
즉, 시리얼 넘버이다

### IP(Internet Protocol) address
- 이진수 32bit 로 구성된 주소체계
- 8bit 씩 4 octet 로 구분, 각 octet을 10진수로 변환해서 표현한다
>EX)
>11000000.10101000.00000000.00000001
>-> 192.168.0.1

- Logical address(논리적 주소)라고 부른다




