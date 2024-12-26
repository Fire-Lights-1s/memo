## MySQL Community 패키지 설치

```shell
dnf -y install https://dev.mysql.com/get/mysql80-community-release-el8-8.noarch.rpm
```

## MySQL 8.0 버전 리포지토리 활성화
```shell
dnf module reset mysql

dnf module disable mysql
```

### MySQL 8.0 버전 확인

```shell
dnf repolist all | grep mysql
```

## MySQL 8.0 community server설치

```shell
dnf -y install mysql-community-server
```

