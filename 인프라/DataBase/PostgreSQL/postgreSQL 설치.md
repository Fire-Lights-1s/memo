
설치 시 주의 사항

yum으로 가져온 패킷과 PostgreSQL 글로벌 개발 그룹에서 가져온 패킷을 혼합하여 설치하면 경고 메세지가 나온다

[postgreSQL 설치 가이드 링크](https://www.postgresql.org/download/linux/redhat/)
yum 으로 설치
```shell
dnf install -y https://download.postgresql.org/pub/repos/yum/reporpms/EL-8-x86_64/pgdg-redhat-repo-latest.noarch.rpm
dnf install -y postgresql12-server
```

PGDG 배포 파일을 다운로드 후 실행
```
rpm -ivh postgresql12-libs-12.12-1PGDG.rhel8.x86_64.rpm
rpm -ivh postgresql12-12.12-1PGDG.rhel8.x86_64.rpm
rpm -ivh postgresql12-contrib-12.12-1PGDG.rhel8.x86_64.rpm
rpm -ivh postgresql12-server-12.12-1PGDG.rhel8.x86_64.rpm
```