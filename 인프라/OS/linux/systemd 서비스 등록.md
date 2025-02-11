서비스 파일 예시
```null
[Unit]
Description=Apache HTTP Server   # 서비스의 설명 제공
After=network.target  # 서비스의 의존성. http service는 network.target 이후에 실행되어야 한다

[Service]
Type=forking  # 서비스의 실행 유형. forking = 백그라운드 실행 서비스
ExecStart=/usr/sbin/httpd -k start  # 서비스 시작 명령어
ExecReload=/usr/sbin/httpd -k restart   # 서비스 재시작 명령어
ExecStop=/usr/sbin/httpd -k stop  # 서비스 중지 명령어
PrivateTmp=true   # 서비스 임시 디렉토리

[Install]
WantedBy=multi-user.target   # 서비스 타겟. multi-user.target = 다중 사용자 모드
```

>참고 블로그
>https://velog.io/@zinna_1109/Linux-systemd-%EC%84%9C%EB%B9%84%EC%8A%A4-%EB%93%B1%EB%A1%9D