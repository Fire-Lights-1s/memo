#### PHP 버전 업데이트 
```
yum remove -y php* php-*
```
=> 기존의 PHP 5.4.16 버전 삭제

```
rpm -qa php
```
=> 아무런 내용이 나오지 않으면 삭제 완료!

```
cd /etc/yum.repos.d/
```
=> yum 명령어 사용 시 리눅스가 찾을 저장소가 저장된 디렉터리

```
yum install -y epel-release
```
=> 기존 저장소가 아닌 확장된 저장소를 찾기 위한 업데이트를 한 번 진행

```
yum install -y https://rpms.remirepo.net/enterprise/remi-release-7.rpm
```
=> PHP 7.x 버전의 패키지를 포함하는 저장소를 추가적으로 등록
=> 이제 yum 명령어를 통해 PHP 7.x 이상의 버전도 설치가 가능해졌음!

```
yum-config-manager --enable remi-php73
```
=> yum 명령어를 통해 install(설치)할 때 PHP 버전을 7.3버전이 기본값이 되도록 설정

```
yum install -y php php-common php-fpm
```
=> 위의 명령어로 php 기본 버전을 7.3으로 등록
=> PHP 7.3버전 설치