# web.xml
web.xml에서 서블릿 맵핑 되는 방법, 인증이 필요한 URL 등의 정보를 확인한다
모든 web application은 반드시 하나의 web.xml 파일을 가져야 하고 위치는 WEB-INF 폴더 아래에 있다
web.xml 파일의 설정들은 web application 시작 시 메모리에 로딩된다
```jsp
<!-- The definition of the Root Spring Container shared by all Servlets and Filters -->
<context-param>
	<param-name>contextConfigLocation</param-name>
	<param-value>/WEB-INF/spring/root-context.xml</param-value>
</context-param>

<!-- Creates the Spring Container shared by all Servlets and Filters -->
<listener>
	<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>

<!-- Processes application requests -->
<servlet>
	<servlet-name>appServlet</servlet-name>
	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
	<init-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>/WEB-INF/spring/appServlet/servlet-context.xml</param-value>
	</init-param>
	<load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
	<servlet-name>appServlet</servlet-name>
	<url-pattern>/</url-pattern>
</servlet-mapping>

```

1. '/ '모든 가상 주소 요청이 들어오면 => 서블릿 이름 appServlet 찾음
```jsp
	<servlet-mapping>
		<servlet-name>appServlet</servlet-name>
		<url-pattern>/</url-pattern>
	</servlet-mapping>
```
2. 찾음 서블릿 이름 appServlet 에 해당하는  DispatcherServlet 스프링 파일 연결
	   => 서블릿 파일 지정  servlet-context.xml
```jsp
<servlet>
	<servlet-name>appServlet</servlet-name>
	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
	<init-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>/WEB-INF/spring/appServlet/servlet-context.xml</param-value>
	</init-param>
	<load-on-startup>1</load-on-startup>
</servlet>
```
3. 서버가 연결이 되어지면 자동으로 ContextLoaderListener 호출
```jsp
<listener>
	<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
```

4. root-context.xml 파일 지정 연결
```jsp
<context-param>
	<param-name>contextConfigLocation</param-name>
	<param-value>/WEB-INF/spring/root-context.xml</param-value>
</context-param>
```