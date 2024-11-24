## 컨테이너 실행
```
docker run (실행 옵션) 이미지명
```
`-d` : 백그라운드 실행
`--name {컨테이너명}` : 컨테이너의 이름 지정
```
docker run -d --name {컨테이너명} 이미지명
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
```shell
docker container inspect 컨테이너명
```

실행 중인 컨테이너 리스트 조회
```
docker ps
```

## 삭제
```
docker rm 컨테이너명/ID
```
실행 중인 컨테이너 삭제
```
docker rm -f (컨테이너명1 컨테이너명2 ...)
```

