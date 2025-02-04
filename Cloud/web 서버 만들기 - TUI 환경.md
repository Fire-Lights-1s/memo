### web 서버 만들기 - TUI 환경
```
yum -y update && yum -y install httpd
```
=> && 를 사용하면 명령어를 이어서 할 수 있음.
=> 단, && 앞의 명령에서 에러가 발생하면 뒤의 명령은 실행되지 않음!!

```
systemctl start httpd
```
=> 아파치 시작

```
systemctl status httpd
```
=> 동작 확인

```
curl localhost
```
=> curl 명령어를 통해서 웹 페이지의 내용을 볼 수 있음

#### 윈도우에서 웹 브라우저로 보기
1) systemctl stop firewalld
=> 방화벽 끄기
>※ systemctl start firewalld : 방화벽 시작
2) 명령어로 http 서비스 허용하기
firewall-cmd --add-service=http --permanent
firewall-cmd --reload
>※ 서비스 확인 : firewall-cmd --list-service

cd /var/www/html
>※ cd : change directory
