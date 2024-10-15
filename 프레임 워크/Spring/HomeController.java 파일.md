## HomeController.java
1. @Controller => 자바파일에 Controller 지정 => 주소매핑 파일
	>파일이 달라져도 @Controller로 지정하면 다 같은 Controller로 인식
1. @RequestMapping 요청주소에 해당하는 실제파일 연결하는 메서드 자동 호출
	=> 가상주소 자동으로 뽑아서 value = "/" 비교, RequestMethod.GET 전송방식 비교
3. 실제 처리작업 메서드 호출
4. 주소변경하면서 이동, 주소변경없이 이동
	>/WEB-INF/views/파일이름.jsp
	/WEB-INF/views/home.jsp
```java
// 1) @Controller => 자바파일에 Controller 지정 => 주소매핑 파일
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	//	2) @RequestMapping 요청주소에 해당하는 실제파일 연결하는 메서드 자동 호출
	//	=> 가상주소 자동으로 뽑아서 value = "/" 비교, RequestMethod.GET 전송방식 비교
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		//3) 실제 처리작업 메서드 호출
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		//스프링에서 제공하는 model에  데이터 담아서 이동 => jsp에서 바로사용 가능 (request 데이터 담아서 이동)
		model.addAttribute("serverTime", formattedDate );
		
		//4) 주소변경하면서 이동, 주소변경없이 이동
		//  /WEB-INF/views/파일이름.jsp
	    //  /WEB-INF/views/home.jsp
		return "home";
	}
}
```
