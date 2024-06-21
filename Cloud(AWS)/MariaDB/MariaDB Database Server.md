### MariaDB Database Server
바탕화면 우클릭 -> 터미널 -> ip add
=> 가상 머신 IP 주소 확인 enp0s3 : 10.0.2.15 , enp0s8 : 192.168.56.103

```
rpm -qa maria*
```
=> 마리아DB 설치 확인
=> mariadb-libs-... 파일이 존재

```
yum list maria*
```
=> 마리아 DB 관련 파일을 미리 확인
=> 그 중 클라이언트용 mariadb 와 서비용 mariadb-server 패키지 필요

```
yum install -y mariadb mariadb-server
```
=> 서비스를 위해서 mariadb-server를 설치하고, DB서버에 접속하는 용도의 클라이언트 프로그램 mariadb 까지 설치

```
systemctl start mariadb
```
=> 마리아DB 시작

```
systemctl status mariadb
```
=> Active: active(running) 확인

```
systemctl enable mariadb
```
=> 리눅스 재시작 시 자동으로 데이터베이스가 동작하도록 설정

```
mysql -uroot
```
=> 마리아 DB 접속

```sql
show databases;
```
=> 데이터베이스(스키마) 확인

```
exit
```
=> 데이터베이스 종료

---
### 마리아 DB 초기 보안 설정

```
mysql_secure_installation
```

Enter current password for root (enter for none):
=> 현재 설정된 root의 비밀번호 입력(초기 시 설정값이 없으면 엔터키)

Set root password? [Y/n]
=> root 비밀번호를 설정, y를 입력
New password: 1234
Re-enter new password: 1234
※ 보안상 몇 글자를 입력하는지 화면에 보이지 않음!

Remove anonymous users? [Y/n]
=> 익명사용자를 삭제하겠습니까?
=> y 입력

Disallow root login remotely? [Y/n]
=> 관리자 계정인 root의 원격 접속을 거부하는 설정
=> 원격 접속을 거부시키려면 y를 입력하고, 허용하려면 n를 입력
=> root 원격 접속을 위해서는 우선 n을 입력
=> 추후 mysql_secure_installation 설정을 통해 root 원격 접속을 거부시킴

Remove test database and access to it? [Y/n]
=> 마리아DB 처음 설치하면 mysql, test 데이터베이스가 존재
=> test 데이터베이스를 굳이 쓰지 않으려면 삭제
=> y 

Reload privilege tables now? [Y/n]
=> 적용하려면 y 입력

※ 설정 도중 Ctrl + C를 입력하면 취소할 수 있음!!

---
#### DB 접속 시 주의할 점
```
mysql -uroot
```
=> ERROR 1045 (28000): Access denied for user 'root'@'localhost' (using password: NO)
=> 암호를 설정하여 에러가 발생

```
mysql -uroot -p1234
```
=> 접속 확인

```
show databases;
```
=> test 데이터베이스가 삭제된것을 확인

```
exit
```
=> 마리아 DB 종료

```
history
```
=> 입력된 명령어들이 보관되어 있는 곳
=> 기본 1000줄이 저장
=> history -d [라인번호] : 해당 라인번호 삭제
=> history -c : 전체 삭제

```
mysql -uroot -p(엔터)
```
Enter password: 1234
=> 마리아DB 접속 시 위의 방법으로 접속

```
exit
```
=> 마리아 DB 종료

---
### Mariadb 설정
설정파일 : /etc/my.cnf

```
vi /etc/my.cnf
```
=> 마리아DB를 처음 설치하면 생성되는 초기의 my.cnf 파일은 내용이 적음
=> 마리아DB에서 제공하는 샘플 설정 파일을 복사해서 원하는 내용을 수정해서 적용

샘플파일 : /usr/share/mysql

```
cd /usr/share/mysql
```
=> 샘플 파일이 위치한 디렉터리로 이동

```
ls
```
=> ls : List의 약자, 디렉터리 내의 파일 또는 하위 디렉터리(폴더) 보기
=> 파란색 디렉터리는 캐릭터넷(언어)
=> .sql : 스키마 파일(데이터베이스가 기본적으로 사용하는 구성 파일)
=> .cnf : 설정파일

```
my-small.cnf, my-medium.cnf, my-large.cnf
```
=> 샘플 설정 파일
=> 파일의 내용은 같음. 캐시 메모리 부분의 설정이 다름
=> small : 256MB, medium : 512MB, large : 1 ~ 2GB

