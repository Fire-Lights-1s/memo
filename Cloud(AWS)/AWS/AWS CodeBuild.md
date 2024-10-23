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

