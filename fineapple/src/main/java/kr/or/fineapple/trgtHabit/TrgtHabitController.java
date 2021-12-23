package kr.or.fineapple.trgtHabit;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.or.fineapple.domain.TrgtHabit;
import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.common.Timer;
import kr.or.fineapple.service.trgtHabit.TrgtHabitService;

@Controller
@RequestMapping("/trgtHabit/*")
public class TrgtHabitController {
	
	@Autowired
	@Qualifier("trgtHabitServiceImpl")
	private TrgtHabitService trgtHabitService;
	
	public TrgtHabitController() {
		System.out.println(this.getClass());
	}
	
	@RequestMapping(value="getTrgtHabit", method=RequestMethod.GET)
	public ModelAndView getTrgtHabit(HttpSession session) {
	
		System.out.println("/trgtHabit/getTrgtHabit : GET");
		
		////사용자에게 보여지는 가장 첫 화면에 필요한 정보 조회(목표 중 커피, 일일 수분섭취량 정보)
		String userId = ((User)session.getAttribute("user")).getUserId();
		LocalDate date = LocalDate.now();
		int trgtCateNo = 1;
		
		////조회를 위한 service 호출
		TrgtHabit trgtHabit = trgtHabitService.getTrgtHabit(userId, date, trgtCateNo);
		double userWtrIntake = trgtHabitService.getWtrIntake(userId, date);
		
		////리턴할 ModelAndView, Timer 생성
		ModelAndView mav = new ModelAndView();
		Timer time = new Timer();
		
		//결과가 null일때(목표 습관 진행중인 건수가 없을때)
		if(trgtHabit != null) {		
			////성공일수 출력 위한 연산 Logic
			//시작일자와 오늘일자의 차
			LocalDateTime trgtHabitStartDate = trgtHabit.getTrgtHabitStartDate().atStartOfDay();
			int trgtHabitSuccDayCount = (int)Duration.between(trgtHabitStartDate, LocalDate.now().atStartOfDay()).toDays();
			//결과를 trgtHabit 도메인에 셋팅
			trgtHabit.setTrgtHabitSuccDayCount(trgtHabitSuccDayCount);
			//time 출력을 위한 timer 도메인 셋팅
			time.setHour(LocalTime.now().getHour());
			time.setMin(LocalTime.now().getMinute());
			time.setSec(LocalTime.now().getSecond());
			mav.addObject("trgtHabit", trgtHabit);
		} else {
			//빈 trgtHabit 객체 생성
			TrgtHabit emptyTrgtHabit = new TrgtHabit();
			emptyTrgtHabit.setTrgtHabitServiceNo(0);
			emptyTrgtHabit.setTrgtHabitCateNo(1);
			emptyTrgtHabit.setTrgtHabitSuccDayCount(0);
			//timer 도메인 셋팅
			time.setHour(0);
			time.setMin(0);
			time.setMin(0);
			mav.addObject("trgtHabit", emptyTrgtHabit);
		}
		mav.addObject("time", time);
		mav.addObject("userWtrIntake", userWtrIntake);
		mav.setViewName("trgtHabit/getTrgtHabit.html");

		return mav;
	}
	
	@RequestMapping(value="addTrgtHabit", method=RequestMethod.POST)
	public ModelAndView addTrgtHabit(@ModelAttribute TrgtHabit trgtHabit) {
		
		System.out.println("/trgtHabit/addTrgtHabit : POST");

		trgtHabitService.addTrgtHabit(trgtHabit.getUserId(), trgtHabit);
//		
		ModelAndView mav = new ModelAndView();
//		mav.addObject("trgtHabit", trgtHabit);
//		mav.addObject("date", LocalDate.now());
		mav.setViewName("redirect:/trgtHabit/json/getTrgtHabit");
		
		return mav;
	}
	

}
