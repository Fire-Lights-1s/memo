## Dockerfile 기본 지시어
베이스 이미지를 지정
```Dockerfile
FROM 이미지명
```
파일을 레이어에 복사
- [빌드 켄텍스트](<이미지 생성.md#빌드 컨텍스트>)에 있는 파일만 카피 명령으로 복사  가능
```Dockerfile
COPY 빌드컨텍스트경로 레이어경로
```

명령어 실행
```Dockerfile
RUN 명령어
```

작업 디렉토리를 지정
>모든 지시어들은 해당 경로에서 실행하게 된다.
```Dockerfile
WORKDIR 폴더명
```

명령을 실행할 사용자 변경
```Dockerfile
USER 유저명
```

컨테이너가 사용할 포트를 명시
> `EXPOSE`를 사용하지 않아도 기본적으로는 컨테이너가 모든 포트를 사용할 수 있다.
> 그럼에도 굳이 `EXPOSE` 지시어를 사용하는 이유는 이 도커 파일을 읽는 다른 사람에게 애플리케이션이 사용하는 포트를 문서처럼 기재하기 위해서이다
```Dockerfile
EXPOSE 포트번호
```

이미지 빌드 시점의 환경 변수 설정
>빌드에만 사용할 환경 변수를 지정
```dockerfile
ARG 변수명 변수값
```

이미지 빌드 및 컨테이너 실행 시점의 환경 변수 설정
>애플리케이션을 실행할 때 참고할 용도로 사용
```dockerfile
ENV 변수명 변수값
```

고정된 명령어를 지정

```dockerfile
ENTRYPOINT ["명령어"]
```
>예시
>```dockerfile
>ENTRYPOINT ["npm"]
>CMD ["start"]
>```
> `npm start`로 실행된다


컨테이너 실행 시 명령어 지정
```Dockerfile
CMD ["명령어"]
```
