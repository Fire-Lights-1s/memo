# 원인
Rocky 9 부터 root의 ssh 접속을 default로 막음

# 해결
ssh 설정 파일에서 설정 변경 후 sshd 서비스 재실행
```sh
vi /etc/ssh/sshd_config
```

```config
#LoginGraceTime 2m
#PermitRootLogin prohibit-password
PermitRootLogin yes # 추가한 내용
#StrictModes yes
#MaxAuthTries 6
#MaxSessions 10
```

```sh
systemctl restart sshd
```