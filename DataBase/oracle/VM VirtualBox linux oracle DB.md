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