**SELECT SYSDATE FROM DUAL;**  
  
Java에서 위 쿼리의 결과를 NamedParameterJdbcTemplate을 이용하여 java.util.Map<String, Object> 타입으로 반환받으면  
SYSDATE 값을 java.sql.Timestamp 타입으로 반환하는데 다시 이 값을 Jackson을 이용하여 JSON 문자열로 변환하면 아래와 같이 13자리 숫자로 반환된다.  
**{"SYSDATE":1356745844000}**  
  
이 숫자의 의미는 1970-01-01 00:00:00을 기준으로 해당 시점까지 초과한 밀리세컨즈를 숫자로 표현한 Timestamp 값이다.  
이러한 Timestamp 값을 클라이언트에서 JSON 응답으로 받았을 때 JavaScript 상에서는 아래와 같이 Date 타입으로 인식이 가능하다.  
  
**var sysdate = new Date(1356745844000); // Timestamp 값을 가진 Number 타입 값을 Date 타입으로 변환  
var sysdate = Date.create(1356745844000); // Sugar를 이용한 Date 타입 변환**  
  
Date 타입으로 변환 후 아래와 같이 사람이 식별할 있는 값으로 변환할 수 있다.  
  
**sysdate.toLocaleDateString(); // '2012년 12월 29일 토요일' 출력**  
  
만약 '2012-12-29'와 같은 형태로 출력하고 싶다면 JavaScript가 제공하는 API가 없기 때문에 파씽 역할을 수행하는 함수를 직접 작성해야 한다.  
**[Moment.js](http://momentjs.com/)**와 같은 JavaScript Date 라이브러리를 이용하면 이런 번거로움을 최소화할 수 있다.  
내 경우 JavaScript 범용 라이브러리인 **[Sugar](http://sugarjs.com/)**를 이용하여 아래와 같이 출력해봤다.  
  
**sysdate.format('{yyyy}-{MM}-{dd}'); // '2012-12-29' 출력  
sysdate.format('{yyyy}-{MM}-{dd} {hh}:{mm}:{ss}'); // '2012-12-29 10:50:44' 출력**