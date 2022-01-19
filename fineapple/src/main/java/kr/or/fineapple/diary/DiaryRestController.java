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
import kr.or.fineapple.domain.UserServ;
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
		//���߿�..������ ���� ViewDuration�� ����� �������� �����Ҽ� �մ� ���� �־�����ҰŰ���
		
		System.out.println("/diary/json/getDiary : POST");
		
		////�ش� ���� �����ϰ� ������ viewDuration�� ����
		LocalDate startDate = LocalDate.of(year, month, 1);
		LocalDate endDate = startDate.plusDays(startDate.lengthOfMonth()-1);
		viewDuration.setStartDate(startDate);
		viewDuration.setEndDate(endDate);
		
		////�ش� ���� ���� ���� ��ȸ
		//parameter : viewDuration �� userId, startDate, endDate
		List<Object> badgeList = diaryService.getBadgeList(viewDuration);
		////�ش� ���� ��ǥ ���� ���� ���� ��ȸ
		//parameter : viewDuration �� userId, startDate, endDate
		List<Object> trgtHabitList = trgtHabitService.getTrgtHabitList(viewDuration);
		////�ش� ���� ��ǥ �̺�Ʈ ���� ��ȸ
		//parameter : viewDuration �� userId, startDate, endDate
		List<Object> keyEventTitleList = diaryService.getKeyEventTitleList(viewDuration);
		////����� �Ĵ�/� ���� �̿�� ��ǥ ���� ��ȸ
		//parameter : userId
		UserServ userServ = diaryService.getUserServiceDetails(viewDuration.getUserId());
		
		Map<String, Object> map = new HashMap<>();
		map.put("badgeList", badgeList);
		map.put("userServ", userServ);
		map.put("trgtHabitList", trgtHabitList);
		map.put("keyEventTitleList", keyEventTitleList);
		map.put("firstDay", startDate);
		
		return map;
	}
	
	@RequestMapping(value="json/getUserDailyLog", method=RequestMethod.POST)
	public Map<String, Object> getUserDailyLog(@RequestBody ViewDuration viewDuration){
		//viewDuration �� userId, date
		
		////�ش� ���� ���� �������� ��ǥ ���� ���� ���� ���� ��ȸ
		//parameter : viewDuration �� userId, date
		List<Object> trgtHabitList = trgtHabitService.getTrgtHabitList(viewDuration);
		////�ش� ������ ��� ����� �̺�Ʈ ��ȸ
		//parameter : viewDuration �� userId, date
		List<Object> userEventList = diaryService.getUserEventList(viewDuration);
		
		//get���ϽĴ�����
		//get���Ͽ�������
		
		////������ ȹ�� ���� ���� ��ȸ
		//parameter : viewDuration �� userId, startDate, endDate
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
		////������̺�Ʈ ��� ��ȸ�� ���� parameter
		//viewDuration �� userId, eventDate
		return diaryService.getUserEventList(viewDuration);
	}
	
	@RequestMapping(value="json/updateUserEvent", method=RequestMethod.POST)
	public void updateUserEvent(@RequestBody UserEvent userEvent) {
		diaryService.updateUserEvent(userEvent);
	}
	
	@RequestMapping(value="json/updateKeyUserEventStt/{userEventNo}", method=RequestMethod.GET)
	public void updateKeyUserEventStt(@PathVariable int userEventNo) {
		diaryService.updateKeyUserEventStt(userEventNo);
	}
	
	@RequestMapping(value="json/deleteUserEvent/{userEventNo}", method=RequestMethod.GET)
	public void deleteUserEvent(@PathVariable int userEventNo) {
		diaryService.deleteUserEvent(userEventNo);
	}
	
	@RequestMapping(value="json/getUserBodyInfo", method=RequestMethod.POST)
	public List<Object> getUserBodyInfoList(@RequestBody ViewDuration viewDuration) {
		System.out.println("/diary/json/getUserBodyInfo");
		////����� ��ü ��ȭ ���� ��ȸ�� ���� parameter
		//viewDuration �� userId, startDate, endDate
		return diaryService.getUserBodyInfoList(viewDuration);
	}
	
	@RequestMapping(value="json/updateUserBodyInfo", method=RequestMethod.POST)
	public void updateUserBodyInfo(@RequestBody UserBodyInfo userBodyInfo) {
		System.out.println("/diary/json/updateUserBodyInfo");
		System.out.println(userBodyInfo);
		////����� ��ü ���� ������Ʈ�� ���� parameter
		//userBodyInfo �� userId, date, ���� ���ϴ� ��
		diaryService.updateUserBodyInfo(userBodyInfo);
	}
	
//	@RequestMapping(value="json/getWeeklyPaper", method=RequestMethod.POST)
//	public WeeklyPaper getWeeklyPaper(@RequestBody ViewDuration viewDuration) {
//		//viewDuration �� userId, startDate, endDate
//		
//		//�ش� ���� �Ϻ� ���� Į�θ�
//		//�ش� ���� �Ϻ� �Ҹ� Į�θ�
//
//		
//		//�ش� ���� ��ݱٷ�/ü���淮/ü�� ��ȭ
//		//parameter : viewDuration �� userId, startDate, endDate
//		//getUserBodyInfoList
//		//�ڸ�Ʈ***
//		return null;
//	}
	
//	@RequestMapping(value="json/getMonthlyPaper", method=RequestMethod.POST)
//	public MonthlyPaper getMonthlyPaper(@RequestBody ViewDuration viewDuration) {
//		//viewDuration �� userId, startDate, endDate
//		//�ش� ���� �ֺ� ����� ���� Į�θ�
//		//�ش� ���� �ֺ� ����� �Ҹ� Į�θ�
//		//�ش� �� ����� ���� Į�θ�
//		//�ش� �� ����� �Ҹ� Į�θ�
//		//�� ���� Į�θ��� ���� ���� �ֿ� �� ���� ����� ���� Į�θ�
//		//�� �Ҹ� Į�θ��� ���� ���� �ֿ� �� ���� ����� �Ҹ� Į�θ�
//		//�ش� �� �� ���� Į�θ��� ���� ���� ���� �� ���� ��ǥ �̺�Ʈ
//		//�ش� �� �� �Ҹ� Į�θ��� ���� ���� ���� �� ���� ��ǥ �̺�Ʈ
//		
//		////�ش� ���� ���� ȹ�� ����
//		//parameter : viewDuration �� userId, startDate, endDate
//		//getBadgeTotalCount
//		//�ش� ���� ��ݱٷ�/ü���淮/ü�� ��ȭ
//		//parameter : viewDuration �� userId, startDate, endDate
//		//getUserBodyInfoList
//		//�ڸ�Ʈ***
//		return null;
//	}
	
	//update�Ĵܱ��
	//update����

}
