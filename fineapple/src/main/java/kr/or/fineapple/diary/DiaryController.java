package kr.or.fineapple.diary;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.fineapple.domain.Achievement;
import kr.or.fineapple.domain.Badge;
import kr.or.fineapple.domain.DietServ;
import kr.or.fineapple.domain.ExerServ;
import kr.or.fineapple.domain.IntakeRecord;
import kr.or.fineapple.domain.TotalRecord;
import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.UserEvent;
import kr.or.fineapple.domain.UserServ;
import kr.or.fineapple.domain.common.ViewDuration;
import kr.or.fineapple.service.diary.DiaryService;
import kr.or.fineapple.service.diet.DietService;
import kr.or.fineapple.service.exer.ExerService;
import kr.or.fineapple.service.trgtHabit.TrgtHabitService;

@Controller
@RequestMapping("/diary/*")
public class DiaryController {
	
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
	
	@RequestMapping(value="getDiary")
	public ModelAndView getDiary(HttpSession session) {
		
		System.out.println("/diary/getDiary : GET");
		
		////사용자에게 보여지는 첫 화면에 필요한 정보(대표 사용자 이벤트, 뱃지, 습관 정보, 서비스 목표 상세 정보)조회 위한 parameter 설정
		//userId, 첫 화면이 사용자 접속일자에 해당하는 월이므로 이달 기간
		String userId = ((User)session.getAttribute("user")).getUserId();
		LocalDate startDate = YearMonth.now().atDay(1);
		LocalDate endDate = YearMonth.now().atEndOfMonth();
		ViewDuration viewDuration = new ViewDuration();
		viewDuration.setUserId(userId);
		viewDuration.setStartDate(startDate);
		viewDuration.setEndDate(endDate);
		
		List<Object> keyEventTitleList = diaryService.getKeyEventTitleList(viewDuration);
		List<Object> badgeList = diaryService.getBadgeList(viewDuration);
		List<Object> trgtHabitList = trgtHabitService.getTrgtHabitList(viewDuration);
		UserServ userServ = diaryService.getUserServiceDetails(userId);

		System.out.println(keyEventTitleList);
		System.out.println(badgeList);
		System.out.println(trgtHabitList);
		System.out.println(userServ);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("keyEventTitleList", keyEventTitleList);
		mav.addObject("badgeList", badgeList);
		mav.addObject("userServ", userServ);
		mav.addObject("trgtHabitList", trgtHabitList);
		mav.addObject("NavName1", "다이어리");
		mav.addObject("NavName2", "다이어리 조회");
		mav.setViewName("diary/getDiary.html");
		
		return mav;
	}
	