```
cp /usr/share/mysql/my-small.cnf /etc/my.cnf
```
=> cp : copy, cp ~을(원본) ~로(복사본)
=> /usr/share/mysql/my-small.cnf 파일을 /etc 디렉터리안에 my.cnf 라는 이름으로 변경해서 복사
=> 같은 이름으로 복사할 때는 파일명을 생략

cp: overwrite `/etc/my.cnf'?
=> 이미 my.cnf 파일이 존재하므로 덮어쓸것인지 물어보는 것
=> y 를 입력

cd(엔터)
=> 어디에 위치해 있든 사용자의 홈 디렉터리로 이동
=> root 사용자의 홈 디렉터리는 /root 방이다!
=> 홈 디렉터리는 '~' 표시

```
vi /etc/my.cnf
```
=> 마리아DB 설정 파일을 수정
=> 앞의 cp 명령어로 초기의 my.cnf가 아닌 my-small.cnf 파일의 내용이 들어있음.

:set nu
=> 라인 번호 넣기

22번 빈라인 추가
```
default-character-set = utf8
```
=> 기본 언어셋을 utf8로 변경
=> 마리아DB의 기본 언어셋은 '라틴1'으로 설정되어 있어서 '한글' 입력 불가!

30번 라인 추가(29번 끝에서 엔터)
```
skip-name-resolve
```
=> 쿼리 구분 실행 시 서버가 사용자의 소속을 알아보는 과정을 생략
=> 빠른 성능을 위해서 설정

31번 라인 추가
```
character-set-server = utf8
```

esc -> :wq

```
systemctl restart mariadb
```

---
### 실습 환경 구성

```
mysql -uroot -p
1234
```
=> 마리아 DB접속

```sql
create database shopping_db;
```
=> 데이터베이스(=스키마) 생성

```sql
show databases;
```
=> 생성된 shopping_db 확인

```sql
use shopping_db;
```
=> shopping_db 데이터베이스 사용
=> [(none)] -> (shopping_db) 로 변경

< customer 테이블 생성 >
```sql
create table customer(
    id varchar(20) not null,
    name varchar(20) not null,
    age int(11) null,
    address varchar(100) not null,
    primary key(id));
```

<purchase 테이블 생성>
```sql
create table purchase(
    no int(11) not null primary key auto_increment,
    cust_id varchar(20) not null,
    date DATETIME not null,
    product varchar(10) not null);
```
=> AUTO_INCREMENT : 생성될 때마다 1씩 증가해서 입력

```sql
show tables;
```
=> 생성된 테이블 확인

```sql
insert into customer values('kang', '강진석', 20, '부산');
```
=> customer 테이블에 데이터 입력

```sql
select * from customer;
```

---
#### 사용자 추가
```sql
grant all [privileges] on shopping_db.* to itwill@localhost identified by '1234';
```
=> grant all : 모든 권한 부여(CRUD)
=> on shopping_db.* : shopping_db 데이터베이스의 모든 테이블에 적용(현재 customer, purchase)
=> to itwill@localhost : 마리아 DB가 설치된 운영체제(리눅스)에서 접속하는 itwill 계정
=> identified by '1234' : 비밀번호를 1234로 설정

```
flush privileges;
```
=> 사용자와 관련된 설정 후에는 적용을 시킴

```
exit
```
=> 마리아DB 종료

```
mysql -uitwill -p
1234
```
=> 생성된 itwill 계정으로 접속

```
show databases;
```
=> mysql 데이터베이스가 보이지 않음!
=> mysql은 root 계정만 사용할 수 있음. 일반 유저인 itwill은 접근할 수 없다.

```
drop user itwill@localhost;
```
=> ERROR 1227 (42000): Access denied; you need (at least one of) the CREATE USER privilege(s) for this operation
=> 사용자 추가 삭제는 'root' 유저만이할 수 있음

---
### 외부에서 마리아BD 접속(리눅스 -> 마리아DB서버)

★ webServer 머신
실습을 위해서 다른 리눅스 머신 실행(webServer)

바탕화면 우클릭 터미널 -> ip add -> 가상머신의 IP주소 확인
=> 192.168.56.102

마리아DB 서버 접속 프로그램(클라이언트 프로그램)
yum install -y mariadb
=> DB 서버로 접속하기 위한 클라이언트 프로그램 설치

※ DB서버로 원격 접속
```
mysql -u유저명 -hDB서버IP주소 -p비밀번호
mysql -uitwill -h192.168.56.103 -p

