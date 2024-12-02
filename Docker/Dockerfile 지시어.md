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