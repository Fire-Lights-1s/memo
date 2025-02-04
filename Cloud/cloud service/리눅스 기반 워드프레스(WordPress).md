### 리눅스 기반 워드프레스(WordPress)
- 워드프레스는 홈페이지를 쉽게 제작할 수 있는 환경
- 웹 서비스 프로그램, 데이터베이스 서버, PHP 프로그램 언어 필요
=> 웹 서비스 프로그램 : Aapche
=> 데이터베이스 서버 : mariadb
=> 언어 : PHP

#### 시작 전 확인
1. 프로그램 설치 확인
`rpm -qa httpd php mariadb-server`
=> 아무런 내용이 나오지 않으면 설치가 안된 상태

2. 가상머신(VM)의 IP주소 확인
=> enp0s3 : 10.0.2.15/24

---
#### 워드 프레스 설치
1. 가상머신 업데이트
`yum -y update`

2. 워드프레스를 동작하기 위한 프로그램(=패키지) 설치
`yum -y install httpd php php-mysqlnd mariadb-server`

3. 패키지 설치 확인
방향키(↑)를 눌러 rpm -qa httpd php mariadb-server를 찾아서 다시 실행

4. 아파치 웹 서비스 프로그램 동작 확인
`systemctl status httpd`
=> Active : inactive(dead)

5. 아파치 웹 서비스 프로그램 시작
`systemctl start httpd`

---
#### 리눅스 웹 설정
`cd /var/www/html`
cd : change directory => 디렉터리(폴더) 변경

`ls`
※ s : list, 현재 위치한 디렉터리(폴더)의 파일 확인

`gedit index.html`
(= gedit /var/www/html/index.html)
=> GUI 환경에서는 문서편집기 gedit를 사용하여 index.html 생성
=> 간단한 내용을 입력
=> 만약, TUI 환경이라면 'vi', 'nano'를 이용해서 생성

#### 웹페이지 확인
프로그램 -> 즐겨찾기 -> Firefox -> 주소창에 localhost 또는 127.0.0.1 입력

#### 윈도우에서 웹 동작 확인
- 윈도우 -> 리눅스 웹 서버로 페이지를 요청하는 경우 리눅스 서버의 http 서비스가 허용(permit) 되어 있어야 함
터미널 -> firewall-config -> 방화벽 설정창
=> 설정 : 런타임 -> 영구적
=> 서비스 : □http
=> 옵션탭 -> Firewalld다시 불러오기 클릭

```
systemctl list-unit-files
```
=> 리눅스 시작 시 자동 실행 여부를 확인
=> 방향키(↑↓) 또는 PgUp, PgDn 키를 이용
=> 빠져나갈 때는 'q'

#### 데이베이스 동작
```
systemctl status mariadb
```
=> 마리아DB 동작 확인
=> Active : inactive(dead)

```
systemctl start mariadb
```
=> 데이터베이스(마리아DB) 시작

```
systemctl enable mariadb
```
=> 아파치와 마찬가지로 마리아DB도 리눅스 시작 시 자동으로 동작

#### PHP 언어 동작 확인
```
cd /var/www/html
gedit phpinfo.php

```
------------------------
```
<?php
    phpinfo();
?>
```
------------------------

systemctl restart httpd
=> 웹과 관련된 설정이 변경되면 웹 서비스 재시작

윈도우 웹 브라우저 주소창에
어탭터 브리지 -> 192.168.1.x/phpinfo.php 접속
NAT -> localhost:8000/phpinfo.php 접속 또는 127.0.0.1:8000/phpinfo.php
호스트 전용 어댑터 -> 192.168.56.x/phpinfo.php 접속
=> PHP Version ... 내용이 나오면 서버가 PHP 언어를 사용할 수 있는 환경을 갖춤

