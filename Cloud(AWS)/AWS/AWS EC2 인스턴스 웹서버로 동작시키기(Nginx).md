# AWS EC2 인스턴스 웹서버로 동작시키기(Nginx)
1. EC2 대시보드 -> EC2 인스턴스 생성
 - 이름 : webServer

2. 원격접속

3. nginx 공식 리포지토리 추가
```
sudo vi /etc/yum.repos.d/nginx.repo
```
---
```
[nginx]
name=nginx repo
baseurl=http://nginx.org/packages/centos/7/$basearch/
gpgcheck=0
enabled=1
```
---

4. nginx 설치
```
sudo yum install nginx -y
```

5. nginx 설치 확인
```
rpm -qa nginx
```
=> nginx-1.26.1-2.el7.ngx.x86_64
또는 
```
nginx -v
```
=> nginx version: nginx/1.26.1

6. nginx 동작 확인
```
systemctl status nginx
```
=> Active: inactive (dead)  : 동작 중지

7. nginx 시작
```
sudo systemctl start nginx
```

8. nginx 동작 확인
```
systemctl status nginx
```
=> Active: active (running) : 동작 중!

9. 윈도우 웹브라우저에서 접속 확인
=> EC2 인스턴스 퍼블릭 IP주소를 웹 브라우저 주소창에 입력
=> Welcome to nginx! 

---
DocumentRoot에 index.html 만들기

아파치(httpd)의 경우 /var/www/html/index.html 생성하면 되는데,
nginx의 DocumentRoot는 어디일까?

```
vi /etc/nginx/nginx.conf 
```
=> 'G' 를 입력해서 맨 아래 줄로 이동하면 
include /etc/nginx/conf.d/*.conf 라인이 보이고, 이 문장으로 인해
/etc/nginx/conf.d 디렉터리 안의 .conf로 끝나는 모든 파일들을 참조하는 것을 알 수 있음!!

```
sudo ls /etc/nginx/conf.d
```
=> default.conf 파일이 보임!

```
sudo vi /etc/nginx/conf.d/default.conf
```
=> root    /usr/share/nginx/html; 보임
=> nginx의 DocumentRoot는 위의 경로이다!

```
sudo mv /usr/share/nginx/html/index.html /usr/share/nginx/html/index.html.org
```

```
sudo vi /usr/share/nginx/html/index.html
```