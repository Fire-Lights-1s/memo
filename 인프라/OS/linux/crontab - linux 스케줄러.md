# crontab이란?
리눅스 계열에서 특정 시간에 특정 작업을 하는 데몬을 Cron이라고 하고
Cron이 언제 무억을 하는지 특정 파일에 저장하는 것을 Crontab이라고 한다

특정 시간에 프로그램을 실행 시키기 위해 사용

## root 권한이 있는 경우
`/etc/crontab` 파일을 편집해서 설정이 가능하다
> 시스템 전반에 관여하는 crontab이다
> 다른 크론탭과 달리 사용자명을 적는 필드가 있다
> SHELL 은 cron을 실행할 쉘을 적어 주는 부분이고, PATH는 cron을 실행할 때의 $PATH다

```sh
SHELL=/bin/bash
PATH=/sbin:/bin:/usr/sbin:/usr/bin
MAILTO=root

# For details see man 4 crontabs

# Example of job definition:
# .---------------- minute (0 - 59)
# |  .------------- hour (0 - 23)
# |  |  .---------- day of month (1 - 31)
# |  |  |  .------- month (1 - 12) OR jan,feb,mar,apr ...
# |  |  |  |  .---- day of week (0 - 6) (Sunday=0 or 7) OR sun,mon,tue,wed,thu,fri,sat
# |  |  |  |  |
# *  *  *  *  * user-name  command to be executed

```
## root 권한이 없는 경우

기본 명령어
```sh
crontab -l : 예약된 작업리스트
crontab -e : 예약된 작업 수정
crontab -r : 예약된 작업 삭제
crontab -u 사용자명 : 루트관리자는 해당 사용자 crontab 파일을 보거나 삭제, 편집 가능
```

주기 설정
```sh
*　　　　　　*　　　　　　 *　　　　　　*　　　　　　 *
분(0-59)　　시간(0-23)　　일(1-31)　　월(1-12)　　　요일(0-7)

 분(0~59)을 설정. *을 설정한 경우 1분 단위로 실행
 시간(0~23)을 설정. *을 설정한 경우 매시간 실행
 일(1~31)을 설정. *을 설정한 경우 매일 실행
 월(1~12)을 설정. *을 설정한 경우 매달 실행
 요일(0~7)을 설정. *을 설정한 경우 월요일부터 일요일까지 매일 실행
```
특수 문자
- `*` : 모든 값
- `?` : 특정한 값이 없음 
- `-` : 범위를 뜻한다( 2-5 -> 2일~5일)
- `,` : 특별한 값일 때만 동작 (2,3,4 -> 2일,3일4일)
- `/` : 시작시간 /단위 ( `* /5 * * * *` 매일 5분 마다 실행)
- `L` : 일에 사용하면 마지막 일, 요일에서는 마지막 요일
- `W` : 가장 가까운 평일 (15W 15일에 가장 가까운 평일)
- `#` : 몇째주의 무슨 요일을 표현

> 크론 표현식 사이트 : http://www.cronmaker.com