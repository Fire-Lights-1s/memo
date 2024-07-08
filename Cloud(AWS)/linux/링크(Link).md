## 링크(Link)
- 윈도우의 바로가기와 같은 개념
- 리눅스에서 systemctl enable 서비스명을 입력하면 심볼릭 링크가 만들어짐
- 예를 들어 systemctl enable mariadb를 입력하면 아래와 같은 메시지가 출력
```
Created symlink from /etc/systemd/system/multi-user.target.wants/mariadb.service to /usr/lib/systemd/system/mariadb.service.
```
=> symlink(심볼릭링크)를 생성

### 파일시스템(FileSystem)
- 물리적인 저장장치(ex. 하드디스크)에 파일을 생성, 저장, 관리하기 위한 논리적인 자료구조를 파일시스템이라고 함
- 물리적인 디스크는 논리적인 파티션(partition)으로 나누어지며, 각 파티션 별로 고유한 파일시스템을 생성한다.
(윈도우 : NTFS, 리눅스 : EXT2, EXT3, EXT4, XFS 등)

#### 파일시스템 구성
- 하나의 파티션은 부트블록 + 슈퍼블록 + 아이노드 리스트 + 데이터블록 형태로 구성
- 부트 블록 : 운영체제를 부팅하거나 초기화하기 위한 **부트스트랩(bootstrap) 코드**를 담고 있는 블록
- 슈퍼 블록 : 해당 파일시스템을 **관리하기 위한 정보**를 담고 있는 블록
- 아이노드 리스트 : 파일들에 대한 **속성 정보**를 담고 있음
=> i-node number, 파일타입, 접근권한, link count, 소유자, 소유그룹, 파일크기, MAC time
- 데이터블록 : 실제 파일의 내용(데이터)이 저장되는 블록

#### i-node
- 리눅스는 파일을 생성할 때 두 가지 절차를 갖는다.
	1) 파일의 데이터를 저장하기 위한 디스크 내의 공간을 확보
	2) 파일에 대한 기본 정보를 저장하기 위해 i-node(index node)라는 구조를 만듦
- i-node에는 리눅스가 파일을 사용하는데 필요한 모든 정보가 들어 있음
- 모든 파일이나 디렉터리는 각각 1개씩의 index를 가짐

#### 링크(link)
- 파일의 링크는 하드링크(Hard Link)와 심볼릭링크(Symbolic Link 또는 Soft Link) 2가지가 있음
- 링크의 개념을 이해하려면 i-node 개념을 먼저 익혀야 함
- 하드링크를 설정하면 '하드 링크 파일'만 하나 생성되며 같은 i-node number를 사용함(백업 개념)
- 반면 심볼릭 링크를 설정하면 새로운 i-node 를 생성하고, 데이터는 원본 파일과 연결되는 효과를 갖는다. (바로가기 개념)
- 하드 링크 생성 방법 : ln [링크대상파일이름][링크파일이름]
- 심볼릭 링크 생성 방법 : ln -s [링크대상파일이름][링크파일이름]

---
#### 실습
```
touch test1.txt test2.txt
```
=> 빈 파일 생성

```
ls -al *.txt
```
=> .txt로 끝나는 파일들의 상세 내용이 표시

```
ls -ail *.txt
```
=> -i 옵션을 추가하면 i-node 번호까지 표시

>34840551 -rw-r--r--. 1 root root 0  6월 28 17:34 test1.txt
>34840552 -rw-r--r--. 1 root root 0  6월 28 17:34 test2.txt

test1.txt -> 34840551
test2.txt -> 34840552

```
ln test1.txt test1_hlink
```
=> 하드 링크를 생성

```
ls -ail test*
```
=> test로 시작하는 파일들을 보여줌
=> test1.txt와 test1_hlink 의 i-node 번호가 같다

---
```
ln -s test2.txt test2_slink
```
=> 심볼릭 링크를 생성

```
ls -ail test2*
```
=>test2.txt와 test1_slink의 i-node 번호가 다르다

>34624722 -rw-r--r--. 1 root root 0  7월  5 09:18 test2.txt
>34625140 lrwxrwxrwx. 1 root root 9  7월  5 09:26 test2_slink -> test2.txt

---
### 하드링크와 심볼릭링크의 차이점
- 하드링크의 경우에는 링크 파일의 원본을 삭제해도 하드링크 파일엔 아무런
  영향이 없음.
- 그러나, 심볼릭링크의 경우 링크 파일의 원본을 삭제하면 심볼릭 링크 파일에
  영향을 미친다.
=> 그 이유는 심볼릭 링크 파일은 원본 파일의 i-node 번호를 참조하기 때문!
=> 원본 파일이 삭제되면 심볼릭 링크에도 영향을 받음

#### 하드 링크
```
rm test1.txt
```
=> 하드링크의 원본 삭제
※ rm : remove(제거)

```
ls -ail test*
```
=> test1.txt가 삭제되었음을 알 수 있음

```
cat test1_hlink
```
=> 원본이 사라져도 기존의 내용이 그대로 남아 있음!
=> i-node 번호도 그대로 가지고 있음
#### 심볼릭 링크
```
rm -f test2.txt
```
=> 심볼릭 링크의 원본을 삭제

```
ls -ail test*
```
=> test2.txt가 삭제되었음을 알 수 있음
=> 34625140 lrwxrwxrwx. 1 root root 9  7월  5 09:26 test2_slink -> test2.txt 
     빨간색으로 변하며 원본 파일을 찾고 있음

```
cat test2_slink
```
=> cat: test2_slink: 그런 파일이나 디렉터리가 없습니다
=> 원본 파일이 없으므로 내용도 보여줄 수 없다!

```
touch test2.txt
ls -ail test*
```
=> 빨간색으로 변했던 심볼릭 링크가 다시 원상복구 되었음
=> 심볼릭링크를 통해 파일시스템을 관리하는 경우 이를 통해 보안에 취약해질 수 있음
=> 삭제되었던 test2.txt를 다시 만들면 본래의 형태로 돌아가나, **파일의 i-node 번호가 달라져서 전혀 다른 파일**인 것을 알 수 있음.

>- 이와 같이 링크는 파일에 대한 백업의 개념으로도 사용됨
>- 하드링크는 디렉터리에 대한 링크를 만들 수 없는데, 심볼릭 링크는 가능하다.

mkdir d
※ mkdir : make directory, 디렉터리 생성

ln d d_hlink
=> ln: `d': 디렉토리는 하드링크할 수 없습니다
=> 하드링크는 디렉터리를 대상으로 불가!

ln -s d d_slink
=> d_slink 가 생성됨

