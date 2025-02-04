# Auto Scaling 그룹과 로드 밸런서를 통한 장애 조치
**p74**

1. 로드밸런싱 -> 대상 그룹 -> □ exercise-target-group 선택 -> 아래쪽 상태검사(Health Checks) 탭 클릭

2. 빠른 결과를 보기 위해서 '편집' 버튼을 눌러 기본 설전값 변경
>▶ 고급 상태 검사 설정 펼침
>- 정상 임계값 : 2
>- 비정상 임계값 : 2
>- 제한 시간 : 2
>- 간격 : 5
>- 성공코드 : 200(기본값)

설정이 완료되면 '변경 내용 저장' 버튼 클릭

3. 장애조치 실습을 위해 2대의 서버가 동작할 수 있도록 설정
=> Auto Scaling 용량을 변경

Auto Scaling 그룹 -> □ EXERCISE-GROUP 체크 -> 세부 정보 -> 그룹 세부 정보 -> 편집
=> 원하는 용량 : 2, 최소 용량 : 2, 최대용량 : 2 로 변경
설정을 변경한 후 '업데이트' 클릭

4. MobaXterm 으로 이동
=> exercise-group1, exercise-group2 의 세션 이름으로 각각의 Auto Scaling EC2 인스턴스 접속

5. EC2 인스턴스의 기본 페이지 수정
cd /var/www/aws-exercise-a
vi app.js
=> app.get('/') => 'AWS exercise의 A project입니다. - 1' 로 변경
esc -> :wq

```
sudo service nginx restart
```

위의 과정을 그대로 하되 exercise-group2 세션에서는
'AWS exercise의 A project입니다. - 2' 로 변경

6. 로드밸런서의 동작 확인
웹 브라우저에서 로드밸런서의 DNS 주소를 입력하고 새로고침(F5)을 누르면
'AWS exercise의 A project입니다. - 1'
'AWS exercise의 A project입니다. - 2'
가 번갈아 가면서 동작

7. 로그 확인을 위해서 이동
```
cd /opt/nginx/logs
```

8. access.log 파일을 보기
```
tail -f access.log
```
※ tail 명령어는 파일의 맨 아래 라인부터 위쪽으로 10줄을 보여주는 명령어
- 숫자를 옵션으로 사용하면 라인 개수를 변경 (ex. tail -5 access.log)
- f 옵션을 사용하면 실시간으로 로그를 볼 수 있음

윈도우의 웹 브라우저에서 주소창에 로드밸런서(exercise-lb)의 DNS 주소를 입력하고 새로고침(F5) 누를 때마다
로드 밸런서가 두 대의 서버를 번갈아가면서 접근하는 것을 볼 수 있음

그러면 하나의 서버가 다운된다면??
둘 중 하나의 서버로 이동하여 Ctrl + C를 입력하여 실시간 로그 보기 종료

exercise-group1로 이동
Ctrl + C 를 입력하여 실시간 로그 보기 종료

해당 서버에서 웹 서비스 중단
```
sudo service nginx stop
```

중단시키고 윈도우의 웹 브라우저에서 F5를 몇 번 누르면 '502 Bad Gateway' 메시지가 뜸
=> **500번대의 상태 코드는 서버의 오류**가 있을 때 발생

시간이 지나면 새로고침을 해도 502에러가 발생하지 않음.
그 이유는 로드밸런서가 하나의 서버에서 문제가 발생했음을 인지했기 때문.
요청을 해당 서버로 보내지 않음. 즉, 나머지 하나의 서버로만 요청을 전달


