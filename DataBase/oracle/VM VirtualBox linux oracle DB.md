# oracle 시작

linux에 oracle이 설치가 되어있는 virtual box 이미지를 받아서 실행

리눅스에서 오라클을 정상 동작 시키기 위한 명령어
리스너 시작 명령어
```
lsnrctl start
```

sqlplus 프로그램 실행
```
sqlplus /nolog
```

sys 사용자로 DB에 접속하는 명령어
```
conn sys/oracle as sysdba
conn 유저명/패스워드 as 권한명
```

DB 시작 
```
startup
```

## Oracle SQL Developer
Oracle DB에 접속해서 작업 할 수 있는 GUI 환경이다.
>- 접속 이름 : 사용자 임의로 지정하면 된다.
>- 사용자 이름 : 접속할 계정명을 적는다. 관리자 계정으로 접속하려면 system 또는 sys를 입력한다.
>- 비밀번호 : 접속할 계정 비밀번호를 입력한다.
>- 호스트 이름 : IP 주소를 입력한다. 자신의 PC에 오라클을 설치했다면 localhost를 입력, 아니라면 설치된 PC의 IP 주소를 입력한다.
>- 포트 : 연결할 오라클의 포트번호를 입력한다. 따로 변경하지 않았다면 1521을 입력하면 된다.
>- SID : 전역 데이터베이스의 이름을 입력한다. 설치 시 따로 지정하지 않았다면 orcl을 입력하면 된다.