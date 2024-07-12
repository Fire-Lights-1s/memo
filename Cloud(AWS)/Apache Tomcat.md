## Apache Tomcat 
1. Tomcat 다운로드 
- 경로 : http://tomcat.apache.org
  1) 웹 페이지에서 다운로드 받기
  2) 터미널에서 wget 명령어를 이용해서 받기
  3) 터미널에서 curl -O 명령어를 이용해서 받기
  4) FTP를 이용해서 받기

2. 프로그램탭 -> 즐겨찾기 -> Firefox 웹 페이지에서 다운로드를 받음

3. 터미널로 이동
```
cd 다운로드
tar xvfz apache-tomcat-9.0.91.tar.gz
```
=> 압축해제
```
cp -r apache-tomcat-9.0.91 /usr/local/tomcat
```
=> apache-tomcat-9.0.91 디렉터리를 /usr/local 디렉터리 아래에 tomcat 이름으로 변경해서 복사
=> cp는 -r 옵션을 사용하면 디렉터리(폴더)도 복사 가능

4. tomcat 폴더로 이동
```
cd /usr/local/tomcat
cd bin
```
=> 한번에 이동할 때는 cd /usr/local/tomcat/bin

5. ls 명령어로 디렉터리 안을 보면 startup.sh 파일이 보인다.
리눅스에서 스크립트(.sh) 파일을 실행하려면 
1) sh startup.sh
2) 파일명을 절대경로로 실행 (./startup.sh)

6. 시작이 잘 되었는지 확인
```
cd /usr/local/tomcat/logs
tail -5 catalina.xxxx-xx-xx.log
```

7. 리눅스에 JDK 설치
```
yum install -y java-1.8.0-openjdk-devel

readlink -f /usr/bin/java
```
=> /usr/lib/jvm/java-1.8.0-openjdk-1.8.0.412.b08-1.el7_9.x86_64/jre/bin/java

8. PATH 설정
- 리눅스는 PATH 시스템 환경 변수를 가지고 있음
- yum 명령어를 통해 설치한 패키지(프로그램)는 systemctl 명령어를 통해서 시작, 종료를 할 수 있다.
- 그러나 수동 설치한 톰캣은 해당 /bin 디렉터리로 이동하여 ./startup.sh 명령어를 통해 톰캣을 시작할 수 있다.
- 위치에 상관없이 톰캣을 실행하고 싶다면 PATH 설정을 한다.
- PATH 환경 변수는
  1) 사용자 별로 설정
      => $HOME 디렉터리 아래의 bash_profile 에 설정
  2) 시스템으로 설정
      => /etc/profile

```
vi /etc/profile
```

---
- bash_profile
```shell
JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.412.b08-1.el7_9.x86_64
CATALINA_HOME=/usr/local/tomcat
PATH=$PATH:$JAVA_HOME/bin:$CATALINA_HOME/bin
CLASSPATH=$JAVA_HOME/jre/lib:$JAVA_HOME/lib/tools.jar
export JAVA_HOME CATALINA_HOME PATH CLASSPATH
```

---
```
source /etc/profile
```
=> 변경된 profile 내용을 적용

이제 위치가 어디에 있든 톰캣을 켜고 끌 수 있음
shutdown.sh
=> 톰캣 종료

startup.sh
=> 톰캣 시작

#### 톰캣 동작 확인
프로그램 -> 즐겨찾기 -> Firefox -> localhost:8080

#### 외부에서 톰캣 동작 확인 
방화벽 설정
터미널 -> firewall-config
설정 : 영구적으로 변경
서비스탭 : □http, □https 체크
포트탭 : 추가 -> 포트:8080, 프로토콜:tcp 
옵션 -> Firewalld 다시 불러오기

윈도우 웹 브라우저 주소창에
Tomcat서버의 IP주소:8080

---
.war 파일을 /usr/local/tomcat/webapps 아래에 파일 복사

윈도우 웹 브라우저에서
톰캣서버IP주소:8080/ExProject
=> 안녕하세요가 나옴

---
### 기존 자바 버전 변경
jdk 1.8버전 -> jdk 11버전 변경

```
yum install -y java-11-openjdk-devel.x86_64

update-alternatives --config java
```
=> JDK11 버전의 번호를 선택

JDK 버전을 11로 변경하였다면 /etc/profile을 변경한다.

```
readlink -f /usr/bin/java
/usr/lib/jvm/java-11-openjdk-11.0.23.0.9-2.el7_9.x86_64/bin/java

vi /etc/profile
```
---
```shell
JAVA_HOME=/usr/lib/jvm/java-11-openjdk-11.0.23.0.9-2.el7_9.x86_64
CATALINA_HOME=/usr/local/tomcat
PATH=$PATH:$JAVA_HOME/bin:$CATALINA_HOME/bin
CLASSPATH=$JAVA_HOME/jre/lib:$JAVA_HOME/lib/tools.jar
export JAVA_HOME CATALINA_HOME PATH CLASSPATH
```

---
```
source /etc/profile
```
=> 변경된 profile 내용을 적용

