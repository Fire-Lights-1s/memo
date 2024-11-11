# CodeDeploy 배포 시 에러

[AWS CI CD 구현](<../AWS/AWS CI CD.md>) 을 실습하는 과정에서 
AWS CodeDeploy에서 문제가 발생함

## 문제 확인

### 콘솔의 에러 메세지
>CodeDeploy agent was not able to receive the lifecycle event. Check the CodeDeploy agent logs on your host and make sure the agent is running and can connect to the CodeDeploy server

CodeDeploy에서 이벤트 주기가 가능하지 않으니, 배포하는 서버의 log를 보라는 의미

### EC2 에서 로그 확인 (Amazon Linux 기준)
EC2에서 `/var/log/aws/codedeploy-agent/` 에서 경로의 `codedeploy-agent.log` 확인

>에러 메시지 : InstanceAgent::Plugins::CodeDeployPlugin::CommandPoller: Missing credentials - please check if this instance was started with an IAM instance profile

해당 에러가 발생하게 된 원인

1.  IAM 역할을 지정하지 않고 인스턴스를 실행시킨다.

2. 이후 실행시킨 인스턴스에 CodeDeploy Agent를 설치 한다.

3. 이때 설치된 CodeDeploy에는 해당 역할을 실행 할 수 있는 자격증명이 없기에 위와 같은 에러가 발생 하는 것 입니다.


## 해결

CodeDeploy Agent를 다시 실행 시키면 됩니다.

```routeros
sudo service codedeploy-agent restart
```



>출처: [https://ssue-dev.tistory.com/12#에러 메시지 %3A InstanceAgent%3A%3APlugins%3A%3ACodeDeployPlugin%3A%3ACommandPoller%3A Missing credentials - please check if this instance was started with an IAM instance profile-1](https://ssue-dev.tistory.com/12#%EC%97%90%EB%9F%AC%20%EB%A9%94%EC%8B%9C%EC%A7%80%20%3A%20InstanceAgent%3A%3APlugins%3A%3ACodeDeployPlugin%3A%3ACommandPoller%3A%20Missing%20credentials%20-%20please%20check%20if%20this%20instance%20was%20started%20with%20an%20IAM%20instance%20profile-1) [Why NOT! Can Do IT!:티스토리]