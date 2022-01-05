package kr.or.fineapple.trgtHabit;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.or.fineapple.domain.TrgtHabit;
import kr.or.fineapple.domain.User;
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
		
		////사용자에게 보여지는 첫 화면에 필요한 정보 조회(목표 중 커피, 일일 수분섭취량 정보, 사용자정의 목표 진행중일 경우 이름)
		String userId = ((User)session.getAttribute("user")).getUserId();
		LocalDate date = LocalDate.now();
		
		////리턴할 ModelAndView 생성
		ModelAndView mav = new ModelAndView();
		
		for(int i=1; i<=4; i++) {
			//조회를 위한 service 호출
			int trgtHabitCateNo = i;
			TrgtHabit trgtHabit = trgtHabitService.getTrgtHabit(userId, date, trgtHabitCateNo);
			
			if(trgtHabit != null) {		
				////성공일수 출력 위한 연산 Logic
				//시작일자와 오늘일자의 차
				LocalDateTime trgtHabitStartDate = trgtHabit.getTrgtHabitStartDate().atStartOfDay();
				int trgtHabitSuccDayCount = (int)Duration.between(trgtHabitStartDate, LocalDate.now().atStartOfDay()).toDays();
				//결과를 trgtHabit 도메인에 셋팅
				trgtHabit.setTrgtHabitSuccDayCount(trgtHabitSuccDayCount+1);
				
				//model에 담을 keyName 셋팅
				if(trgtHabit.getTrgtHabitCateNo()==1) {
					mav.addObject("coffee", trgtHabit);
				}else if(trgtHabit.getTrgtHabitCateNo()==2) {
					mav.addObject("alcohol", trgtHabit);
				}else if(trgtHabit.getTrgtHabitCateNo()==3) {
					mav.addObject("smoking", trgtHabit);
				}else if(trgtHabit.getTrgtHabitCateNo()==4) {
					mav.addObject("userHabit", trgtHabit);
				}
			} else {
				//사용자가 아직 시작하지 않아 null인 습관인 경우 빈 trgtHabit 객체 생성
				TrgtHabit emptyTrgtHabit = new TrgtHabit();
				emptyTrgtHabit.setTrgtHabitCateNo(i);
				emptyTrgtHabit.setTrgtHabitSuccDayCount(0);
				
				//model에 담을 keyName 셋팅
				if(emptyTrgtHabit.getTrgtHabitCateNo()==1) {
					mav.addObject("coffee", emptyTrgtHabit);
				}else if(emptyTrgtHabit.getTrgtHabitCateNo()==2) {
					mav.addObject("alcohol", emptyTrgtHabit);
				}else if(emptyTrgtHabit.getTrgtHabitCateNo()==3) {
					mav.addObject("smoking", emptyTrgtHabit);
				}else if(emptyTrgtHabit.getTrgtHabitCateNo()==4) {
					emptyTrgtHabit.setTrgtHabitCateName("사용자정의");
					mav.addObject("userHabit", emptyTrgtHabit);
				}
			}
		}

		double userWtrIntake = trgtHabitService.getWtrIntake(userId, date);
		mav.addObject("userWtrIntake", userWtrIntake);
		mav.setViewName("trgtHabit/getTrgtHabit.html");
		
		return mav;
	}
	
	@RequestMapping(value="addUserHabitCateName", method=RequestMethod.POST)
	public TrgtHabit addUserHabitCateName(@RequestBody TrgtHabit userHabit) {
		System.out.println(userHabit.getTrgtHabitCateName());
		return userHabit;
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