1234
```
※ 만약, 에러가 발생하면
1) DB서버의 방화벽이 동작하고 있고, mysql을 허용하지 않았다면 방화벽 설정필요!
2) 마리아DB 서버 내의 itwill@'클라이언트IP주소'의 [grant 명령어](<#사용자 추가>) 필요!

★ DB서버로 이동

1) 방화벽 설정(터미널)
```
firewall-cmd --add-service=mysql --permanent
firewall-cmd --reload
```

2) web의 IP주소인 192.168.56.102의 주소를 가지는 장치에서 접속하는 itwill 유저에 대한 권한 설정이 필요!

```
mysql -uroot -p
1234
```
=> 유저를 추가하기 위해서는 root 권한 필요

```sql
grant all on shopping_db.* to itwill@'192.168.56.102' identified by '1234';
```
=> 192.168.56.102의 IP주소를 가지는 장치에서 itwill 계정으로 접속 허용

```
flush privileges;
```
=> 변경 사항 적용

```
exit
```
=> 마리아DB 접속 종료

### 외부에서 마리아DB 접속(윈도우 -> 마리아DB서버)

★ DB서버로 이동
```
mysql -uroot -p
1234
```

```sql
grant all on shopping_db.* to itwill@'192.168.56.1' identified by '1234';
```
=> 192.168.56.1의 IP주소를 가지는 장치에서 itwill 계정으로 접속 허용

```
flush privileges;
```
=> 변경 사항 적용

★ 윈도우로 이동

워크벤츠 실행
'+' 버튼 클릭 -> setup new connection 설정
Connection Name : MariaDB
Hostname : DB서버 IP주소(ex. 192.168.56.103)
Username : itwill
Password : 1234

Test Connection 버튼트로 접속 확인 또는 OK를 눌러서 설정을 마무리

메인화면에 만들어진 MariaDB 버튼을 누르면 접속이 완료!
접속화면 왼쪽에 shopping_db 확인

---
### 데이터베이스 유저 확인

```
mysql -uroot -p mysql
```
=> mysql 데이터베이스를 지정해서 접속을 함(=use mysql 명령어를 넣은 것과 같음)

```
select user,host from user where user NOT LIKE '';
```
=> user 테이블의 user 컬럼의 공백을 제외하고 user 컬럼과 host만 묶어서 보여주기!
=> 위에서 설정한 webserver와 윈도우의 IP주소를 host로 가지는 itwill 계정 확인

```
delete from user where host='192.168.56.1';
```
=>  host 컬럼의 값이 192.168.56.1 인 로우(=레코드) 삭제!

```
flush privileges;
```

---
### root 비밀번호 설정
1) 리눅스에서 설정
```
mysqladmin -uroot -p password '1111'
Enter password : 1234
```
=> 명령어를 입력하면 history에 등록되므로 history -d 라인번호를 입력하여 삭제!

2) 마리아DB 내에서 설정(권장)
```
mysql -uroot -p mysql
update user set password=password('1') where user='root';
flush privileges;


exit

mysql -uroot -p
Enter password : 1
```
---
### 데이터베이스 백업/복구

```
mysqldump -uroot -p --all-databases;
Enter password : 1
```
=> mysqldump 명령어는 지정된 데이터베이스 또는 테이블의 내용을 화면(모니터)에 출력

>백업이라고 하면 보통은 파일 형태로 존재!
>모니터에 출력되는 내용을 파일 형태로 저장하는 과정이 필요!
>=> 리다이렉션을 통해서 이를 구현

※ 리다이렉션(redirection)
- 출력의 방향을 바꿔주는 방법(기술)
- 리눅스의 표준 출력장치(stdOut)는 모니터
- 리다이렉션 기호 : << , <, >, >>

#### 리다이렉션 실습
```
echo 1
```
=> 1 을 모니터에 출력
echo Hello, World!

```
touch test.txt
```
=> touch : 비어 있는 파일(빈파일)을 만드는 명령어
=> touch 파일명

```
cat test.txt
```
=> test.txt 파일의 내용 보기
=> 현재 비어 있으니 아무 내용이 나오지 않음!

```
echo 1 > test.txt
```
=> 1을 모니터에 출력하지 않고, test.txt로 방향을 바꿔서 보냄
=> test.txt에 1이 입력

```
echo 강진석 > test.txt
echo 2 > test.txt
cat test.txt
```
=> 1이 사라지고 2가 출력

```
echo 1 >> test.txt
cat test.txt
```
=> 기존의 내용을 삭제하지 않고 1만 마지막 라인에 추가
2
1

>※ '>' 와 '>>'
>공통점 : 표준 출력인 모니터로 출력하지 않고, 파일로 출력하는 (입력) 리다이렉트 기호
>차이점 : '>'는 기존의 파일 내용을 없애고 파일에 출력
 >          '>>' 는 기존의 파일 내용을 유지하고 마지막 라인에 추가
