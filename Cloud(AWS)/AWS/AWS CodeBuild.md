# AWS CodeBuild
- 소프트웨어 개발에 필요한 소스코드를 컴파일하는 단계에서부터 테스트 후 소프트웨어 배포까지의 단계를 지원하는 완전 관리형 지속적 통합(Continuous Integration) 서비스.
- CodeBuild를 사용하면 자체 빌드 서버가 필요하지 않으며, 빌드 서버를 프로비저닝하거나, 운영/관리 및 확장을 수행할 수 있다.

- AWS CodeBuild는 Apache Maven, Gradle 등과 같이 널리 사용되는 프로그래밍 언어 및 빌드 도구에 맞게 사전 패키지된 빌드 환경을 제공.
- 사전에 정의된 빌드 환경을 생성하여, CodeBuild에서 지원하는 사전 패키지 형태의 빌드 도구와 런타임을 활용하여, 자체 빌드 도구와 런타임을 AWS CodeBuild에서 사용할 수 있다.

## AWS CodeBuild의 기능
1. 코드에 대한 빌드와 테스트
2. 구성 설정
3. 지속적인 통합과 지속적인 배포 워크플로우
4. 빌드 프로세스에 대한 모니터링

## AWS CodeBuild의 동작 방식
1. Source Control 
=> CodeCommit, GitHub, S3 등의 소스 관리 툴에서 데이터 불러오기
=> Create Build 환경

2. Build Project
=> 소스를 가져와서 빌드 프로젝트 수행
=> 소스코드 위치, 빌드 환경, 실행할 빌드 명령 및 빌드 출력을 저장할 위치 등의 정보 포함
=> Run Build

3. Build Enviornment
=> CodeBuild가 Build Project를 사용하여 Build Enviornment 생성
=> CodeBuild가 빌드 환경에 소스코드 다운로드
=> Build Project에 정의된대로 또는 소스코드에 직접 포함된 buildspec.yml 실행
※ buildspec.yml는 CodeBuild가 빌드를 실행하는 데 사용되는 YAML 형식의 빌드 명령 및 관련 설정의 모음
=> Generate Output(산출물)

4. S3 Bucket
=> Build Enviornment에서 Output(산출물)을 Amazon S3 버킷에 업로드

5. Notifications
=> Aamzon SNS에 빌드 알림을 전송하거나 빌드가 실행되는 동안 빌드 정보를 CodeBuild 및 Amazon CloudWatch Logs에 전송

6. Destory Build
=> 빌드 작업이 완료된 후 Build Environment을 삭제하고 Clean Up 작업 수행 

### buildspec 의 요소
1. enviornment_variables : 빌드 단계에서 사용할 변수 정의
2. phases : 빌드 단계에서 사용할 수 있는 작업의 예
- install : 환경 준비나 패키지 설치 작업 수행
- pre_build : 로그인 단계 또는 종속적 설치와 같은 빌드 전에 실행할 명령
- build : 컴파일 또는 실행 테스트와 같은 빌드 실행
- post_build : 빌드의 성공 또는 실패에 따라 명령 실행
3. atifacts : S3에 아티팩트 생성 및 저장

---
## AWS CodeBuild 실습 
1. Visual Studio Code 설치
https://code.visualstudio.com/ 접속
Download for Windows 를 클릭해서 다운로드 받고 설치 

2. AWS CLI 설치
https://aws.amazon.com/cli/   접속
화면 오른쪽의 Windows '64비트' 클릭
=> AWSCLIV2.msi 파일 다운로드 및 설치

3. github 접속

4. 새 리포지토리를 생성
NEW -> Repository name : codebuild-sample -> create repository

5. 화면이 전환되면
Quick setup 아래에서 'creating a new file' 클릭 -> README.md 파일명을 입력하고 적당한 내용을 입력한다. 그리고 오른쪽 상단의 Commit changes 버튼 클릭.

6. Commit changes 창이 뜨고, Commit message : Create README.md, Extended description 부분은 비워두고 commit changes 버튼 클릭

7. 생성된 리포지토리 페이지에서 제공되는 HTTPS URL 주소를 복사
예) https://github.com/jinsim2/codebuild-sample.git

8. Visual Studio Code로 이동 -> 상단의 Terminal 탭을 클릭 -> New terminal을 클릭해서 터미널창 열기.
cd d:
git clone https://github.com/jinsim2/codebuild-sample.git

9. 프로젝트 디렉터리 이동
cd codebuild-sample

10. 샘플 프로젝트를 만들기 위해 nodejs 사이트로 이동
https://nodejs.org/en/ 접속 -> Download -> Prebuilt Installer -> Download Node.js v20.18.0
=> node-v20.18.0-x64.msi 다운로드 됨
=> 설치 경로  C:\Program Files\nodejs\
Tools for Native Modules 단계에서 □Automatically install... 체크 후 Next, Install

11. 설치 후 VS Code 터미널에서 
node -v
npm -v
명령어로 설치 확인 및 버전 확인.

12. Vue.js 설치
npm install vue
=> node_modules 폴더와 package-lock.json, package.json 파일 생성

13. vue cli 설치
npm install -g @vue/cli

14. 설치가 완료되면 vue 명령어를 입력
※ 만약, vue를 입력 시 오류가 발생하면 Window PowerShell 실행 정책 때문.

> 검색창에서 Windows PowerShell 검색 우클릭, 관리자 권한으로 실행 
> -> Get-ExecutionPolicy 명령어를 통해 현재 실행 정책을 확인 
> -> Restricted 설정을 RemoteSigned 설정으로 변경해야 함.
`set-executionpolicy remotesigned `
-> 'y' 를 입력하여 변경

변경 사항을 적용하기 위해 VS Code를 완전히 닫았다가 다시 실행
터미널을 열고 'vue' 명령어 입력

15. Vue.js create 명령어를 활용하여 Sample 프로젝트를 생성
vue create .
Generate project in current directory? (Y/n) y
> Default ([Vue 3] babel, eslint) (엔터)

16. npm run serve 터미널 실행
웹 브라우저에서 localhost:8080 을 주소창에 입력하면 화면이 보임!
서버 중지는 Ctrl + C -> y, 확인이 되었으면 서버 중지

17. Vue 샘플 프로젝트를 확인했다면 Github에 push
git add .
git commit -m "CodeBuild Sample Source Commit"
git push

18. github로 페이지로 돌아가서 새로고침(F5)을 하고 push 된 내용 확인

19. AWS 페이지로 돌아가서 검색창에 S3 검색 -> 즐겨찾기 추가 -> 대시보드로 이동
버킷만들기
- 버킷 이름 : codebuild-sample-test
- □ '모든 퍼블릭 액세스 차단' 체크 해제
  □ 현재 설정으로 ... 알고 있습니다 항목 체크
[버킷 만들기] 클릭

20. 만들어진 버킷을 클릭 -> 속성탭 클릭 -> 스크롤을 밑으로 내려 '정적 웹 사이트 호스팅' [편집] 클릭
- 정적 웹 사이트 호스팅 : 활성화 변경
- 인덱스 문서 : index.html
[변경 사항 저장] 클릭

21. 권한탭 -> 버킷 정책 [편집] 클릭
-------------------------------------
```
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Sid": "PublicReadGetObject",
            "Effect": "Allow",
            "Principal": "*",
            "Action": [
                "s3:GetObject"    
            ],
            "Resource": [
                "arn:aws:s3:::codebuild-sample-test/*"    
            ]
        }    
        
    ]
}
```
-------------------------------------------------