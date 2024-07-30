## AWS EC2 인스턴스 원격(Remote) 접속
1. 원격 접속을 위해서는 원격 접속 프로그램 필요
=> 수업 시 설치한 MobaXterm 프로그램 사용

2. 인스턴스 -> 인스턴스 -> webServer 체크
=> 퍼블릭 IPv4 주소 복사
=> 왼쪽의 네모(네모) 버튼 클릭하면 복사됨

3. 다시 MobaXterm 으로 돌아옴
=> 왼쪽 상단의 Session -> SSH -> Remote Host 항목에 복사한 주소를 붙여넣기

4. 아래쪽의 Advanced SSH settings 탭 클릭
=> □Use private key 체크 -> 오른쪽 파일모양 버튼 클릭
=> AWS EC2 인스턴스 설치 중 생성한 키페어 파일을 선택
     (D:\class1_key.pem)

5. Bookmark settings 탭 클릭
=> Session name : 기본값으로 IP주소가 설정되어 있음
=> Session name을 webServer로 변경

6. 설정이 완료되면 [OK] 버튼 클릭. webServer를 더블 클릭
=> Accecpt 클릭
---
### 원격 접속
login as : ec2-user
=> 접속 완료
※ AWS EC2는 보안을 위해 root 사용자 접속을 하지 못하도록 설정이 되어 있음

