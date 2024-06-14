### OwnCloud 클라우드 서비스
- 가상머신 초기화, 복원
- 터미널 -> ip add -> IP주소 확인
- 필요 요건
  1) 웹 서비스 프로그램
  2) PHP
  3) 데이터베이스
```
rpm -qa httpd php mariadb-server
```
=> 패키지 설치 확인
=> 아무런 내용이 나오지 않으면 패키지 설치가 되어 있지 않음!

```
yum install -y httpd mariadb-server php 
```
=> 아파치 웹 서비스, 마리아DB, PHP 관련 패키지 설치

```
systemctl start httpd
```
=> 아파치 웹 서비스 시작

```
systemctl enable httpd
```
=> 리눅스 재시작 시 자동 실행

```
systemctl status httpd
```
=> 웹 서비스 시작 확인

```
firewall-cmd --add-service=http --permanent
firewall-cmd --add-service=https --permanent
```
=> 방화벽에서 웹 서비스(http, https) 허용

```
firewall-cmd --reload
```
=> 변경된 내용을 적용하기 위해 방화벽 재시작

---
### OwnCloud 클라우드 설치
- /var/www/html/ 에서 설치
```
wget https://download.owncloud.com/server/owncloud-10.11.0.zip
```

ls
=> owncloud-10.11.0.zip (빨간색) 확인

```
unzip -q owncloud-10.11.0.zip
```
=> 압축해제
=> owncloud(파란색) 디렉터리가 생성

```
mkdir /var/www/html/owncloud/data
```
※ mkdir : make directory (윈도우의 '새폴더 생성'과 같은 개념)
=> 압축이 해제된 owncloud 안에 data 라는 디렉터리(폴더) 생성

```
chown -R apache.apache owncloud
```
=> chown: change owner 
=> -R : 하위 디렉터리(폴더)까지 모두 한 번에 변경

```
chmod -R 755 owncloud
```
=> 755 : rwxr-xr-x 
=> 소유자는 읽고,쓰고,실행 / 소유그룹 읽고,실행 / 그밖의 유저 읽고,실행

윈도우 웹 브라우저에 서버IP주소/owncloud 를 입력하면
This version of ownCloud requires at least PHP 7.3.0
You are currently running PHP 5.4.16. Please update your PHP version.
에러가 발생!!
=> PHP 버전이 낮아서 진행이 불가 
=> 현재 설치된 PHP를 삭제하고, PHP 7.3.0 버전 이상의 PHP 버전 설치 필요

---
#### [[php 버전 업데이트 설정 for linux|PHP 버전 업데이트]]

```
yum install -y php php-common php-fpm
```
=> 위의 명령어로 php 기본 버전을 7.3으로 등록
=> PHP 7.3버전 설치

```
yum install -y php-mysqlnd php-pecl-memcache php-pecl-memcached php-gd php-mbstring php-mcrypt php-xml php-pecl-apc php-cli php-pear php-pdo php-curl php-intl php-zip
```
=> PHP 7.3 버전 추가 설치

```
systemctl restart httpd
```
=> 변경된 PHP 버전을 적용하기 위해 웹 서비스 재시작

웹 브라우저로 다시 이동해서 F5(새로고침) owncloud 단어 보임!
=> PHP 버전 관련 문제는 해결

---
#### ownCloud 에서 사용할 DB 설정
```
systemctl start mariadb
```
=> 마리아DB 시작

```
mysql -uroot -p
```
엔터

```
CREATE DATABASE webDB;
```
=> owncloud가 사용할 데이터베이스 공간 생성

```
SHOW DATABASES;
```
=> webDB가 생성되었는지 확인

```
GRANT ALL ON webDB.* TO webUser@localhost IDENTIFIED BY '1234';
```
=> 암호 1234를 가지는 webUser에게 webDB 데이터베이스 공간의 모든 권한을 주겠다!

```
FLUSH PRIVILEGES;
```
=> 변경사항 적용

```
exit
```
=> 접속 해제

```
setenforce 0
```
=> selinux 해제

---
#### owncloud 기본 설정 

웹 브라우저 주소창에 "본인IP/owncloud" 접속 시 index.php 페이지가 보임

사용자 이름 : admin
암호 : 1

저장소 및 데이터베이스▼
- 데이터폴더 : /var/www/html/owncloud/data
  => 미리 설정해 놓은 디렉터리(폴더)
- 데이터베이스 설정 : SQLite -> MySQL/MariaDB 로 변경
  데이터베이스 사용자 : webUser
  데이터베이스 암호 : 1234
  데이터베이스 이름 : webDB
  데이터베이스 호스트 : localhost(기본값)

---
[ owncloud 접속 ]
사용자 : admin
암호 : 1

