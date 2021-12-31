package kr.or.fineapple.diary;

import java.time.LocalDate;
import java.time.YearMonth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/diary/*")
public class DiaryController {
	
	@RequestMapping(value="getDiary")
	public String getDiary() {
		
		 LocalDate startDate = YearMonth.now().atDay(1);
		 LocalDate endDate = YearMonth.now().atEndOfMonth();
		
		return "diary/getDiary.html";
	}
	
	@RequestMapping(value="getUserBodyInfo")
	public String getUserBodyInfo() {
		return "diary/getUserBodyInfo.html";
	}

}
