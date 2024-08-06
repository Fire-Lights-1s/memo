## servlet-context.xml
파일을 스캔하고 경로 매핑을함
```jsp
<!-- DispatcherServlet Context: defines this servlet's request-processing infrastructure -->
	
<!-- Enables the Spring MVC @Controller programming model -->
<annotation-driven />

<!-- Handles HTTP GET requests for /resources/** by efficiently serving up static resources in the ${webappRoot}/resources directory -->
<resources mapping="/resources/**" location="/resources/" />

<!-- Resolves views selected for rendering by @Controllers to .jsp resources in the /WEB-INF/views directory -->
<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	<beans:property name="prefix" value="/WEB-INF/views/" />
	<beans:property name="suffix" value=".jsp" />
</beans:bean>

<context:component-scan base-package="com.itwillbs.myweb" />
```
서블릿 지정 => 자동으로 스캔해서 동작하도록 지정
1. 패키지 목록에 있는 파일을 자동으로 스캔 => @ 찾음
```jsp
<context:component-scan base-package="com.itwillbs.myweb" />
```

2. 찾은 @(어노테이션)을 자동으로 동작 => @Controller 동작(주소매핑)
```jsp
<!-- Enables the Spring MVC @Controller programming model -->
<annotation-driven />
```

3. jsp 경로 지정 => 접두사 /WEB-INF/views/ => 파일 이름 => 접미사 .jsp
```jsp
<!-- Resolves views selected for rendering by @Controllers to .jsp resources in the /WEB-INF/views directory -->
<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	<beans:property name="prefix" value="/WEB-INF/views/" />
	<beans:property name="suffix" value=".jsp" />
</beans:bean>
```

4. /resources/ 폴더 안에 이미지, CSS, JS 경로 지정
```jsp
<!-- Handles HTTP GET requests for /resources/** by efficiently serving up static resources in the ${webappRoot}/resources directory -->
<resources mapping="/resources/**" location="/resources/" />
```