package kr.or.fineapple.diary;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/diary/*")
public class DiaryController {
	
	@RequestMapping(value="getUserBodyInfo")
	public String getUserBodyInfo() {
		return "diary/getUserBodyInfo.html";
	}

}
