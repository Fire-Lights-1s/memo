# EC2 인스턴스 작성

## 환경 구성
1. AWS 로그인

2. EC2 대시보드 인스턴스 -> 인스턴스 시작을 눌러 EC2 인스턴스 생성
이름 : exercise-instance1
AMI : Amazon Linux 2 AMI
인스턴스 유형 : t2.micro
키페어(로그인) : 기존에 있던 키페어 사용
네트워크 설정 : 기존 보안 그룹 선택 -> web, ssh
스토리지 구성 : 기본값 8GiB

설정이 완료되면 인스턴스 시작 -> 모든 인스턴스 보기

3. 새로 생성된 exercise-instance1 에 원격 접속

---

교재 p24 

```
sudo yum install git -y
```
=> EC2 서버에 git 설치

```
git clone https://github.com/asdf-vm/asdf.git ~/.asdf --branch v0.9.0
```
=> ~ : 홈 디렉터리를 의미. 현재 접속자가 ec2-user 이므로 /home/ec2-user 를 가리킴
=> 즉, ~/.asdf 는 /home/ec2-user/.asdf 와 같음
=> '.' 으로 시작하는 파일 또는 디렉터리는 숨김 파일과 같음

```
ls -a
```
=> .asdf 가 보임

```
echo . $HOME/.asdf/asdf.sh >> ~/.bashrc
```
=> $HOME : 시스템 환경 변수 중 하나. 접속 유저의 홈디렉터리를 변수값으로 가지고 있음
=> 리눅스의 사용자마다 자신의 홈 디렉터리에 .bashrc 라는 파일을 가지고 있음
=> 이 파일은 사용자가 접속하게 되면 초기 설정을 해주는 역할을 함.
=> '>>' 리다이렉션 기호
=> echo 명령어는 뒤에 나올 문장을 화면에 출력하는 명령어나 리다이렉션 기호를 만나서
    .bashrc 파일에 해당 내용을 추가(append)
=> .sh 파일은 쉘(셸) 스크립트 파일. 특정한 명령어가 미리 설정되어 있고 .sh 파일을 실행하면 예약된 명령어 실행

※ 셸(shell)
- 명령어를 해석하는 역할
- 리눅스의 구조
사용자(응용프로그램) - 셸(shell) - 커널(Kernal) - 하드웨어

```
source ~/.bashrc
```
=> .bashrc 파일을 수정하면 적용을 시켜야 하는데  1) 리눅스를 재시작 2) source 명령어로 적용

※ asdf
- 버전 관리 툴(tool)
- 프로젝트 별로 사용하는 언어의 버전이 다를 경우 다양한 언어의 버전 별로 관리 가능
- 언어별 버전 매니저를 사용
- Go는 gvm, Node.js는 nvm, ruby는 rbenv 를 사용
- 다양한 버전 매니저를 하나의 도구로 사용할 수 있는 것이 'asdf' 이다

asdf 특징
- asdf 명령어 하나로 여러 언어의 버전을 관리할 수 있음
- global 또는 프로젝트(local) 별로 사용 언어 버전을 지정할 수 있음
- plugin 방식을 사용하여 여러 가지 프로그램을 지원

asdf 사용 방법
1. asdf를 설치
2. 언어별 플러그인을 설치
3. 플러그인을 설치 후 원하는 버전을 설치
4. 프로젝트 별로 사용하는 버전을 지정

```
asdf plugin add nodejs https://github.com/asdf-vm/asdf-nodejs.git
```
=> nodejs의 플러그인 설치

```
asdf install nodejs 16.14.0
```
=> 원하는 버전 설치

```
asdf global nodejs 16.14.0
```
=> 모든 프로젝트가 nodejs를 사용 시 기본 버전 16.14.0을 사용하도록 global 설정을 함

```
node -e "console.log('Running Node.js' + process.version)"
```
=> Node.js 설치가 잘 되었는지 확인
=> process.version 항목이 global 버전으로 대체되어 출력

p25

```
cd /var
```
=> /var 로 이동
※ cd : change directory

```
mkdir www
```
=> www를 /var 아래에 만드는 명령어
=> mkdir: cannot create directory ‘www’: Permission denied
=> root 의 권한이 없어서 발생하는 오류

```
sudo mkdir www
```
(= sudo mkdir /var/www)
※ 절대 경로 : '/'로 시작하는 경로를 말함. 절대경로를 통해 다른 위치에서도 해당 경로를 지정할 수 있음.

