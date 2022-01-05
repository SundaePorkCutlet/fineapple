package kr.or.fineapple.trgtHabit;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.or.fineapple.domain.TrgtHabit;
import kr.or.fineapple.service.trgtHabit.TrgtHabitService;

@RestController
@RequestMapping("/trgtHabit/*")
public class TrgtHabitRestController {
	
	@Autowired
	@Qualifier("trgtHabitServiceImpl")
	private TrgtHabitService trgtHabitService;
	
	public TrgtHabitRestController() {
		System.out.println(this.getClass());
	}

	@RequestMapping(value="json/getTrgtHabit", method=RequestMethod.POST)
	public TrgtHabit getTrgtHabit(@RequestBody TrgtHabit trgtHabit) {
	
		System.out.println("/trgtHabit/json/getTrgtHabit : POST");
		////조회를 위한 service 호출
		TrgtHabit returnTrgtHabit = trgtHabitService.getTrgtHabit(trgtHabit.getUserId(), trgtHabit.getViewDate(), trgtHabit.getTrgtHabitCateNo());
	
		//결과가 null이 아닐때
		if(returnTrgtHabit != null) {		
			////성공일수 출력 위한 연산 Logic
			//시작일자와 오늘일자의 차
			LocalDateTime trgtHabitStartDate = returnTrgtHabit.getTrgtHabitStartDate().atStartOfDay();
			int trgtHabitSuccDayCount = (int)Duration.between(trgtHabitStartDate, LocalDate.now().atStartOfDay()).toDays();
			returnTrgtHabit.setTrgtHabitSuccDayCount(trgtHabitSuccDayCount+1);
			
			return returnTrgtHabit;		
			
		} else {
			
			TrgtHabit emptyTrgtHabit = new TrgtHabit();
			emptyTrgtHabit.setTrgtHabitCateNo(trgtHabit.getTrgtHabitCateNo());

			return emptyTrgtHabit;
		}
	}
		
	@RequestMapping(value="json/addTrgtHabit", method=RequestMethod.POST)
	public TrgtHabit addTrgtHabit(@RequestBody TrgtHabit trgtHabit) {
		  
		System.out.println("/trgtHabit/json/addTrgtHabit : POST");
		
		//서비스 시작 전 현재 진행 중인지 여부 확인(0개 리턴경우 시작 가능/1 리턴경우 이미 진행중으로 시작불가)
		int usingStt = trgtHabitService.getUsingTrgtHabit(trgtHabit.getUserId(), trgtHabit.getTrgtHabitCateNo());
		
		//서비스 시작을 위한 service 호출	  
		if(usingStt == 0) {
			trgtHabitService.addTrgtHabit(trgtHabit.getUserId(), trgtHabit);
			//시작된 목표 관리 정보 조회를 위한 service 호출
			TrgtHabit returnTrgtHabit = trgtHabitService.getTrgtHabit(trgtHabit.getUserId(), LocalDate.now(), trgtHabit.getTrgtHabitCateNo());
	
			////성공일수 출력 위한 연산 Logic
			//시작일자와 오늘일자의 차
			LocalDateTime trgtHabitStartDate = returnTrgtHabit.getTrgtHabitStartDate().atStartOfDay();
			int trgtHabitSuccDayCount = (int)Duration.between(trgtHabitStartDate, LocalDate.now().atStartOfDay()).toDays();
			returnTrgtHabit.setTrgtHabitSuccDayCount(trgtHabitSuccDayCount+1);
			System.out.println(returnTrgtHabit);
		
			return returnTrgtHabit;
		}
		return null;
	}
	 
	@RequestMapping(value="json/endTrgtHabit", method=RequestMethod.POST)
	public void endTrgtHabit(@RequestBody TrgtHabit trgthabit) {
		
		System.out.println("/trgtHabit/json/endTrgtHabit : POST");
		////목표 성공 일수 초기화를 위한 서비스 호출
		trgtHabitService.endTrgtHabit(trgthabit.getTrgtHabitServiceNo());
	}
	
	@RequestMapping(value="json/updateWtrIntake", method=RequestMethod.POST)
	public Double updateWtrIntake(@RequestBody TrgtHabit trgtHabit) {
		//REST 이용위해 TrgtHabit 도메인에 userWtrIntake 필드 추가하여 사용중(일반 컨트롤러 이용한다면 필요X)
		System.out.println("/trgt/json/updateWtrIntake");
		////사용자로부터 입력받은 수분섭취량 기록후 기록된값 조회하여 리턴
		Double returnUserWtrIntake = trgtHabitService.updateWtrIntake(trgtHabit.getUserId(), trgtHabit.getUserWtrIntake());
		
		return returnUserWtrIntake;
	}
}
