package kr.or.fineapple.diary;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.or.fineapple.domain.Badge;
import kr.or.fineapple.domain.MonthlyPaper;
import kr.or.fineapple.domain.UserBodyInfo;
import kr.or.fineapple.domain.UserEvent;
import kr.or.fineapple.domain.WeeklyPaper;
import kr.or.fineapple.domain.common.ViewDuration;
import kr.or.fineapple.service.diary.DiaryService;
import kr.or.fineapple.service.diet.DietService;
import kr.or.fineapple.service.exer.ExerService;
import kr.or.fineapple.service.trgtHabit.TrgtHabitService;

@RestController
@RequestMapping("/diary/*")
public class DiaryRestController {
	
	@Autowired
	@Qualifier("diaryServiceImpl")
	private DiaryService diaryService;
	
	@Autowired
	@Qualifier("dietServiceImpl")
	private DietService dietService;
	
	@Autowired
	@Qualifier("ExerServiceImpl")
	private ExerService exerService;
	
	@Autowired
	@Qualifier("trgtHabitServiceImpl")
	private TrgtHabitService trgtHabitService;
	
	
	public DiaryRestController() {
		System.out.println(this.getClass());
	}

	@RequestMapping(value="json/getDiary/{year}/{month}", method=RequestMethod.POST)
	public Map<String, Object> getDiary(@RequestBody ViewDuration viewDuration, @PathVariable int year, @PathVariable int month) {
		//나중에..보안을 위해 ViewDuration에 사용자 본인임을 증명할수 잇는 값을 넣어줘야할거같음
		
		////해당 월의 시작일과 종료일 viewDuration에 세팅
		LocalDate startDate = LocalDate.of(year, month, 1);
		LocalDate endDate = startDate.plusDays(startDate.lengthOfMonth()-1);
		viewDuration.setStartDate(startDate);
		viewDuration.setEndDate(endDate);
		
		////해당 월의 뱃지 정보 조회
		//parameter : viewDuration 내 userId, startDate, endDate
		List<Object> badgeList = diaryService.getBadgeList(viewDuration);
		////해당 월의 목표 습관 서비스 정보 조회
		//parameter : viewDuration 내 userId, startDate, endDate
		List<Object> trgtHabitList = trgtHabitService.getTrgtHabitList(viewDuration);
		////해당 월의 대표 이벤트 제목 조회
		//parameter : viewDuration 내 userId, startDate, endDate
		List<Object> keyEventList = diaryService.getKeyEventTitleList(viewDuration);
		
		Map<String, Object> map = new HashMap<>();
		map.put("badgeList", badgeList);
		map.put("trgtHabitList", trgtHabitList);
		map.put("keyEventList", keyEventList);
		
		return map;
	}
	
	@RequestMapping(value="json/getUserDailyLog", method=RequestMethod.POST)
	public Map<String, Object> getUserDailyLog(@RequestBody ViewDuration viewDuration){
		//viewDuration 내 userId, date
		
		////해당 일자 기준 진행중인 목표 습관 관리 진행 정보 조회
		//parameter : viewDuration 내 userId, date
		List<Object> trgtHabitList = trgtHabitService.getTrgtHabitList(viewDuration);
		////해당 일자의 모든 사용자 이벤트 조회
		//parameter : viewDuration 내 userId, date
		List<Object> userEventList = diaryService.getUserEventList(viewDuration);
		
		//get일일식단정보
		//get일일운동기록정보
		
		////이주의 획득 뱃지 갯수 조회
		//parameter : viewDuration 내 userId, startDate, endDate
		LocalDate startDate = viewDuration.getDate().with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
		LocalDate endDate = viewDuration.getDate().with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
		viewDuration.setStartDate(startDate);
		viewDuration.setEndDate(endDate);
		Badge badgeCount = diaryService.getBadgeTotalCount(viewDuration);
		
		Map<String, Object> map = new HashMap<>();
		map.put("trgtHabitList", trgtHabitList);
		map.put("userEventList", userEventList);
		map.put("badgeCount", badgeCount);

		return map;
	}
	
