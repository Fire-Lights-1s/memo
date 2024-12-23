### **tnsname.ora 파일이란?**

tnsname.ora 파일은
Oracle Database의 접속관련 정보를 저장해 놓은 파일이다.

**위치**
오라클 홈 파일이 있는 위치에 network\admin 위치 
```
[접속 DB 별칭] =   
  (DESCRIPTION =  
    (ADDRESS_LIST =   
      (ADDRESS = (PROTOCOL = TCP)(HOST = [IP])(PORT = [Port No]))  
    )  
    (CONNECT_DATA =   
      (SERVICE_NAME = [Service Name])  
    )  
  )
```

**연결 확인 명령어**
```
tnsping [ip or service name]
```