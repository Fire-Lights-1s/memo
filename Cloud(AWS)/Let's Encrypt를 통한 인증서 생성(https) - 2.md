# Lets's Encrypt를 통한 SSL/TLS 인증서 생성
1. https://letsencrypt.org
- 인증 절차가 단순해서 단 한 줄 명령어로 인증서 발급 가능
- 발급 대시 시간이 없어 바로 발급
- nginx, apache 와 같은 웹 서버에 맞추어 자동 옵션이 설정되도록 설치 가능
- 인증 유효기간 90일(자동으로 인증 갱신 가능)
- 무료!!!

2. AWS 로그인 -> EC2 대시보드 -> EC2 인스턴스 생성(인스턴스 시작)
- 이름 : SecureWeb
- AMI : Amazon Linux 2 AMI(HVM) - Kernel 5.10
- 인스턴스 유형 : t2.micro
- 키페어 : 기존 것 사용
- 기존 보안 그룹 선택 : ssh, web
- 스토리지 구성 : 기본값(8Gib)
=> 인스턴스 시작

3. MobXterm 원격 접속

4. 아파치 웹 서버 설정
```
sudo yum install -y httpd
sudo systemctl start httpd
sudo systemctl enable httpd
sudo vi /var/www/html/index.html
```

5. Certbot 설치
- Let's Encrypt 는 ACME 프로토콜을 사용하여 설정한 도메인명의 유효성을 확인하고 인증서를 발급
※ ACME(Automatic Certification Management Environment)
- Let's Encrypt CA(Certificate Authority)로 부터 SSL/TLS 인증서를 발급 받고 싶다면 ACME 클라이언트 소프트웨어를 하나 설치해야 함!
- Let's Encrypt 에서는 그 중 사용하기 편리한 Certbot의 사용을 권장함

```
sudo amazon-linux-extras install epel -y
sudo yum install -y certbot
certbot --version
certbot --help
```
=> 발급에 대한 옵션을 볼 수 있음
=> --nginx, --apache, --standalone, --webroot, --manual 등이 있음

6. Let's Encrypt SSL 인증서 발급
1) webroot
2) 사용하는 웹서버의 인증서 발급
3) Standalone
4) DNS 

2) --nginx 또는 --apache 옵션을 사용한 인증서 발급
- nginx나 아파치와 같은 웹 서버에서 직접 SSL 인증을 실시하고, 웹서버에 맞는 SSL 셋팅값 부여
- 발급이나 갱신을 위해 웹 서버를 중단시킬 필요가 없음
- 인증서 갱신 시 상황에 맞게 셋팅을 자동으로 업데이트

```
sudo mkdir -p /var/www/letsencrypt

sudo yum install -y python-certbot-apache

sudo certbot certonly --apache -d s31.itwillbs.com
```
-----------------------------------
```
Plugins selected: Authenticator apache, Installer apache
Enter email address (used for urgent renewal and security notices)
 (Enter 'c' to cancel): 본인 이메일 주소 입력 (ex. jskang@itwillbs.co.kr)
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
Please read the Terms of Service at
https://letsencrypt.org/documents/LE-SA-v1.4-April-3-2024.pdf. You must agree in
order to register with the ACME server. Do you agree?
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
(Y)es/(N)o: y 입력 엔터
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
Would you be willing, once your first certificate is successfully issued, to
share your email address with the Electronic Frontier Foundation, a founding
partner of the Let's Encrypt project and the non-profit organization that
develops Certbot? We'd like to send you email about our work encrypting the web,
EFF news, campaigns, and ways to support digital freedom.
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
(Y)es/(N)o: y 입력 엔터
Account registered.
Requesting a certificate for s31.itwillbs.com
Performing the following challenges:
http-01 challenge for s31.itwillbs.com
Cleaning up challenges
Unable to find a virtual host listening on port 80 which is currently needed for Certbot to prove to the CA that you control your domain. Please add a virtual host for port 80.
```
=> 오류 메시지가 발생!
=> virtual host 가 필요하다!

```
sudo vi /etc/httpd/conf/httpd.conf
```

```
...
364 <VirtualHost *:80>
365     DocumentRoot /var/www/html
366     ServerName itwillbs.com
367     ServerAlias s31.itwillbs.com
368 </VirtualHost>
```
=> 마지막 라인에 추가, esc -> :wq 빠져나옴

인증서 발급을 다시 진행
```
sudo certbot certonly --apache -d s31.itwillbs.com
```









