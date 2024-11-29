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
## 삭제
```
docker rm 컨테이너명/ID
```
실행 중인 컨테이너 삭제
```
docker rm -f (컨테이너명1 컨테이너명2 ...)
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