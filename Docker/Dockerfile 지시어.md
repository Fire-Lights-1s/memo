## Dockerfile 기본 지시어
베이스 이미지를 지정
```Dockerfile
FROM 이미지명
```
파일을 레이어에 복사
- [빌드 켄텍스트](<./이미지 빌드.md#빌드 컨텍스트>)에 있는 파일만 카피 명령으로 복사  가능
```Dockerfile
COPY 빌드컨텍스트경로 레이어경로
```

명령어 실행
```Dockerfile
RUN 명령어
```

컨테이너 실행 시 명령어 지정
```Dockerfile
CMD ["명령어"]
```

작업 디렉토리를 지정
```Dockerfile
WORKDIR 폴더명
```

명령을 실행할 사용자 변경
```Dockerfile
USER 유저명
```

컨테이너가 사용할 포트를 명시
```Dockerfile
EXPOSE 포트번호
```