```
sudo chown ec2-user www
```
※ chown : change owner
=> sudo mkdir www 명령어에 의해서 만들어진 www 디렉터리(폴더)는 root 소유로 되어 있음
=> ec2-user 가 소유자로 변경되도록 명령어를 입력

```
cd /var/www
```
=> /var/www 디렉터리(폴더)로 이동

```
git clone https://github.com/deopard/aws-exercise-a.git
```
=> 샘플 프로젝트 aws-exercise-a 를 /var/www 아래로 다운로드

```
ls
```
=> aws-exercise-a 디렉터리가 보임

```
cd aws-exercise-a 
```
=> aws-exercise-a 디렉터리로 이동

```
npm install
```
=> npm : node package manager
=> Node.js 에서 사용하는 의존성 패키지를 쉽게 관리할 수 있게 도와주는 프로그램

=============================================================

p30

웹 서버(nginx)와 웹 애플리케이션 서버(Phusion Passenger) 설치

```
cd /var/www
```
=> /var/www 디렉터리 이동

```
wget http://s3.amazonaws.com/phusion-passenger/releases/passenger-6.0.12.tar.gz
```
=> 현재 위치한 /var/www 디렉터리에 압축 파일인 passenger-6.0.12.tar.gz 을 다운로드
=> EC2 서버를 웹 애플리케이션 서버(Web Application Server : WAS)로 동작시키기 위해 phusion passenger를 설치
=> Tomcat과 같은 역할을 한다고 보면 됨!
=> Phusion passenger는 Node.js, Python, Ruby, Meteor 언어를 지원

```
ls
```
=> passenger-6.0.12.tar.gz 보임

```
sudo mkdir /var/passenger
```
=> /var/passenger 디렉터리(폴더)를 생성

```
sudo chown ec2-user /var/passenger
```
=> sudo 명령어를 통해 root 소유자로 지정되어 있는 /var/passenger 디렉터리의 소유자를 ec2-user로 변경

```
tar xvfz passenger-6.0.12.tar.gz
```
=> 위의 명령어를 입력하면 현재 위치한 디렉터리(폴더) 아래에 압축해제 됨!
=> 압축 형태가 .gz 이므로 z 옵션을 사용하여 압축 해제
=> 만약, 압축 형태가 .xz 이라면 J(대문자)를 .bz2 이라면 j(소문자)를 옵션으로 사용
=> 압축해제 시 현재 위치한 디렉터리가 아닌 다른 디렉터리에 해제하고 싶을 때 '-C 파일위치'를 사용

```
tar xvfz passenger-6.0.12.tar.gz -C /var/passenger
```
=> 압축해제와 묶음파일 풀기를 동시에 진행하여(xvfz) -C 옵션뒤에 설정한 경로(/var/passenger)에 위치시켜라!

```
ls /var/passenger
```
=> 압축 해제된 passenger-6.0.12 디렉터리(폴더)가 보임!

p31

```
asdf plugin add ruby
```
=> 루비 언어의 플러그인 설치

```
sudo yum install gcc gcc-c++ glibc glibc-common gd gd-devel openssl-devel libcurl-devel -y
```
=> 루비 언어를 설치하기 전 필요한 패키지 설치

```
asdf install ruby 3.1.1
```
=> 루비 언어 설치

```
asdf global ruby 3.1.1
```
=> 버전 3.1.1을 루비 언어의 기본 버전으로 설정

---
Phusion Passenger 설치
설치 파일 위치 : /var/passenger/passenger-6.0.12/bin

Phusion Passenger 설치(WAS서버)와 nginx(Web서버)를 동시에 진행하려면 passenger-install-nginx-module 설치 파일 사용
만약, 웹 서버 없이 WAS 서버만 설치하여 운용할 경우 passenger 설치 파일을 사용

현재 위치에 상관없이 passenger-install-nginx-module 설치 파일을 실행할 수 있도록 PATH 설정

```
echo export PATH=/var/passenger/passenger-6.0.12/bin:$PATH >> ~/.bash_profile
```
=> 기존의 PATH 설정값에 '/var/passenger/passenger-6.0.12/bin' 를 추가하여 다시 PATH 환경변수 설정을 함.
=> 리다이렉션 기호(>>)를 사용하여 bash_profile 내에 추가!
=> bash_profile은 사용자가 접속 시 사용하는 기본 환경 설정 파일
=> 환경 설정 파일은 각각 유저마다 별개

