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
		
		////����ڿ��� �������� ù ȭ�鿡 �ʿ��� ����(��ǥ ����� �̺�Ʈ, ����, ���� ����, ���� ��ǥ �� ����)��ȸ ���� parameter ����
		//userId, ù ȭ���� ����� �������ڿ� �ش��ϴ� ���̹Ƿ� �̴� �Ⱓ
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
		mav.addObject("NavName1", "���̾");
		mav.addObject("NavName2", "���̾ ��ȸ");
		mav.setViewName("diary/getDiary.html");
		
		return mav;
	}
	
	@RequestMapping(value="getUserDailyLog")
	public ModelAndView getUserDailyLog(@RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date, HttpSession session) throws Exception{
		
		System.out.println("/diary/getUserDailyLog : GET");
		System.out.println(date);
		
		//viewDuration �� userId, date ����
		String userId = ((User)session.getAttribute("user")).getUserId();
		ViewDuration viewDuration = new ViewDuration();
		viewDuration.setUserId(userId);
		viewDuration.setDate(date);
		
		////�������� mav ����
		ModelAndView mav = new ModelAndView();
		mav.addObject("viewDate", date);
		mav.addObject("todayDate", LocalDate.now());
		
		////�� ���� ������ ���� ������ �Ǵ��ϱ� ���� Flag ����
		Boolean isUsingDietServ = false;	//�Ĵ� ���� �̿� ����
		Boolean isDietServInfo = false;		//�Ĵ� ���񽺸� �̿��Ѵٸ� ��ȸ�� ������ �����ϴ��� ����
		Boolean isUsingExerServ = false;	//� ���� �̿� ����
		Boolean isExerServInfo = false;		//� ���񽺸� �̿��Ѵٸ� ��ȸ�� ������ �����ϴ��� ����
		
		////�ش� ���� ���� �������� ��ǥ ���� ���� ���� ���� ��ȸ
		//parameter : viewDuration �� userId, date
		List<Object> trgtHabitList = trgtHabitService.getTrgtHabitList(viewDuration);
		////�ش� ������ ��� ����� �̺�Ʈ ��ȸ
		//parameter : viewDuration �� userId, date
		List<Object> userEventList = diaryService.getUserEventList(viewDuration);
		
		////getIntakeRecordList
		//�Ĵܼ��񽺸� �̿��ϴ��� ���� Ȯ�� �� �̿��Ѵٸ� ���� �Ĵ� ��� ��ȸ
		DietServ diet = dietService.getDietService(userId);
		if(diet != null) {
			isUsingDietServ = true;
			List<IntakeRecord> intakeRecordListAll = dietService.getIntakeRecordListForDiary(date, date, diet.getUserServiceNo());
			
			//�Ĵ� ����� �����ϴ� ���
			if(!intakeRecordListAll.isEmpty()) { 
				isDietServInfo = true;
				//�ش� ��¥ �� ���� ���� �Ļ� ������ ���
				List<IntakeRecord> breakfastIntakeRecordList = new ArrayList<IntakeRecord>();
				List<IntakeRecord> lunchIntakeRecordList = new ArrayList<IntakeRecord>();
				List<IntakeRecord> dinnerIntakeRecordList = new ArrayList<IntakeRecord>();
				List<IntakeRecord> snackIntakeRecordList = new ArrayList<IntakeRecord>();
				List<IntakeRecord> supperIntakeRecordList = new ArrayList<IntakeRecord>();
				for (IntakeRecord intakeRecord : intakeRecordListAll) {
					if(intakeRecord.getMeal().equals("��ħ")) {
						breakfastIntakeRecordList.add(intakeRecord);
					}else if(intakeRecord.getMeal().equals("����")) {
						lunchIntakeRecordList.add(intakeRecord);
					}else if(intakeRecord.getMeal().equals("����")) {
						dinnerIntakeRecordList.add(intakeRecord);
					}else if(intakeRecord.getMeal().equals("����")) {
						snackIntakeRecordList.add(intakeRecord);
					}else if(intakeRecord.getMeal().equals("�߽�")) {
						supperIntakeRecordList.add(intakeRecord);
					}
				}
				mav.addObject("breakfast", breakfastIntakeRecordList);
				mav.addObject("lunch", lunchIntakeRecordList);
				mav.addObject("dinner", dinnerIntakeRecordList);
				mav.addObject("snack", snackIntakeRecordList);
				mav.addObject("supper", supperIntakeRecordList);
				
				if(breakfastIntakeRecordList != null) {
					double totalIntakeKcal = 0.0;	//�ش� ������ �� ���� Į�θ�
					double totalIntakeCarb = 0.0;	//�ش� ������ �� ���� ź��ȭ��
					double totalIntakeProtein = 0.0;	//�ش� ������ �� ���� ����
					double totalIntakeFat = 0.0;	//�ش� ������ �� ���� ����
					Map<String, Object> bfTotal = new HashMap<>();
					for(IntakeRecord item : breakfastIntakeRecordList) {
						totalIntakeKcal += item.getFood().getFoodKcal();
						totalIntakeCarb += item.getFood().getFoodCarb();
						totalIntakeProtein += item.getFood().getFoodProtein();
						totalIntakeFat += item.getFood().getFoodFat();
					}
					bfTotal.put("totalIntakeKcal", totalIntakeKcal);
					bfTotal.put("totalIntakeCarb", totalIntakeCarb);
					bfTotal.put("totalIntakeProtein", totalIntakeProtein);
					bfTotal.put("totalIntakeFat", totalIntakeFat);
					mav.addObject("bfTotal", bfTotal);
				}
				if(lunchIntakeRecordList != null) {
					double totalIntakeKcal = 0.0;	//�ش� ������ �� ���� Į�θ�
					double totalIntakeCarb = 0.0;	//�ش� ������ �� ���� ź��ȭ��
					double totalIntakeProtein = 0.0;	//�ش� ������ �� ���� ����
					double totalIntakeFat = 0.0;	//�ش� ������ �� ���� ����
					Map<String, Object> lunchTotal = new HashMap<>();
					for(IntakeRecord item : lunchIntakeRecordList) {
						totalIntakeKcal += item.getFood().getFoodKcal();
						totalIntakeCarb += item.getFood().getFoodCarb();
						totalIntakeProtein += item.getFood().getFoodProtein();
						totalIntakeFat += item.getFood().getFoodFat();
					}
					lunchTotal.put("totalIntakeKcal", totalIntakeKcal);
					lunchTotal.put("totalIntakeCarb", totalIntakeCarb);
					lunchTotal.put("totalIntakeProtein", totalIntakeProtein);
					lunchTotal.put("totalIntakeFat", totalIntakeFat);
					mav.addObject("lunchTotal", lunchTotal);
				}
				if(dinnerIntakeRecordList != null) {
					double totalIntakeKcal = 0.0;	//�ش� ������ �� ���� Į�θ�
					double totalIntakeCarb = 0.0;	//�ش� ������ �� ���� ź��ȭ��
					double totalIntakeProtein = 0.0;	//�ش� ������ �� ���� ����
					double totalIntakeFat = 0.0;	//�ش� ������ �� ���� ����
					Map<String, Object> dinnerTotal = new HashMap<>();
					for(IntakeRecord item : dinnerIntakeRecordList) {
						totalIntakeKcal += item.getFood().getFoodKcal();
						totalIntakeCarb += item.getFood().getFoodCarb();
						totalIntakeProtein += item.getFood().getFoodProtein();
						totalIntakeFat += item.getFood().getFoodFat();
					}
					dinnerTotal.put("totalIntakeKcal", totalIntakeKcal);
					dinnerTotal.put("totalIntakeCarb", totalIntakeCarb);
					dinnerTotal.put("totalIntakeProtein", totalIntakeProtein);
					dinnerTotal.put("totalIntakeFat", totalIntakeFat);
					mav.addObject("dinnerTotal", dinnerTotal);
				}
				if(snackIntakeRecordList != null) {
					double totalIntakeKcal = 0.0;	//�ش� ������ �� ���� Į�θ�
					double totalIntakeCarb = 0.0;	//�ش� ������ �� ���� ź��ȭ��
					double totalIntakeProtein = 0.0;	//�ش� ������ �� ���� ����
					double totalIntakeFat = 0.0;	//�ش� ������ �� ���� ����
					Map<String, Object> snackTotal = new HashMap<>();
					for(IntakeRecord item : snackIntakeRecordList) {
						totalIntakeKcal += item.getFood().getFoodKcal();
						totalIntakeCarb += item.getFood().getFoodCarb();
						totalIntakeProtein += item.getFood().getFoodProtein();
						totalIntakeFat += item.getFood().getFoodFat();
					}
					snackTotal.put("totalIntakeKcal", totalIntakeKcal);
					snackTotal.put("totalIntakeCarb", totalIntakeCarb);
					snackTotal.put("totalIntakeProtein", totalIntakeProtein);
					snackTotal.put("totalIntakeFat", totalIntakeFat);
					mav.addObject("snackTotal", snackTotal);
				}
				if(supperIntakeRecordList != null) {
					double totalIntakeKcal = 0.0;	//�ش� ������ �� ���� Į�θ�
					double totalIntakeCarb = 0.0;	//�ش� ������ �� ���� ź��ȭ��
					double totalIntakeProtein = 0.0;	//�ش� ������ �� ���� ����
					double totalIntakeFat = 0.0;	//�ش� ������ �� ���� ����
					Map<String, Object> supperTotal = new HashMap<>();
					for(IntakeRecord item : supperIntakeRecordList) {
						totalIntakeKcal += item.getFood().getFoodKcal();
						totalIntakeCarb += item.getFood().getFoodCarb();
						totalIntakeProtein += item.getFood().getFoodProtein();
						totalIntakeFat += item.getFood().getFoodFat();
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
		//����񽺸� �̿��ϴ��� ���� Ȯ�� �� �̿��Ѵٸ� ���� ��� ��� ��ȸ
		ExerServ exer = exerService.getUserService(userId);
		if(exer != null) {
			isUsingExerServ = true;
			List burnningRecordList = exerService.getBurnningRecordListForDiary(date, date, exer.getUserServiceNo());
			//� ����� �����ϴ� ���
			if(!burnningRecordList.isEmpty()) { 
				isExerServInfo = true;
				mav.addObject("burnningRecordList", burnningRecordList);
				System.out.println(burnningRecordList);
			}
		}
		
		////diet �Ǵ� exer ���񽺸� �̿��ϴ� ���
		//1. ȸ���� �Ĵ� ����/� ���� ��ǥ ���� ��ȸ
		//2. �ش��� �� ���� Į�θ��� �Ҹ� Į�θ� ��ȸ
		//3. ȸ���� ���� �̿� ��ǥ�޼��� ���� �� ��ȸ
		if(diet != null || exer != null) {
			viewDuration.setStartDate(date);
			viewDuration.setEndDate(date);
			Achievement achievement = new Achievement();

			//1. ȸ���� �Ĵ� ����/� ���� ��ǥ ���� ��ȸ
			UserServ userServ = diaryService.getUserServiceDetails(userId);
			
			if(diet != null) {
				//2. �ش��� �� ���� Į�θ� ��ȸ
				TotalRecord totalDietRecord = dietService.getTotalDietRecord(viewDuration);
				mav.addObject("totalDietRecord", totalDietRecord);
				//3. ȸ���� ���� �̿� ��ǥ�޼��� ���� �� ��ȸ
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
				//2. �ش��� �� �Ҹ� Į�θ� ��ȸ
				TotalRecord totalExerRecord = exerService.getTotalExerRecord(date, date, userServ.getUserServiceNo());
				mav.addObject("totalExerRecord", totalExerRecord);
				//3. ȸ���� ���� �̿� ��ǥ�޼��� ���� �� ��ȸ
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

		////������ ȹ�� ���� ���� ��ȸ
		//parameter : viewDuration �� userId, startDate, endDate
		LocalDate startDate = viewDuration.getDate().with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
		LocalDate endDate = viewDuration.getDate().with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
		viewDuration.setStartDate(startDate);
		viewDuration.setEndDate(endDate);
		Badge badgeCount = diaryService.getBadgeTotalCount(viewDuration);
		
		////View���� ��ȸ�� Flag
		mav.addObject("isUsingDietServ", isUsingDietServ);
		mav.addObject("isDietServInfo", isDietServInfo);
		mav.addObject("isUsingExerServ", isUsingExerServ);
		mav.addObject("isExerServInfo", isExerServInfo);
		
		
		mav.addObject("trgtHabitList", trgtHabitList);
		mav.addObject("userEventList", userEventList);
		mav.addObject("badgeCount", badgeCount);
		mav.addObject("NavName1", "���̾ ��ȸ");
		mav.addObject("NavName2", "�Ϻ� �� ���� ��ȸ");
		mav.addObject("user", session.getAttribute("user"));
		mav.setViewName("diary/getUserDailyLog.html");
		
		System.out.println(date);
		System.out.println(trgtHabitList);
		System.out.println(userEventList);
		System.out.println(badgeCount);

		return mav;
	}
	
//	@RequestMapping(value="getWeeklyPaper")
//	public ModelAndView getWeeklyPaper(HttpSession session) throws Exception {
//		System.out.println("/diary/getWeeklyPaper");
//		
//		////����ڿ��� �������� ù ȭ�鿡 �ʿ��� ���� ��ȸ ���� parameter
//		//��ȸ ���� ���� ���� ��� ��ȸ�� ���� viewDuration �� userId, startDate, endDate ����
//		String userId = ((User)session.getAttribute("user")).getUserId();
//		LocalDate startDate = LocalDate.now().with(TemporalAdjusters.previous(DayOfWeek.SUNDAY));
//		LocalDate endDate = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY));
//		ViewDuration viewDuration = new ViewDuration();
//		viewDuration.setUserId(userId);
//		viewDuration.setStartDate(startDate);
//		viewDuration.setEndDate(endDate);
//		System.out.println(startDate);
//		System.out.println(endDate);
//		
//		////�������� mav ����
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("startDate", startDate);
//		mav.addObject("endDate", endDate);
//		
//		//�ش� ���� �Ϻ� ���� Į�θ�
//		////getIntakeRecordList
//		//�Ĵܼ��񽺸� �̿��ϴ��� ���� Ȯ�� �� �̿��Ѵٸ� ���� �Ĵ� ��� ��ȸ
//		DietServ diet = dietService.getDietService(userId);
//		if(diet != null) {
//			List intakeRecordList = dietService.getIntakeRecordListForDiary(startDate, endDate, diet.getUserServiceNo());
//			mav.addObject("intakeRecordList", intakeRecordList);
//			System.out.println(intakeRecordList);
//		}
//		//�ش� ���� �Ϻ� �Ҹ� Į�θ�
//		////getBurnningRecordList
//		//����񽺸� �̿��ϴ��� ���� Ȯ�� �� �̿��Ѵٸ� ���� ��� ��� ��ȸ
//		ExerServ exer = exerService.getUserService(userId);
//		if(exer != null) {
//			List burnningRecordList = exerService.getBurnningRecordListForDiary(startDate, endDate, exer.getUserServiceNo());
//			mav.addObject("burnningRecordList", burnningRecordList);
//			System.out.println(burnningRecordList);
//		}
//
//		//�ش� ���� ��ݱٷ�/ü���淮/ü�� ��ȭ
//		//getUserBodyInfoList
//		List<Object> userBodyInfoList = diaryService.getUserBodyInfoList(viewDuration);
//		mav.addObject("userBodyInfoList", userBodyInfoList);
//		System.out.println(userBodyInfoList);
//		
//		//�ڸ�Ʈ***
//
//		mav.addObject("NavName1", "���̾");
//		mav.addObject("NavName2", "�ְ� ����Ʈ");
//		mav.setViewName("diary/getWeeklyPaper.html");
//		
//		return mav;
//	}
	
//	@RequestMapping(value="getMontlyPaper")
//	public ModelAndView getMontlyPaper(HttpSession session) throws Exception {
//		System.out.println("/diary/getMonthlyPaper");
//		
//		////����ڿ��� �������� ù ȭ�鿡 �ʿ��� ������ȸ ���� parameter ����
//		//��ȸ ���� ���� ���� ��� ��ȸ ���� viewDuration �� userId, startDate, endDate ����
//		String userId = ((User)session.getAttribute("user")).getUserId();
//		LocalDate startDate = YearMonth.now().minusMonths(1).atDay(1);
//		LocalDate endDate = YearMonth.now().minusMonths(1).atEndOfMonth();
//		ViewDuration viewDuration = new ViewDuration();
//		viewDuration.setUserId(userId);
//		viewDuration.setStartDate(startDate);
//		viewDuration.setEndDate(endDate);
//		System.out.println(startDate);
//		System.out.println(endDate);
//		
//		////�������� mav ����
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("startDate", startDate);
//		mav.addObject("endDate", endDate);
//		
//		////�� ���� ������ ���� ������ �Ǵ��ϱ� ���� Flag ����
//		Boolean isUsingDietServ = false;	//�Ĵ� ���� �̿� ����
//		Boolean isDietServInfo = false;		//�Ĵ� ���񽺸� �̿��Ѵٸ� ��ȸ�� ������ �����ϴ��� ����
//		Boolean isUsingExerServ = false;	//� ���� �̿� ����
//		Boolean isExerServInfo = false;		//� ���񽺸� �̿��Ѵٸ� ��ȸ�� ������ �����ϴ��� ����
//		
//		////�Ĵ� ���� ���� ��ȸ
//		//�ش� ���� �ֺ� ����� ���� Į�θ�, �ش� �� ����� ���� Į�θ�
//		//�� ���� Į�θ��� ���� ���� �ֿ� �� ���� ����� ���� Į�θ�
//		//�ش� �� �� ���� Į�θ��� ���� ���� ���� �� ���� ��ǥ �̺�Ʈ
//		DietServ diet = dietService.getDietService(userId);
//		if(diet != null) {
//			//1. �Ĵܼ��񽺸� �̿��ϴ��� ���� Ȯ�� ��
//			isUsingDietServ = true;
//			//2. �ش� ���� �� ���� ��ȸ
//			double totalIntakeKcal = dietService.getTotalDietRecord(viewDuration).getTotalIntakeKcal();
//			int daysCount = dietService.getDaysCount(viewDuration);
//			
//			//����� 1���� ������ ��쿡�� ����
//			if(totalIntakeKcal != 0 && daysCount != 0) {
//				isDietServInfo = true;
//
//				//�ش� �� ����� ���� Į�θ� ���
//				double avgDailyIntakeKcalOfMonth = totalIntakeKcal/daysCount;
//				mav.addObject("avgDailyIntakeKcalOfMonth", avgDailyIntakeKcalOfMonth);
//				System.out.println(avgDailyIntakeKcalOfMonth);
//				
//				//�ش� �� �� ���� Į�θ��� ���� ���� ���� �� ���� ��ǥ �̺�Ʈ ��ȸ
//				TotalRecord theHighestIntakeKcalDayInfo = dietService.getTheHighestIntakeKcalDay(viewDuration);		//����, Į�θ�
//				UserEvent theHighestIntakeKcalDayUserEvent = diaryService.getTheHighestIntakeKcalDayUserEvent(viewDuration);	//�ش� ���� ��ǥ �̺�Ʈ
//				mav.addObject("theHighestIntakeKcalDayInfo", theHighestIntakeKcalDayInfo);
//				mav.addObject("theHighestIntakeKcalDayUserEvent", theHighestIntakeKcalDayUserEvent);
//				System.out.println(theHighestIntakeKcalDayInfo);
//				System.out.println(theHighestIntakeKcalDayUserEvent);
//						
//				//3. �ش� ���� �� ���� ��ȸ
//				//���� �� ��¥ �Ҵ�
//				ViewDuration firstWeek = new ViewDuration();
//				ViewDuration secondWeek = new ViewDuration();
//				ViewDuration thirdWeek = new ViewDuration();
//				ViewDuration fourthWeek = new ViewDuration();
//				ViewDuration fifthWeek = new ViewDuration();
//				ViewDuration sixthWeek = new ViewDuration();
//				List<ViewDuration> weekDates = new ArrayList<ViewDuration>();
//				LocalDate weekStartDate = startDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
//				
//				firstWeek.setUserId(userId);
//				firstWeek.setStartDate(weekStartDate);
//				firstWeek.setEndDate(weekStartDate.plusDays(6));
//				weekDates.add(firstWeek);
//				
//				secondWeek.setUserId(userId);
//				secondWeek.setStartDate(weekStartDate.plusDays(7));
//				secondWeek.setEndDate(weekStartDate.plusDays(13));
//				weekDates.add(secondWeek);
//				
//				thirdWeek.setUserId(userId);
//				thirdWeek.setStartDate(weekStartDate.plusDays(14));
//				thirdWeek.setEndDate(weekStartDate.plusDays(20));
//				weekDates.add(thirdWeek);
//				
//				fourthWeek.setUserId(userId);
//				fourthWeek.setStartDate(weekStartDate.plusDays(21));
//				fourthWeek.setEndDate(weekStartDate.plusDays(27));
//				weekDates.add(fourthWeek);
//				
//				if(weekStartDate.plusDays(28).isBefore(endDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY)))) {
//					fifthWeek.setUserId(userId);
//					fifthWeek.setStartDate(weekStartDate.plusDays(28));
//					fifthWeek.setEndDate(weekStartDate.plusDays(34));
//					weekDates.add(fifthWeek);
//					
//					if(weekStartDate.plusDays(35).isBefore(endDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY)))) {
//						sixthWeek.setUserId(userId);
//						sixthWeek.setStartDate(weekStartDate.plusDays(35));
//						sixthWeek.setEndDate(weekStartDate.plusDays(41));
//						weekDates.add(sixthWeek);
//					}
//				}
//				
//				Map avgDailyIntakeKcalOfWeekMap = new HashMap<ViewDuration, Double>();
//				//�ش� ���� �ֺ� ����� ���� Į�θ� ���
//				for(ViewDuration week:weekDates) {
//					totalIntakeKcal = dietService.getTotalDietRecord(week).getTotalIntakeKcal();
//					daysCount = dietService.getDaysCount(week);
//					
//					if(daysCount == 0) {
//						double avgDailyIntakeKcalOfWeek = 0.0;
//						avgDailyIntakeKcalOfWeekMap.put(week, avgDailyIntakeKcalOfWeek);
//					}
//					double avgDailyIntakeKcalOfWeek = totalIntakeKcal/daysCount;						
//					avgDailyIntakeKcalOfWeekMap.put(week, avgDailyIntakeKcalOfWeek);
//				}
//				mav.addObject("avgDailyIntakeKcalOfWeekMap", avgDailyIntakeKcalOfWeekMap);
//		
//				////�� ���� Į�θ��� ���� ���� �ֿ� �� ���� ����� ���� Į�θ�(JavaScript���� ó��)
//			}//���� 1���� ������ �����ҽ� ���� end
//		}//�Ĵ� ���� ���� ��ȸ end
//		
//		////� ���� ���� ��ȸ
//		//�ش� ���� �ֺ� ����� �Ҹ� Į�θ�, �ش� �� ����� �Ҹ� Į�θ�
//		//�� �Ҹ� Į�θ��� ���� ���� �ֿ� �� ���� ����� �Ҹ� Į�θ�
//		//�ش� �� �� �Ҹ� Į�θ��� ���� ���� ���� �� ���� ��ǥ �̺�Ʈ
//		ExerServ exer = exerService.getUserService(userId);
//		if(exer != null) {
//			//1. ����񽺸� �̿��ϴ��� ���� Ȯ�� ��
//			isUsingExerServ = true;
//			//2. �ش� ���� �� ���� ��ȸ
//			double totalBurnningKcal = dietService.getTotalDietRecord(viewDuration).getTotalIntakeKcal();
//			int daysCount = dietService.getDaysCount(viewDuration);
//			
//			//����� 1���� ������ ��쿡�� ����
//			if(totalBurnningKcal != 0 && daysCount != 0) {
//			}
//			
//			
//			//�ش� ���� �ֺ� ����� �Ҹ� Į�θ�
//			//�ش� �� ����� �Ҹ� Į�θ�
//			//�� �Ҹ� Į�θ��� ���� ���� �ֿ� �� ���� ����� �Ҹ� Į�θ�
//			//�ش� �� �� �Ҹ� Į�θ��� ���� ���� ���� �� ���� ��ǥ �̺�Ʈ
//		}		
//		
//		
//		
//		////�ش� ���� ���� ȹ�� ����
//		//parameter : viewDuration �� userId, startDate, endDate
//		//getBadgeTotalCount
//		//�ش� ���� ��ݱٷ�/ü���淮/ü�� ��ȭ
//		//parameter : viewDuration �� userId, startDate, endDate
//		//getUserBodyInfoList
//		//�ڸ�Ʈ***
//		
//		mav.addObject("NavName1", "���̾");
//		mav.addObject("NavName2", "���� ����Ʈ");
//		
//		mav.setViewName("diary/getMonthlyPaper.html");
//		
//		return mav;
//	}
	
	@RequestMapping(value="getUserBodyInfo")
	public ModelAndView getUserBodyInfo(HttpSession session) {
		
		System.out.println("/diary/getUserBodyInfo");
		
		////����� ��ü ��ȭ ���� �Է� ��¸� ���� parameter
		//session �� userId�� ���� ���� ���� �ְ� ��� ��ȸ
		String userId = ((User)session.getAttribute("user")).getUserId();
		LocalDateTime endDate = LocalDate.now().atStartOfDay();
		LocalDateTime startDate = endDate.minus(Duration.ofDays(6));
		//viewDuration �� userId, startDate, endDate, date ����
		ViewDuration viewDuration = new ViewDuration();
		viewDuration.setUserId(userId);
		viewDuration.setEndDate(LocalDate.now());
		viewDuration.setStartDate(startDate.toLocalDate());
		System.out.println(viewDuration.getStartDate());
		
		List<Object> list = diaryService.getUserBodyInfoList(viewDuration);
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("userBodyInfo", list);
		mav.addObject("NavName1", "���̾");
		mav.addObject("NavName2", "��ü ��ȭ ���� ��ȸ");
		mav.setViewName("diary/getUserBodyInfo.html");
		System.out.println(list);
		return mav;
	}

}
