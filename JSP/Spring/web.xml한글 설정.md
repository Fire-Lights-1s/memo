## web.xml한글 설정
post방식으로 요청 => 톰캣9버전 까지 request 한글처리 설정

```xml
<filter>
	<filter-name>encoding</filter-name>
	<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
	<init-param>
		<param-name>encoding</param-name>
		<param-value>UTF-8</param-value>
	</init-param>
</filter>

<filter-mapping>
	<filter-name>encoding</filter-name>
	<url-pattern>/*</url-pattern>
</filter-mapping>
```