```
tail -5 ~/.bash_profile
```
=> tail 명령어는 파일의 내용을 뒤에서 부터 위로 기본 10줄을 보여주는 명령어.
=> '-숫자' 옵션을 통해 조절이 가능

```
source ~/.bash_profile
```
=> 새로 변경된 PATH 설정을 적용

---

p32

이제 위치에 상관없이 passenger-install-nginx-module 명령어를 실행할 수 있음!

```
passenger-install-nginx-module
```
=> Phusion Passenger 설치 진행(with nginx)

```
Press Enter to continue, or Ctrl-C to abort.
```
=> 메시지화 함께 Enter키를 누르면 설치가 진행

느낌표(!) 입력
```
>(*)  Ruby
 (*)  Python
 (*)  Node.js
 ( )  Meteor
```
=> * 해제 : 스페이스 바 입력
=> Ruby와 Python 항목의 별표를 해제 시킴
 
  ```
 ( )  Ruby
>( )  Python
 (*)  Node.js
 ( )  Meteor
```
위와 같은 상태(Node.js만 선택된 상태) 라면 엔터키를 누른다.

가상메모리 관련 권장 사항이 표시됨
=> 가상메모리 공간이 부족하면 설치 시 문제가 발생할 수도 있음
=> 가상 메모리 공간을 늘린 뒤 다시 설치를 진행

Ctrl + C 를 입력해서 설치를 종료

sudo dd if=/dev/zero of=/swap bs=1M count=1024
sudo mkswap /swap
sudo swapon /swap

명령어를 차례대로 입력하고 다시 설치 진행

passenger-install-nginx-module -> 엔터 -> ! -> ruby, python 별표 없애기 -> 엔터

Enter your choice (1 or 2) or press Ctrl-C to abort:
=> 1 또는 2 또는 Ctrl + C 선택하는 메시지
=> 1번을 입력해서 Phusion Passenger(톰캣과 같은 역할) + nginx(아파치와 같은 역할)을 같이 설치를 진행

Please specify a prefix directory [/opt/nginx]:
=> nginx의 기본 설치 경로
=> 엔터키를 눌러서 계속 진행

★ 엔터키를 누르면 'Permission problems' 오류가 발생!
=> /opt 디렉터리는 root 유저의 소유이므로 /opt 아래에 ec2-user로는 설치를 진행할 수 없음
=> 오류를 해결해야 설치가 완료됨!

=================================

export ASDF_DATA_DIR=/home/ec2-user/.asdf
=> ASDF_DATA_DIR 환경변수를 생성하고 /home/ec2-user/.asdf 경로를 그 값으로 저장
=> export 명령어로 적용

echo $ASDF_DATA_DIR
=> /home/ec2-user/.asdf 가 화면에 출력

export ORIG_PATH="$PATH"
=> 현재 접속한 ec2-user 사용자의 PATH 설정값을 ORIG_PATH 변수에 저장
=> PATH는 사용자마다 따로 적용되며 이후 설치를 root 유저로 진행할 예정
=> 따라서 root 유저로 변경되면 기존에 설정된 PATH 값은 없어짐!
=> 변수로 저장 후 그 변수의 값을 root의 PATH에 추가시킬 예정

sudo -sE
=> ec2-user에서 root 유저로 사용자를 변경

export PATH="$ORIG_PATH"
=> 현재 사용자는 root 이고, root 사용자의 PATH 환경 변수에 ec2-user 사용자의 PATH 설정값을 가지고 있는
     ORIG_PATH를 대입(저장)

asdf global ruby 3.1.1
=> ec2-user의 PATH 값을 현재 접속한 root 유저의 PATH에 대입했으므로 asdf 관련 명령어도 사용할 수 있음
=> root 유저에서는 기본 루비 버전을 설정하지 않았으므로 global 설정을 입력

이제 root 유저로 다시 passenger-install-nginx-module 명령어를 입력

passenger-install-nginx-module -> 엔터 -> ! -> ruby, python 별표 없애기 -> 엔터 -> 1 엔터 -> 엔터

설치가 진행되는데 조금 시간이 걸림!(몇 분!)

Press ENTER to continue.
=> 엔터키를 입력하면 설치가 마무리 된다.

root 유저로 접속이 되어 있어 exit 명령어를 입력해서 로그아웃 시킴


