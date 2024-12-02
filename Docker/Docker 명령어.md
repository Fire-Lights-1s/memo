## 컨테이너 실행
```
docker run (실행 옵션) 이미지명
```

`-d` : 백그라운드 실행
`--name {컨테이너명}` : 컨테이너의 이름 지정
```
docker run -d --name {컨테이너명} 이미지명
```

컨테이너 실행 시 메타데이터의 cmd 덮어쓰기
```
docker run 이미지명 (실행명령)
```
컨테이너 실행 시 메타데이터의 env 덮어쓰기
```
docker run --env KEY=VALUE 이미지명
```

컨테이너 실행과 동시에 터미널 접속
`-it` : 커맨드 창을 통해서 실행할 컨테이너와 직접 상호작용 할 수 있다
```
docker run -it --name 컨테이너명 이미지명 bin/bash
```
실행 중인 컨테이너를 이미지로 생성
```
docker commit -m 커밋명 실행중인컨테이너명 생성할이미지명
```

## 조회
로컬 이미지 조회
```
docker image ls (이미지명)
```

이미지의 세부 정보 조회
```
docker image inspect 이미지명
```

컨테이너의 세부 정보 조회
```
docker container inspect 컨테이너명
```

실행 중인 컨테이너 리스트 조회
```
docker ps
```

실행 중인 컨테이너의 **로그** 조회
```
docker logs (컨테이너명)
```

이미지의 레이어 이력 조회
```
docker image history 이미지명
```
## 삭제
```
docker rm 컨테이너명/ID
```
실행 중인 컨테이너 삭제
```
docker rm -f (컨테이너명1 컨테이너명2 ...)
```

로컬 스토리지의 이미지 삭제
```
docker images rm 이미지명
```

## 이미지 레지스트리
로컬 스토리지로 이미지 다운로드
```
docker pull 이미지명
```
로컬 스토리지의 이미지명 추가(원래 이미지 유지)
```
docker tag 기존이미지명 추가할이미지명
```
이미지 레지스트리에 이미지 업로드
```
docker push 이미지명
```

이미지 레지스트리 인증 정보 생성
```
docker login
```

이미지 레지스트리 인증 정보 삭제
```
docker logout
```

# 이미지 빌드
도커파일을 통해 이미지 빌드
```
docker build -t 이미지명 Dockerfile경로
```

## 도커 파일 기본 지시어
베이스 이미지를 지정
```Dockerfile
FROM 이미지명
```
파일을 레이어에 복사
```Dockerfile
COPY 파일결로 복사할경로
```
컨테이너 실행 시 명령어 지정
```Dockerfile
CMD ["명령어"]
```