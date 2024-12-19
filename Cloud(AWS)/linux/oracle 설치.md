os : rocky linux 8.10
최소 필요 메모리 : 3GB
# 오라클 설치 전 사전 설정

**/etc/hosts 설정**
```
vi /etc/hosts

127.0.0.1   localhost localhost.localdomain localhost4 localhost4.localdomain4
::1         localhost localhost.localdomain localhost6 localhost6.localdomain6
192.168.137.19 ora19--추가
현제 리눅스 ip  아이피명
```

**/etc/hostname 확인**
```
vi /etc/hostname

ora19 -- 아까 작성한 아이피명 추가
```

# 오라클 설치

**preinstall rpm 설치**
- 필요한 설정을 해줌
```shell
curl -o oracle-database-preinstall-19c-1.0-2.el8.x86_64.rpm https://yum.oracle.com/repo/OracleLinux/OL8/appstream/x86_64/getPackage/oracle-database-preinstall-19c-1.0-2.el8.x86_64.rpm

curl -o [저장할 파일명] [파일을 다운 받을 주소]
```

> 오류
> [Errno 14] curl#6 – “Could not resolve host: yum.oracle.com; Unknown error”
> DNS 확인
> ```shell
> nslookup yum.oracle.com
> ```
> server값이 로컬을 찾고 있을때
> https://databasehustler.home.blog/2019/08/22/errno-14-curl6-could-not-resolve-host-yum-oracle-com-unknown-error/

>`connection timed out; no servers could be reached` 라고 출력될 때
>systemd-resolved 서비스가 동작중인지 확인
>```shell
>systemctl status systemd-resolved
>```
>죽어있다면 다시 실행
>```shell
>systemctl start systemd-resolved.service
>```

**패스워드 설정**
```shell
passwd oracle
```

**selinux permissive 설정**
```shell
vi /etc/selinux/config

SELINUX=permissive
```


**방화벽 해제**
```shell
systemctl stop firewalld
systemctl disable firewalld
```


**설치 경로 생성**
```shell
mkdir -p /app/oracle/product/19c/

chown -R oracle:oinstall /app
chmod -R 775 /app
```


**서버에 Oracle Database 설치 미디어 업로드 후 권한 부여**
`/app/oracle`에 `LINUX.X64_193000_db_home.zip`를 넣음
```sh
chown oracle:oinstall LINUX.X64_193000_db_home.zip
```

>오류
>mobaxterm으로 ssh 외부 접속 후 파일 전송이 거부됨
 >->root 계정으로 로그인 후 전송 가능해짐

**오라클 계정 접속 후 .bash_profile 에 아래 내용 추가**
```sh
export umask=022
export TMP=/tmp
export TMPDIR=$TMP
export EDITOR=vi
export ORACLE_BASE=/app/oracle
export ORACLE_HOME=$ORACLE_BASE/product/19c/dbhome
export ORACLE_SID=ORA19C
export ORACLE_TERM=xterm

export BASE_PATH=/usr/sbin:$PATH
export TNS_ADMIN=$ORACLE_HOME/network/admin
export PATH=$TNS_ADMIN:$ORACLE_HOME/bin:$ORACLE_HOME/OPatch:$BASE_PATH
export LD_LIBRARY_PATH=$ORACLE_HOME/lib:/lib:/usr/lib
export CLASSPATH=$ORACLE_HOME/JRE:$ORACLE_HOME/jlib:$ORACLE_HOME/rdbms/jlib
export NLS_LANG=AMERICAN_AMERICA.KO16MSWIN949
export LIBPATH=$ORACLE_HOME/lib:/lib:/usr/lib:$LIBPATH

```

**적용**
```shell
. ./.bash_profile
```

**오라클 설치파일 압축해제**
```shell
cd $ORACLE_HOME
unzip /app/media/LINUX.X64_193000_db_home.zip
```

**오라클 설치**
```shell
./runInstaller
```

>오류
>oracle 설치 프로그램 실행 시 한글 깨짐
 -> 설치 실행 전에 `export LANG=C` 과 `export LC_ALL=C`
>
>설치 프로그램 실행 시 에러 [INS-801010] 발생
 -> 설치 실행 전에 `export CV_ASSUME_DISTID=OEL7` 입력
 -> oracle 설치 프로그램이 현재 실행 중인 리눅스 배포판이 특정 배포판 ID를 가진다고 가정하게 만듬


### **리스너 설정**
netca 실행을 위해 `$ORACLE_HOME/bin` 으로 이동
```shell
cd $ORACLE_HOME/bin
netca
```