---
#### 워드프레스가 사용할 데이터베이스 설정
```
mysql -uroot -p
Enter password: (엔터)
```
=> root는 마리아DB 관리자 계정. 패스워드를 설정한 적이 없으므로 엔터키를 입력

```sql
show databases;
```
=> 마리아DB의 데이터베이스 이름 확인
=> Database는 schema 라고 하며 내부에 0개 이상의 테이블을 가지고 있음.

```sql
create database wpDB;
```
=> 워드프레스가 사용할 데이터베이스 wpDB를 생성

```sql
show databases;
```
=> 생성된 wpDB가 보임

<grant 명령어 기본 문법 >
```sql
grant [권한] on [사용할 데이터베이스명] to [유저명] identified by [비밀번호]
```
※ grant : 권한을 설정하는 명령어(<-> revoke)
※ 권한(CRUD) : Create(만들고), Read(읽고, select), Update(수정하고), Delete(삭제)

```sql
grant all privileges on wpDB.* to wpUser@localhost identified by '1234';
```
=> wpDB를 워드프레스가 사용할 수 있도록 설정
=> 1234 패스워드를 가진 wpUser 유저를 생성. wpUser는 wpDB의 모든 테이블에 대해서 읽고, 쓰고, 수정, 삭제하는 권한을 가짐

```
flush privileges;
```
=> 사용자와 관련된 설정 시 적용을 시키는 명령어

```
exit
```
=> 명령어로 데이터베이스 종료

---
#### 워드프레스 설치
```
wget https://ko.wordpress.org/wordpress-4.9.6-ko_KR.tar.gz
```
=> 인터넷을 통해 파일을 다운로드 받을 때 wget 명령어 사용
=> 확장자명이 .tar.gz 은 압축된 파일

```
tar xvfz wordpress-4.9.6-ko_KR.tar.gz
```
=> tar xvfz w[Tab키]
=> 압축 해제

압축 해제를 하면 'wordpress' 라는 폴더(디렉터리)가 생김

```
chown -R apache.apache wordpress
```
※ chown : change owner 소유자를 변경하는 명령어
-R : wordpress 내의 모든 디렉터리(폴더)와 파일들의 소유자를 변경하는 옵션
=> -R을 쓰지 않으면 wordpress 디렉터리(폴더)에만 적용. wordpress 아래(안)의 다른 디렉터리 및 파일에 대해서는 변경되지 않음

외부(윈도우 등)에서 웹 브라우저를 통해서 wordpress 디렉터리(폴더)에 접근할 경우
wordpress 디렉터리(폴더)는 apache 소유여야함!

```
chmod 777 wordpress
```
※ chmod : change modify

[허가권 permision]
=> 리눅스 운영체제는 파일에 대해서 소유자가 존재하고 유저에대한 접근 권한이 존재
=> 접근 권한 : r(읽기), w(쓰기), x(실행)
=> 3개씩 끊어서 소유자/소유그룹/그밖의사람 구성.
=> rwxr-xr-x : 소유자에 대해서 읽고 쓰고 실행, 소유그룹 읽기 실행, 그밖의 사람 읽기 실행 권한이 부여됨
=> 만약, 소유자만 읽고 쓰고 실행하고 싶다면
     chmod 700 [파일명 또는 디렉터리명]
     => rwx------

또 다른 설정방법으로 소유자(u), 소유그룹(g), 그밖의 자(o) 에 대해서
+ 또는 - 기호를 사용하여 읽기(r), 쓰기(w), 실행(x) 권한을 부여 가능
ex) rwxr-xr-x 에서 r-xr-xr-x 로 변경하려면 즉, 사용자에게 쓰기 권한을 빼려면
     chmod u-w [파일명]
ex) 모든 사용자에게 w권한을 부여할 때는 chmod a+w [파일명]
ex) 모든 사용자에게 읽고, 쓰고, 실행하는 권한을 부여하려면 chmod a+rwx [파일명]

#### [[WordPress 설정|워드프레스 설정]]



