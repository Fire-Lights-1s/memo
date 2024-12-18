os : rocky linux 8.10

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

**preinstall rpm 설치**
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