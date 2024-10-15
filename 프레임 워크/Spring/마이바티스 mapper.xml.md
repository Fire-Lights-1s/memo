# Mapper.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.itwillbs.mapper.BoardMapper">
	<select id="getMaxNum" resultType="java.lang.Integer">
	select MAX(num) from board
	</select>
	
	<insert id="insertBoard" >
	insert into board(num, name,subject,content,readCount,date)
	values(#{num}, #{name}, #{subject}, #{content}, #{readCount}, #{date})
	</insert>
	
	<select id="getBoardCount"  resultType="java.lang.Integer">
	select count(*) from board
    <if test="search != null">
    where subject like concat('%',#{search},'%')
    </if>
	</select>
	
	<!-- 동적 쿼리 이용 -->
	<select id="getBoardList" resultType="com.itwillbs.domain.BoardDTO">
	select * from board
    <if test="search != null">
    where subject like concat('%',#{search},'%')
    </if>
	order by num desc
	limit #{startRow}, #{pageSize}
	</select>
</mapper>
```