	@RequestMapping(value="getUserDailyLog")
	public ModelAndView getUserDailyLog(@RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date, HttpSession session) throws Exception{
		
		System.out.println("/diary/getUserDailyLog : GET");
		System.out.println(date);
		
		//viewDuration 내 userId, date 셋팅
		String userId = ((User)session.getAttribute("user")).getUserId();
		ViewDuration viewDuration = new ViewDuration();
		viewDuration.setUserId(userId);
		viewDuration.setDate(date);
		
		////리턴해줄 mav 생성
		ModelAndView mav = new ModelAndView();
		mav.addObject("viewDate", date);
		
		////각 서비스 정보의 존재 유무를 판단하기 위한 Flag 셋팅
		Boolean isUsingDietServ = false;	//식단 서비스 이용 여부
		Boolean isDietServInfo = false;		//식단 서비스를 이용한다면 조회할 정보가 존재하는지 여부
		Boolean isUsingExerServ = false;	//운동 서비스 이용 여부
		Boolean isExerServInfo = false;		//운동 서비스를 이용한다면 조회할 정보가 존재하는지 여부
		
		////해당 일자 기준 진행중인 목표 습관 관리 진행 정보 조회
		//parameter : viewDuration 내 userId, date
		List<Object> trgtHabitList = trgtHabitService.getTrgtHabitList(viewDuration);
		////해당 일자의 모든 사용자 이벤트 조회
		//parameter : viewDuration 내 userId, date
		List<Object> userEventList = diaryService.getUserEventList(viewDuration);
		
		////getIntakeRecordList
		//식단서비스를 이용하는지 여부 확인 후 이용한다면 일일 식단 기록 조회
		DietServ diet = dietService.getDietService(userId);
		if(diet != null) {
			isUsingDietServ = true;
			List<IntakeRecord> intakeRecordListAll = dietService.getIntakeRecordListForDiary(date, date, diet.getUserServiceNo());
			
			//식단 기록이 존재하는 경우
			if(!intakeRecordListAll.isEmpty()) { 
				isDietServInfo = true;
				//해당 날짜 매 끼니 별로 식사 나눠서 담기
				List<IntakeRecord> breakfastIntakeRecordList = new ArrayList<IntakeRecord>();
				List<IntakeRecord> lunchIntakeRecordList = new ArrayList<IntakeRecord>();
				List<IntakeRecord> dinnerIntakeRecordList = new ArrayList<IntakeRecord>();
				List<IntakeRecord> snackIntakeRecordList = new ArrayList<IntakeRecord>();
				List<IntakeRecord> supperIntakeRecordList = new ArrayList<IntakeRecord>();
				for (IntakeRecord intakeRecord : intakeRecordListAll) {
					if(intakeRecord.getMeal().equals("아침")) {
						breakfastIntakeRecordList.add(intakeRecord);
					}else if(intakeRecord.getMeal().equals("점심")) {
						lunchIntakeRecordList.add(intakeRecord);
					}else if(intakeRecord.getMeal().equals("저녁")) {
						dinnerIntakeRecordList.add(intakeRecord);
					}else if(intakeRecord.getMeal().equals("간식")) {
						snackIntakeRecordList.add(intakeRecord);
					}else if(intakeRecord.getMeal().equals("야식")) {
						supperIntakeRecordList.add(intakeRecord);
					}
				}
				mav.addObject("breakfast", breakfastIntakeRecordList);
				mav.addObject("lunch", lunchIntakeRecordList);
				mav.addObject("dinner", dinnerIntakeRecordList);
				mav.addObject("snack", snackIntakeRecordList);
				mav.addObject("supper", supperIntakeRecordList);
				
				if(breakfastIntakeRecordList != null) {
					double totalIntakeKcal = 0.0;	//해당 끼니의 총 섭취 칼로리
					double totalIntakeCarb = 0.0;	//해당 끼니의 총 섭취 탄수화물
					double totalIntakeProtein = 0.0;	//해당 끼니의 총 섭취 딘벡질
					double totalIntakeFat = 0.0;	//해당 끼니의 총 섭취 지방
					Map<String, Object> bfTotal = new HashMap<>();
					for(IntakeRecord item : breakfastIntakeRecordList) {
						totalIntakeKcal += item.getFoodKcal();
						totalIntakeCarb += item.getFoodCarb();
						totalIntakeProtein += item.getFoodProtein();
						totalIntakeFat += item.getFoodFat();
					}
					bfTotal.put("totalIntakeKcal", totalIntakeKcal);
					bfTotal.put("totalIntakeCarb", totalIntakeCarb);
					bfTotal.put("totalIntakeProtein", totalIntakeProtein);
					bfTotal.put("totalIntakeFat", totalIntakeFat);
					mav.addObject("bfTotal", bfTotal);
				}
				if(lunchIntakeRecordList != null) {
					double totalIntakeKcal = 0.0;	//해당 끼니의 총 섭취 칼로리
					double totalIntakeCarb = 0.0;	//해당 끼니의 총 섭취 탄수화물
					double totalIntakeProtein = 0.0;	//해당 끼니의 총 섭취 딘벡질
					double totalIntakeFat = 0.0;	//해당 끼니의 총 섭취 지방
					Map<String, Object> lunchTotal = new HashMap<>();
					for(IntakeRecord item : lunchIntakeRecordList) {
						totalIntakeKcal += item.getFoodKcal();
						totalIntakeCarb += item.getFoodCarb();
						totalIntakeProtein += item.getFoodProtein();
						totalIntakeFat += item.getFoodFat();
					}
					lunchTotal.put("totalIntakeKcal", totalIntakeKcal);
					lunchTotal.put("totalIntakeCarb", totalIntakeCarb);
					lunchTotal.put("totalIntakeProtein", totalIntakeProtein);
					lunchTotal.put("totalIntakeFat", totalIntakeFat);
					mav.addObject("lunchTotal", lunchTotal);
				}
				if(dinnerIntakeRecordList != null) {
					double totalIntakeKcal = 0.0;	//해당 끼니의 총 섭취 칼로리
					double totalIntakeCarb = 0.0;	//해당 끼니의 총 섭취 탄수화물
					double totalIntakeProtein = 0.0;	//해당 끼니의 총 섭취 딘벡질
					double totalIntakeFat = 0.0;	//해당 끼니의 총 섭취 지방
					Map<String, Object> dinnerTotal = new HashMap<>();
					for(IntakeRecord item : dinnerIntakeRecordList) {
						totalIntakeKcal += item.getFoodKcal();
						totalIntakeCarb += item.getFoodCarb();
						totalIntakeProtein += item.getFoodProtein();
						totalIntakeFat += item.getFoodFat();
					}
					dinnerTotal.put("totalIntakeKcal", totalIntakeKcal);
					dinnerTotal.put("totalIntakeCarb", totalIntakeCarb);
					dinnerTotal.put("totalIntakeProtein", totalIntakeProtein);
					dinnerTotal.put("totalIntakeFat", totalIntakeFat);
					mav.addObject("dinnerTotal", dinnerTotal);
				}
				if(snackIntakeRecordList != null) {
					double totalIntakeKcal = 0.0;	//해당 끼니의 총 섭취 칼로리
					double totalIntakeCarb = 0.0;	//해당 끼니의 총 섭취 탄수화물
					double totalIntakeProtein = 0.0;	//해당 끼니의 총 섭취 딘벡질
					double totalIntakeFat = 0.0;	//해당 끼니의 총 섭취 지방
					Map<String, Object> sncakTotal = new HashMap<>();
					for(IntakeRecord item : snackIntakeRecordList) {
						totalIntakeKcal += item.getFoodKcal();
						totalIntakeCarb += item.getFoodCarb();
						totalIntakeProtein += item.getFoodProtein();
						totalIntakeFat += item.getFoodFat();
					}
					sncakTotal.put("totalIntakeKcal", totalIntakeKcal);
					sncakTotal.put("totalIntakeCarb", totalIntakeCarb);
					sncakTotal.put("totalIntakeProtein", totalIntakeProtein);
					sncakTotal.put("totalIntakeFat", totalIntakeFat);
					mav.addObject("sncakTotal", sncakTotal);
				}
				if(supperIntakeRecordList != null) {
					double totalIntakeKcal = 0.0;	//해당 끼니의 총 섭취 칼로리
					double totalIntakeCarb = 0.0;	//해당 끼니의 총 섭취 탄수화물
					double totalIntakeProtein = 0.0;	//해당 끼니의 총 섭취 딘벡질
					double totalIntakeFat = 0.0;	//해당 끼니의 총 섭취 지방
					Map<String, Object> supperTotal = new HashMap<>();
					for(IntakeRecord item : supperIntakeRecordList) {
						totalIntakeKcal += item.getFoodKcal();
						totalIntakeCarb += item.getFoodCarb();
						totalIntakeProtein += item.getFoodProtein();
						totalIntakeFat += item.getFoodFat();
					}
					supperTotal.put("totalIntakeKcal", totalIntakeKcal);
					supperTotal.put("totalIntakeCarb", totalIntakeCarb);
					supperTotal.put("totalIntakeProtein", totalIntakeProtein);
					supperTotal.put("totalIntakeFat", totalIntakeFat);
					mav.addObject("supperTotal", supperTotal);
				}				
				System.out.println(breakfastIntakeRecordList);
				System.out.println(lunchIntakeRecordList);
				System.out.println(dinnerIntakeRecordList);
				System.out.println(snackIntakeRecordList);
				System.out.println(supperIntakeRecordList);

			}
		}

		////getBurnningRecordList
		//운동서비스를 이용하는지 여부 확인 후 이용한다면 일일 운동량 기록 조회
		ExerServ exer = exerService.getUserService(userId);
		if(exer != null) {
			isUsingExerServ = true;
			List burnningRecordList = exerService.getBurnningRecordListForDiary(date, date, exer.getUserServiceNo());
			//운동 기록이 존재하는 경우
			if(!burnningRecordList.isEmpty()) { 
				isExerServInfo = true;
				mav.addObject("burnningRecordList", burnningRecordList);
				System.out.println(burnningRecordList);
			}
		}
		
		////diet 또는 exer 서비스를 이용하는 경우
		//1. 회원의 식단 서비스/운동 서비스 목표 정보 조회
		//2. 해당일 총 섭취 칼로리와 소모 칼로리 조회
		//3. 회원의 서비스 이용 목표달성률 산출 및 조회
		if(diet != null || exer != null) {
			viewDuration.setStartDate(date);
			viewDuration.setEndDate(date);
			Achievement achievement = new Achievement();

			//1. 회원의 식단 서비스/운동 서비스 목표 정보 조회
			UserServ userServ = diaryService.getUserServiceDetails(userId);
			
			if(diet != null) {
				//2. 해당일 총 섭취 칼로리 조회
				TotalRecord totalDietRecord = dietService.getTotalDietRecord(viewDuration);
				mav.addObject("totalDietRecord", totalDietRecord);
				//3. 회원의 서비스 이용 목표달성률 산출 및 조회
				if(totalDietRecord != null) {
					achievement = diaryService.getDietAchievement(viewDuration);
					System.out.println(achievement);
				} else {
					achievement.setIntakeKcalInPercentage(0);
					achievement.setIntakeCarbInPercentage(0);
					achievement.setIntakeProteinInPercentage(0);
					achievement.setIntakeFatInPercentage(0);
				}
			}
			if(exer != null) {
				//2. 해당일 총 소모 칼로리 조회
				TotalRecord totalExerRecord = exerService.getTotalExerRecord(date, date, userServ.getUserServiceNo());
				mav.addObject("totalExerRecord", totalExerRecord);
				//3. 회원의 서비스 이용 목표달성률 산출 및 조회
				if(totalExerRecord != null ) {
					Integer burnningKcalInPercentage = diaryService.getExerAchievement(viewDuration);
					achievement.setBurnningKcalInPercentage(burnningKcalInPercentage);					
					System.out.println(burnningKcalInPercentage);
					System.out.println(achievement);					
				} else {
					achievement.setBurnningKcalInPercentage(0);
				}
			}
			mav.addObject("achievement", achievement);
			mav.addObject("userServ", userServ);
			
			System.out.println(achievement);
			System.out.println(userServ);
		}

		////이주의 획득 뱃지 갯수 조회
		//parameter : viewDuration 내 userId, startDate, endDate
		LocalDate startDate = viewDuration.getDate().with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
		LocalDate endDate = viewDuration.getDate().with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
		viewDuration.setStartDate(startDate);
		viewDuration.setEndDate(endDate);
		Badge badgeCount = diaryService.getBadgeTotalCount(viewDuration);
		
		////View에서 조회할 Flag
		mav.addObject("isUsingDietServ", isUsingDietServ);
		mav.addObject("isDietServInfo", isDietServInfo);
		mav.addObject("isUsingExerServ", isUsingExerServ);
		mav.addObject("isExerServInfo", isExerServInfo);
		
		
		mav.addObject("trgtHabitList", trgtHabitList);
		mav.addObject("userEventList", userEventList);
		mav.addObject("badgeCount", badgeCount);
		mav.addObject("NavName1", "다이어리 조회");
		mav.addObject("NavName2", "일별 상세 정보 조회");
		mav.addObject("user", session.getAttribute("user"));
		mav.setViewName("diary/getUserDailyLog.html");
		
		System.out.println(date);
		System.out.println(trgtHabitList);
		System.out.println(userEventList);
		System.out.println(badgeCount);

		return mav;
	}
	
