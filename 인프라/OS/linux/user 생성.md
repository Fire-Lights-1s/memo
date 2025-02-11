```bash
useradd [option] user-name

# option
-u *UID* : 사용자의 UID 지정
-U : 사용자를 생성할 때 사용자와 같은 이름의 그룹을 생성
-g *GID* : 사용자의 기본 그룹 지정
-G *GID* : 사용자의 보조 그룹 지정
-c *주석* : 사용자에 대한 GECOS 지정
-d *디렉토리* : 사용자의 홈 디렉토리 지정
-s *쉘* : 사용자의 기본 쉘 지정
-o : UID의 중복 허용
-p *패스워드* : 패스워드 지정 -> 현재 사용 x
-r : UID가 199~999인 시스템 계정 만듦
-m : 사용자의 홈 디렉토리가 존재하지 않는 경우 생성
-e *만기일* : /etc/shadow 파일의 EXPIRE 필드값 지정
-f *기간* : /etc/shadow 파일의 INACTIVE 필드값 지정
```

예시 
```bash
useradd -g [그룹명] 유저명
```
