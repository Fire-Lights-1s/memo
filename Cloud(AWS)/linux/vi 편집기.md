### vi 편집기
- vi = visual editor
- TUI(Text User Interface) 환경에서 사용하는 문서 편집기
#### vi 3가지 모드
- 명령모드
  => 기본모드
  => i 또는 a 를 입력하면 입력모드 전환됨
  => 콜론(:)을 입력하면 실행모드 전환됨
- 입력모드
  => 키보드를 통해서 입력을 할 수 있는 모드
  => 왼쪽 하단에 '-- INSERT --' 표시됨
  => 'esc'키를 누르면 다시 명령모드로 이동
- 실행모드
  => 파일을 저장, 불러오기 등을 할 수 있는 모드
  => 왼쪽 하단에 콜론(:)이 생겨남
  => :q 빠져나가기 (저장 없이 나가려면 :q!)
=============================
### vi 실습

```
vi 엔터키
```

- i 또는 a를 눌러서 입력모드 전환
=> Hello 입력

- esc -> :q
=> E37: No write since last change (add ! to override)
=> 저장을 하지 않고 편집기를 종료하려고 해서 생긴 에러

관리자는
1) 저장하고 빠져 나갈지, 2) 저장을 하지 않고 빠져나갈지 선택
1) 경우 : ':wq'
※ 단순히 저장만 할 때는 ':w'
2) 경우 : 'q!'
---

- vi 엔터

- 'i' 를 눌러서 입력모드 전환
=> Hello, World! 입력

- 현재 입력모드(INSERT) 이므로 실행모드로 바로 이동할 수 없고, esc키를 눌러서 명령모드로 전환 후 실행모드로 접근할 수 있음

```
esc -> :wq
```
=> E32: No file name
=> 저장할 파일 이름을 지정하지 않아서 생긴 에러

- :wq [파일이름] 필요

```
:wq test.txt
```
=> Hello, World! 문장이 입력된 test.txt 파일이 현재 작업 디렉터리에 생겨남!

```
vi test.txt
```
=> 기존에 존재하는 파일명을 적으면 그 파일을 수정하는 형태로 문서 편집기가 동작!

만약, 존재하지 않는 파일명을 vi 명령어 뒤에 적으면 리눅스는 새 파일을 생성하면서 편집기로 접근

vi test.txt
=> test.txt 파일을 수정하는 형태로 편집기 실행

명령모드에서
':set nu' : 라인마다 번호를 붙일 수 있음
':set nonu' : 라인에 번호를 삭제
'G' : 제일 끝 행으로 이동
'gg' : 첫번째 행으로 이동
':행번호' : 해당 행으로 이동

명령모드에서
u : 현재 작업에서 앞 시점으로 되돌리기
=> undo
ctrl + r : 작업의 앞 시점으로 이동
=> redo
yy : 한줄 복사
p : 복사한 내용을 커서 아래쪽에 붙여넣기
dd : 한 줄 삭제

3yy : 현재 커서가 위치한 라인부터 3줄을 복사
3dd : 현재 커서가 위치한 라인부터 3줄을 삭제