	@RequestMapping(value="getWeeklyPaper")
	public ModelAndView getWeeklyPaper(HttpSession session) throws Exception {
		System.out.println("/diary/getWeeklyPaper");
		
		////사용자에게 보여지는 첫 화면에 필요한 정보 조회 위한 parameter
		//조회 일자 기준 전주 기록 조회를 위해 viewDuration 내 userId, startDate, endDate 셋팅
		String userId = ((User)session.getAttribute("user")).getUserId();
		LocalDate startDate = LocalDate.now().with(TemporalAdjusters.previous(DayOfWeek.SUNDAY));
		LocalDate endDate = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY));
		ViewDuration viewDuration = new ViewDuration();
		viewDuration.setUserId(userId);
		viewDuration.setStartDate(startDate);
		viewDuration.setEndDate(endDate);
		System.out.println(startDate);
		System.out.println(endDate);
		
		////리턴해줄 mav 생성
		ModelAndView mav = new ModelAndView();
		mav.addObject("startDate", startDate);
		mav.addObject("endDate", endDate);
		
		//해당 주의 일별 섭취 칼로리
		////getIntakeRecordList
		//식단서비스를 이용하는지 여부 확인 후 이용한다면 일일 식단 기록 조회
		DietServ diet = dietService.getDietService(userId);
		if(diet != null) {
			List intakeRecordList = dietService.getIntakeRecordListForDiary(startDate, endDate, diet.getUserServiceNo());
			mav.addObject("intakeRecordList", intakeRecordList);
			System.out.println(intakeRecordList);
		}
		//해당 주의 일별 소모 칼로리
		////getBurnningRecordList
		//운동서비스를 이용하는지 여부 확인 후 이용한다면 일일 운동량 기록 조회
		ExerServ exer = exerService.getUserService(userId);
		if(exer != null) {
			List burnningRecordList = exerService.getBurnningRecordListForDiary(startDate, endDate, exer.getUserServiceNo());
			mav.addObject("burnningRecordList", burnningRecordList);
			System.out.println(burnningRecordList);
		}

		//해당 주의 골격근량/체지방량/체중 변화
		//getUserBodyInfoList
		List<Object> userBodyInfoList = diaryService.getUserBodyInfoList(viewDuration);
		mav.addObject("userBodyInfoList", userBodyInfoList);
		System.out.println(userBodyInfoList);
		
		//코멘트***

		mav.addObject("NavName1", "다이어리");
		mav.addObject("NavName2", "주간 레포트");
		mav.setViewName("diary/getWeeklyPaper.html");
		
		return mav;
	}
	
	@RequestMapping(value="getMontlyPaper")
	public ModelAndView getMontlyPaper(HttpSession session) throws Exception {
		System.out.println("/diary/getMonthlyPaper");
		
		////사용자에게 보여지는 첫 화면에 필요한 정보조회 위한 parameter 설정
		//조회 일자 기준 전월 기록 조회 위해 viewDuration 내 userId, startDate, endDate 셋팅
		String userId = ((User)session.getAttribute("user")).getUserId();
		LocalDate startDate = YearMonth.now().minusMonths(1).atDay(1);
		LocalDate endDate = YearMonth.now().minusMonths(1).atEndOfMonth();
		ViewDuration viewDuration = new ViewDuration();
		viewDuration.setUserId(userId);
		viewDuration.setStartDate(startDate);
		viewDuration.setEndDate(endDate);
		System.out.println(startDate);
		System.out.println(endDate);
		
		////리턴해줄 mav 생성
		ModelAndView mav = new ModelAndView();
		mav.addObject("startDate", startDate);
		mav.addObject("endDate", endDate);
		
		////각 서비스 정보의 존재 유무를 판단하기 위한 Flag 셋팅
		Boolean isUsingDietServ = false;	//식단 서비스 이용 여부
		Boolean isDietServInfo = false;		//식단 서비스를 이용한다면 조회할 정보가 존재하는지 여부
		Boolean isUsingExerServ = false;	//운동 서비스 이용 여부
		Boolean isExerServInfo = false;		//운동 서비스를 이용한다면 조회할 정보가 존재하는지 여부
		
		////식단 서비스 정보 조회
		//해당 월의 주별 일평균 섭취 칼로리, 해당 월 일평균 섭취 칼로리
		//총 섭취 칼로리가 가장 높은 주와 그 주의 일평균 섭취 칼로리
		//해당 월 중 섭취 칼로리가 가장 높은 날과 그 날의 대표 이벤트
		DietServ diet = dietService.getDietService(userId);
		if(diet != null) {
			//1. 식단서비스를 이용하는지 여부 확인 후
			isUsingDietServ = true;
			//2. 해당 월의 일 정보 조회
			double totalIntakeKcal = dietService.getTotalDietRecord(viewDuration).getTotalIntakeKcal();
			int daysCount = dietService.getDaysCount(viewDuration);
			
			//기록이 1개라도 존재할 경우에만 실행
			if(totalIntakeKcal != 0 && daysCount != 0) {
				isDietServInfo = true;

				//해당 월 일평균 섭취 칼로리 계산
				double avgDailyIntakeKcalOfMonth = totalIntakeKcal/daysCount;
				mav.addObject("avgDailyIntakeKcalOfMonth", avgDailyIntakeKcalOfMonth);
				System.out.println(avgDailyIntakeKcalOfMonth);
				
				//해당 월 중 섭취 칼로리가 가장 높은 날과 그 날의 대표 이벤트 조회
				TotalRecord theHighestIntakeKcalDayInfo = dietService.getTheHighestIntakeKcalDay(viewDuration);		//일자, 칼로리
				UserEvent theHighestIntakeKcalDayUserEvent = diaryService.getTheHighestIntakeKcalDayUserEvent(viewDuration);	//해당 일의 대표 이벤트
				mav.addObject("theHighestIntakeKcalDayInfo", theHighestIntakeKcalDayInfo);
				mav.addObject("theHighestIntakeKcalDayUserEvent", theHighestIntakeKcalDayUserEvent);
				System.out.println(theHighestIntakeKcalDayInfo);
				System.out.println(theHighestIntakeKcalDayUserEvent);
						
				//3. 해당 월의 주 정보 조회
				//주차 별 날짜 할당
				ViewDuration firstWeek = new ViewDuration();
				ViewDuration secondWeek = new ViewDuration();
				ViewDuration thirdWeek = new ViewDuration();
				ViewDuration fourthWeek = new ViewDuration();
				ViewDuration fifthWeek = new ViewDuration();
				ViewDuration sixthWeek = new ViewDuration();
				List<ViewDuration> weekDates = new ArrayList<ViewDuration>();
				LocalDate weekStartDate = startDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
				
				firstWeek.setUserId(userId);
				firstWeek.setStartDate(weekStartDate);
				firstWeek.setEndDate(weekStartDate.plusDays(6));
				weekDates.add(firstWeek);
				
				secondWeek.setUserId(userId);
				secondWeek.setStartDate(weekStartDate.plusDays(7));
				secondWeek.setEndDate(weekStartDate.plusDays(13));
				weekDates.add(secondWeek);
				
				thirdWeek.setUserId(userId);
				thirdWeek.setStartDate(weekStartDate.plusDays(14));
				thirdWeek.setEndDate(weekStartDate.plusDays(20));
				weekDates.add(thirdWeek);
				
				fourthWeek.setUserId(userId);
				fourthWeek.setStartDate(weekStartDate.plusDays(21));
				fourthWeek.setEndDate(weekStartDate.plusDays(27));
				weekDates.add(fourthWeek);
				
				if(weekStartDate.plusDays(28).isBefore(endDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY)))) {
					fifthWeek.setUserId(userId);
					fifthWeek.setStartDate(weekStartDate.plusDays(28));
					fifthWeek.setEndDate(weekStartDate.plusDays(34));
					weekDates.add(fifthWeek);
					
					if(weekStartDate.plusDays(35).isBefore(endDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY)))) {
						sixthWeek.setUserId(userId);
						sixthWeek.setStartDate(weekStartDate.plusDays(35));
						sixthWeek.setEndDate(weekStartDate.plusDays(41));
						weekDates.add(sixthWeek);
					}
				}
				
				Map avgDailyIntakeKcalOfWeekMap = new HashMap<ViewDuration, Double>();
				//해당 월의 주별 일평균 섭취 칼로리 계산
				for(ViewDuration week:weekDates) {
					totalIntakeKcal = dietService.getTotalDietRecord(week).getTotalIntakeKcal();
					daysCount = dietService.getDaysCount(week);
					
					if(daysCount == 0) {
						double avgDailyIntakeKcalOfWeek = 0.0;
						avgDailyIntakeKcalOfWeekMap.put(week, avgDailyIntakeKcalOfWeek);
					}
					double avgDailyIntakeKcalOfWeek = totalIntakeKcal/daysCount;						
					avgDailyIntakeKcalOfWeekMap.put(week, avgDailyIntakeKcalOfWeek);
				}
				mav.addObject("avgDailyIntakeKcalOfWeekMap", avgDailyIntakeKcalOfWeekMap);
		
				////총 섭취 칼로리가 가장 높은 주와 그 주의 일평균 섭취 칼로리(JavaScript에서 처리)
			}//월에 1개라도 정보가 존재할시 실행 end
		}//식단 서비스 정보 조회 end
		
		////운동 서비스 정보 조회
		//해당 월의 주별 일평균 소모 칼로리, 해당 월 일평균 소모 칼로리
		//총 소모 칼로리가 가장 높은 주와 그 주의 일평균 소모 칼로리
		//해당 월 중 소모 칼로리가 가장 높은 날과 그 날의 대표 이벤트
		ExerServ exer = exerService.getUserService(userId);
		if(exer != null) {
			//1. 운동서비스를 이용하는지 여부 확인 후
			isUsingExerServ = true;
			//2. 해당 월의 일 정보 조회
			double totalBurnningKcal = dietService.getTotalDietRecord(viewDuration).getTotalIntakeKcal();
			int daysCount = dietService.getDaysCount(viewDuration);
			
			//기록이 1개라도 존재할 경우에만 실행
			if(totalBurnningKcal != 0 && daysCount != 0) {
			}
			
			
			//해당 월의 주별 일평균 소모 칼로리
			//해당 월 일평균 소모 칼로리
			//총 소모 칼로리가 가장 높은 주와 그 주의 일평균 소모 칼로리
			//해당 월 중 소모 칼로리가 가장 높은 날과 그 날의 대표 이벤트
		}		
		
		
		
		////해당 월의 뱃지 획득 갯수
		//parameter : viewDuration 내 userId, startDate, endDate
		//getBadgeTotalCount
		//해당 월의 골격근량/체지방량/체중 변화
		//parameter : viewDuration 내 userId, startDate, endDate
		//getUserBodyInfoList
		//코멘트***
		
		mav.addObject("NavName1", "다이어리");
		mav.addObject("NavName2", "월간 레포트");
		
		mav.setViewName("diary/getMonthlyPaper.html");
		
		return mav;
	}
	
	@RequestMapping(value="getUserBodyInfo")
	public ModelAndView getUserBodyInfo(HttpSession session) {
		
		System.out.println("/diary/getUserBodyInfo");
		
		////사용자 신체 변화 정보 입력 출력를 위한 parameter
		//session 내 userId와 오늘 일자 기준 주간 기록 조회
		String userId = ((User)session.getAttribute("user")).getUserId();
		LocalDateTime endDate = LocalDate.now().atStartOfDay();
		LocalDateTime startDate = endDate.minus(Duration.ofDays(6));
		//viewDuration 내 userId, startDate, endDate, date 셋팅
		ViewDuration viewDuration = new ViewDuration();
		viewDuration.setUserId(userId);
		viewDuration.setEndDate(LocalDate.now());
		viewDuration.setStartDate(startDate.toLocalDate());
		System.out.println(viewDuration.getStartDate());
		
		List<Object> list = diaryService.getUserBodyInfoList(viewDuration);
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("userBodyInfo", list);
		mav.addObject("NavName1", "다이어리");
		mav.addObject("NavName2", "신체 변화 추이 조회");
		mav.setViewName("diary/getUserBodyInfo.html");
		System.out.println(list);
		return mav;
	}

}
