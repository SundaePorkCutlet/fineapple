package kr.or.fineapple.diary;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

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
		mav.setViewName("diary/getDiary.html");
		
		return mav;
	}
	
	@RequestMapping(value="getUserDailyLog")
	public ModelAndView getUserDailyLog(@RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date, HttpSession session) throws Exception{
		
		System.out.println("/diary/getUserDailyLog : GET");
		
		//viewDuration 내 userId, date 셋팅
		String userId = ((User)session.getAttribute("user")).getUserId();
		ViewDuration viewDuration = new ViewDuration();
		viewDuration.setUserId(userId);
		viewDuration.setDate(date);
		
		////리턴해줄 mav 생성
		ModelAndView mav = new ModelAndView();
		mav.addObject("date", date);
		
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
			//해당 날짜 매 끼니 별로 식사 나눠서 담기
			List<IntakeRecord> intakeRecordListAll = dietService.getIntakeRecordListForDiary(date, diet.getUserServiceNo());
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
			System.out.println(breakfastIntakeRecordList);
			System.out.println(lunchIntakeRecordList);
			System.out.println(dinnerIntakeRecordList);
			System.out.println(snackIntakeRecordList);
			System.out.println(supperIntakeRecordList);
		}

		////getBurnningRecordList
		//운동서비스를 이용하는지 여부 확인 후 이용한다면 일일 운동량 기록 조회
		ExerServ exer = exerService.getUserService(userId);
		if(exer != null) {
			List burnningRecordList = exerService.getBurnningRecordListForDiary(date, exer.getUserServiceNo());
			mav.addObject("burnningRecordList", burnningRecordList);
			System.out.println(burnningRecordList);
		}
		
		////diet 또는 exer 서비스를 이용하는 경우
		//1. 뱃지 테이블에서 해당 일 총 섭취 칼로리와 소모 칼로리 조회
		//2. 회원의 식단 서비스/운동 서비스 목표 정보 조회
		//3. 회원의 서비스 이용 목표달성률 산출 및 조회
		if(diet != null || exer != null) {
			viewDuration.setStartDate(date);
			viewDuration.setEndDate(date);
			
			//1. 뱃지 테이블에서 해당 일 총 섭취 칼로리와 소모 칼로리 조회
			List dailyRecordList = diaryService.getBadgeList(viewDuration);
			Badge dailyRecord = (Badge) dailyRecordList.get(0);	//무조건 하나의 레코드만 조회되므로(하루 1개)
			TotalRecord totalRecord = dietService.getTotalDietRecord(viewDuration); //하루 기간 내 총 섭취 영양소 정보 조회
			
			//2. 회원의 식단 서비스/운동 서비스 목표 정보 조회
			UserServ userServ = diaryService.getUserServiceDetails(userId);
			
			//3. 회원의 서비스 이용 목표달성률 산출 및 조회
			Achievement achievement = new Achievement();
			
			if(diet != null) {
				achievement = diaryService.getDietAchievement(viewDuration);
			}
			if(exer !=null) {
				Integer burnningKcalInPercentage = diaryService.getExerAchievement(viewDuration);
				achievement.setBurnningKcalInPercentage(burnningKcalInPercentage);
			}
			
			mav.addObject("userServ", userServ);
			mav.addObject("dailyRecord", dailyRecord);
			mav.addObject("totalRecord", totalRecord);
			mav.addObject("achievement", achievement);
			
			System.out.println(achievement);
			System.out.println(dailyRecord);
			System.out.println(userServ);
		}

		////이주의 획득 뱃지 갯수 조회
		//parameter : viewDuration 내 userId, startDate, endDate
		LocalDate startDate = viewDuration.getDate().with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
		LocalDate endDate = viewDuration.getDate().with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
		viewDuration.setStartDate(startDate);
		viewDuration.setEndDate(endDate);
		Badge badgeCount = diaryService.getBadgeTotalCount(viewDuration);
		
		mav.addObject("trgtHabitList", trgtHabitList);
		mav.addObject("userEventList", userEventList);
		mav.addObject("badgeCount", badgeCount);
		
		mav.addObject("user", session.getAttribute("user"));
		mav.setViewName("diary/getUserDailyLog.html");
		
		System.out.println(date);
		System.out.println(trgtHabitList);
		System.out.println(userEventList);
		System.out.println(badgeCount);

		return mav;
	}
	
	@RequestMapping(value="getUserBodyInfo")
	public ModelAndView getUserBodyInfo(HttpSession session) {
		
		System.out.println("diary/getUserBodyInfo");
		
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
		mav.setViewName("diary/getUserBodyInfo.html");
		mav.addObject("userBodyInfo", list);
		System.out.println(list);
		return mav;
	}

}
