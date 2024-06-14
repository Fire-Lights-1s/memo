cd /var/www/html/wordpress

워드프레스의 설정 파일 이름 wp-config.php 이다!
=> 이 파일이 존재하지 않음
=> wp-config-sample.php를 복사해서 wp-config.php 를 생성해야 함

```
cp wp-config-sample.php /var/www/html/wordpress/wp-config.php
```
※ cp [무엇을(원본)] [~로(복사본)]
=> 현재 위치(pwd)가 /var/www/html/wordpress 이라면 
     cp wp-config-sample.php wp-config.php 명령어를 입력하면 같은 디렉터리(폴더) 내에 복사가 됨

- vi wp-config.php
=> 복사된 wp-config.php 파일을 수정

- :set nu
>23 define('DB_NAME', 'database_name_here'); 항목에서 'database_name_here' 삭제하고 설정해둔 'wpDB'로 변경

>26 define('DB_USER', 'username_here'); 항목에서 'username_here' 삭제하고 'wpUser'로 변경

>29 define('DB_PASSWORD', 'password_here'); 항목에서 'password_here' 삭제하고 '1234'로 변경
- 3가지 항목 변경 후 esc -> :wq

- 윈도우 웹 브라우저에서 192.168.56.101/wordpress 접속
=> 환영합니다! 창
사이트 제목 : 아이티윌 부산교육센터
사용자명: admin
비밀번호: 1
=> 매우약함~약감 이면 □약한 비밀번호 사용 확인 체크!
이메일 주소: jskang@itwillbs.co.kr

입력 후 [워드프레스 설치하기] 클릭

로그인 => 관리자 페이지로 이동!

================================================
#### URL 설정
현재 wordpress는 서버IP주소/wordpress로 접근해야 함
주소창에 서버IP주소만 입력하면 wordpress 화면이 보이도록 설정

외부에서 웹 브라우저로 접근 시 아파치는 DocumentRoot 설정값에 의해서 /var/www/html 안의 파일을 찾음

아파치 웹 서비스 설정값을 변경
=> 아파치 웹 서비스 설정 파일 위치는 /etc/httpd/conf/httpd.conf 이다.

vi /etc/httpd/conf/httpd.conf
:set nu

>:119
DocumentRoot "/var/www/html" -> DocumentRoot "/var/www/html/wordpress" 로 변경

>:131
<Directory "/var/www/html"> -> <Directory "/var/www/html/wordpress">

>:151
AllowOverride None -> AllowOverride All

3가지 항목이 수정되었으면 esc -> :wq 

아파치 웹 서비스 프로그램의 설정을 변경했다면 재시작이 필요
systemctl restart httpd

