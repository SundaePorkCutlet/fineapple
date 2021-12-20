package kr.or.fineapple.trgtHabit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trgtHabit/*")
public class TrgtHabitController {
	
	@RequestMapping(value="getTrgtHabit")
	public String getTrgtHabit() {
		return "trgtHabit/getTrgtHabit.html";
	}

}
