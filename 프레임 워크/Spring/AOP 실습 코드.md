## pom.xml
```xml
<!-- https://mvnrepository.com/artifact/org.springframework/spring-test -->
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-test</artifactId>
	<version>${org.springframework-version}</version>
	<scope>test</scope>
</dependency>

<!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
<dependency>
	<groupId>org.projectlombok</groupId>
	<artifactId>lombok</artifactId>
	<version>1.18.0</version>
	<scope>provided</scope>
</dependency>
<!-- 버전 변경 -->
<!-- Test -->
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.12</version>
			<scope>test</scope>
		</dependency>       
```

## src/main/java

### SampleService.java
```java
import org.springframework.stereotype.Service;

@Service
public class SampleService {
	public Integer doAdd(String str1, String str2) throws Exception{
		
		return Integer.parseInt(str1) + Integer.parseInt(str1);
	}
}

```
### LogAdvice.java
```java
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


import lombok.extern.log4j.Log4j;


@Aspect
@Log4j
@Component
public class LogAdvice {
	@Before("execution(* com.itwillbs.service.SampleService*.*(..))")
	public void logBefore() {
		log.info("================");
	}
	
	@Before("execution(* com.itwillbs.service.SampleService*.doAdd(String, String)) && args(str1,str2)")
	public void logBeforeWithParam(String str1, String str2) {
		log.info("str1 = " + str1);
		log.info("str2 = " + str2);
	}
	
	@After(value = "execution(* com.itwillbs.service.SampleService*.*(..))")
	public void logAfter() {
		log.info("After ==================");
	}
	
	//@Around에서 예외 처리를 해서 동작하지 않음
	@AfterThrowing(pointcut = "execution(* com.itwillbs.service.SampleService*.*(..))", throwing = "exception")
	public void logException(Exception exception) {
		log.info("Exception....!!!!");
		log.info("exception : " + exception);
	}
	
	@Around("execution(* com.itwillbs.service.SampleService*.*(..))")
	public Object logTime(ProceedingJoinPoint pjp) {
		long start = System.currentTimeMillis();
		log.info("Target : "+pjp.getTarget());
		log.info("Param : " + Arrays.toString(pjp.getArgs()));
		
		Object result = null;
		
		try {
			//target code 실행
			result = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		log.info("TIME : " + (end - start));
		
		return result;
	}
}

```

## src/test/java
### SampleServiceTests.java
```java
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class SampleServiceTests {
	
	@Setter(onMethod_ = @Autowired)
	private SampleService sampleService;
	
	@Test
	public void testClass() {
		log.info(sampleService);
		log.info(sampleService.getClass().getName());
	}
	
	@Test
	public void testAdd() throws Exception{
		log.info(sampleService.doAdd("123", "456"));
	}
	
	@Test
	public void testAddError() throws Exception{
		log.info(sampleService.doAdd("123", "ABC"));
	}
}
```