	@RequestMapping(value="json/addUserEvent", method=RequestMethod.POST)
	public void addUserEvent(@RequestBody UserEvent userEvent) {
		diaryService.addUserEvent(userEvent);
	}
	
	@RequestMapping(value="json/getUserEvent/{userEventNo}", method=RequestMethod.GET)
	public UserEvent getUserEvent(@PathVariable int userEventNo) {
		return diaryService.getUserEvent(userEventNo);
	}
	
	@RequestMapping(value="json/getUserEventList", method=RequestMethod.POST)
	public List<Object> getUserEventList(@RequestBody ViewDuration viewDuration){
		////사용자이벤트 모두 조회를 위한 parameter
		//viewDuration 내 userId, eventDate
		return diaryService.getUserEventList(viewDuration);
	}
	
	@RequestMapping(value="json/updateUserEvent", method=RequestMethod.POST)
	public void updateUserEvent(UserEvent userEvent) {
		diaryService.updateUserEvent(userEvent);
	}
	
	@RequestMapping(value="json/updateKeyUserEventStt/{userEventNo}", method=RequestMethod.GET)
	public void updateKeyUserEventStt(@PathVariable int userEventNo) {
		diaryService.updateKeyUserEventStt(userEventNo);
	}
	
	@RequestMapping(value="json/deleteUserEvent/{userEventNo}", method=RequestMethod.POST)
	public void deleteUserEvent(@PathVariable int userEventNo) {
		diaryService.deleteUserEvent(userEventNo);
	}
	
	@RequestMapping(value="json/getUserBodyInfo", method=RequestMethod.POST)
	public List<Object> getUserBodyInfoList(@RequestBody ViewDuration viewDuration) {
		System.out.println("/diary/json/getUserBodyInfo");
		////사용자 신체 변화 정보 조회를 위한 parameter
		//viewDuration 내 userId, startDate, endDate
		return diaryService.getUserBodyInfoList(viewDuration);
	}
	
	@RequestMapping(value="json/updateUserBodyInfo", method=RequestMethod.POST)
	public void updateUserBodyInfo(@RequestBody UserBodyInfo userBodyInfo) {
		System.out.println("/diary/json/updateUserBodyInfo");
		System.out.println(userBodyInfo);
		////사용자 신체 정보 업데이트를 위한 parameter
		//userBodyInfo 중 userId, date, 수정 원하는 값
		diaryService.updateUserBodyInfo(userBodyInfo);
	}
	
	@RequestMapping(value="json/getWeeklyPaper", method=RequestMethod.POST)
	public WeeklyPaper getWeeklyPaper(@RequestBody ViewDuration viewDuration) {
		//viewDuration 내 userId, startDate, endDate
		
		//해당 주의 일별 섭취 칼로리
		//해당 주의 일별 소모 칼로리

		
		//해당 주의 골격근량/체지방량/체중 변화
		//parameter : viewDuration 내 userId, startDate, endDate
		//getUserBodyInfoList
		//코멘트***
		return null;
	}
	
	@RequestMapping(value="json/getMonthlyPaper", method=RequestMethod.POST)
	public MonthlyPaper getMonthlyPaper(@RequestBody ViewDuration viewDuration) {
		//viewDuration 내 userId, startDate, endDate
		//해당 월의 주별 일평균 섭취 칼로리
		//해당 월의 주별 일평균 소모 칼로리
		//해당 월 일평균 섭취 칼로리
		//해당 월 일평균 소모 칼로리
		//총 섭취 칼로리가 가장 높은 주와 그 주의 일평균 섭취 칼로리
		//총 소모 칼로리가 가장 높은 주와 그 주의 일평균 소모 칼로리
		//해당 월 중 섭취 칼로리가 가장 높은 날과 그 날의 대표 이벤트
		//해당 월 중 소모 칼로리가 가장 높은 날과 그 날의 대표 이벤트
		
		////해당 월의 뱃지 획득 갯수
		//parameter : viewDuration 내 userId, startDate, endDate
		//getBadgeTotalCount
		//해당 월의 골격근량/체지방량/체중 변화
		//parameter : viewDuration 내 userId, startDate, endDate
		//getUserBodyInfoList
		//코멘트***
		return null;
	}
	
	//update식단기록
	//update운동기록

}
