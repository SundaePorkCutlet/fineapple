package kr.or.fineapple.trgtHabit;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.or.fineapple.domain.TrgtHabit;
import kr.or.fineapple.domain.common.Timer;
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
	public TrgtHabit getTrgtHabit(@RequestBody TrgtHabit trgtHabit) {
	
		System.out.println("/trgtHabit/json/getTrgtHabit : POST");
		////조회를 위한 service 호출
		TrgtHabit returnTrgtHabit = trgtHabitService.getTrgtHabit(trgtHabit.getUserId(), trgtHabit.getViewDate(), trgtHabit.getTrgtHabitCateNo());
	
		//결과가 null일때(목표 습관 진행중인 건수가 없을때)
		if(returnTrgtHabit != null) {		
			////성공일수 출력 위한 연산 Logic
			//시작일자와 오늘일자의 차
			LocalDateTime trgtHabitStartDate = returnTrgtHabit.getTrgtHabitStartDate().atStartOfDay();
			int trgtHabitSuccDayCount = (int)Duration.between(trgtHabitStartDate, LocalDate.now().atStartOfDay()).toDays();
			returnTrgtHabit.setTrgtHabitSuccDayCount(trgtHabitSuccDayCount);
		}
		return returnTrgtHabit;		
	}
	
	@RequestMapping(value="json/addTrgtHabit", method=RequestMethod.POST)
	public void addTrgtHabit(@RequestBody TrgtHabit trgtHabit) {
		  
		System.out.println("/trgtHabit/json/addTrgtHabit : POST");
		//서비스 시작을 위한 service 호출	  
		trgtHabitService.addTrgtHabit(trgtHabit.getUserId(), trgtHabit);
		
	}
	 
	@RequestMapping(value="json/endTrgtHabit", method=RequestMethod.POST)
	public void endTrgtHabit(@RequestBody TrgtHabit trgthabit) {
		
		System.out.println("/trgtHabit/json/endTrgtHabit : POST");
		////목표 성공 일수 초기화를 위한 서비스 호출
		trgtHabitService.endTrgtHabit(trgthabit.getTrgtHabitServiceNo());
	}
	
	@RequestMapping(value="json/getCurrentTime")
	public Timer getCurrentTime() {
		
		System.out.println("/trgtHabit/json/getCurrentTime");
		
		Timer time = new Timer();
		time.setHour(LocalTime.now().getHour());
		time.setMin(LocalTime.now().getMinute());
		time.setSec(LocalTime.now().getSecond());
		
		System.out.println(time);
		return time;
	}
}
