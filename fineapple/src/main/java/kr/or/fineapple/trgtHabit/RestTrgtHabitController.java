package kr.or.fineapple.trgtHabit;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.or.fineapple.domain.TrgtHabit;
import kr.or.fineapple.domain.User;
import kr.or.fineapple.service.trgtHabit.TrgtHabitService;

@RestController
@RequestMapping("/trgtHabit/*")
public class RestTrgtHabitController {
	
	@Autowired
	@Qualifier("trgtHabitServiceImpl")
	private TrgtHabitService trgtHabitService;
	
	public RestTrgtHabitController() {
		System.out.println(this.getClass());
	}

	@RequestMapping(value="json/getTrgtHabit", method=RequestMethod.POST)
	public ModelAndView getTrgtHabit(@RequestBody TrgtHabit trgtHabit, @RequestBody LocalDate date) {
	
		System.out.println("/trgtHabit/json/getTrgtHabit : POST");

		TrgtHabit returnTrgtHabit = trgtHabitService.getTrgtHabit(trgtHabit.getUserId(), date, trgtHabit.getTrgtHabitCateNo());
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("trgtHabit", returnTrgtHabit);
		mav.setViewName("trgtHabit/getTrgtHabit.html");
		
		return mav;
	}
	
	@RequestMapping(value="json/addTrgtHabit", method=RequestMethod.POST)
	public ModelAndView addTrgtHabit(@RequestBody TrgtHabit trgtHabit) {
		
		System.out.println("/trgtHabit/json/addTrgtHabit : POST");

		trgtHabitService.addTrgtHabit(trgtHabit.getUserId(), trgtHabit);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("trgtHabit", trgtHabit);
		mav.addObject("date", LocalDate.now());
		mav.setViewName("redirect:/trgtHabit/json/getTrgtHabit");
		
		return mav;
	}
	
}
