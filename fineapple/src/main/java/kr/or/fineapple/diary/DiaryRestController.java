package kr.or.fineapple.diary;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.or.fineapple.domain.Diary;
import kr.or.fineapple.domain.MonthlyPaper;
import kr.or.fineapple.domain.UserBodyInfo;
import kr.or.fineapple.domain.UserDailyLog;
import kr.or.fineapple.domain.UserEvent;
import kr.or.fineapple.domain.WeeklyPaper;
import kr.or.fineapple.domain.common.ViewDuration;
import kr.or.fineapple.service.diary.DiaryService;

@RestController
@RequestMapping("/diary/*")
public class DiaryRestController {
	
	@Autowired
	@Qualifier("diaryServiceImpl")
	private DiaryService diaryService;
	
	public DiaryRestController() {
		System.out.println(this.getClass());
	}

	@RequestMapping(value="json/getDiary", method=RequestMethod.POST)
	public Diary getDiary(int year, int month) {
		
		LocalDate startDate = LocalDate.of(year, month, 1);
		LocalDate endDate = startDate.plusDays(startDate.lengthOfMonth()-1);
		
		return null;
	}
	
	@RequestMapping(value="json/getUserDailyLog", method=RequestMethod.POST)
	public UserDailyLog getUserDailyLog(@RequestBody ViewDuration viewDuration){
		String userId = viewDuration.getUserId();
		LocalDate viewDate = viewDuration.getDate();
		return null;
	}
	
	@RequestMapping(value="json/addUserBodyInfo", method=RequestMethod.POST)
	public void addUserBodyInfo(@RequestBody UserEvent userEvent) {
		diaryService.addUserEvent(userEvent);
	}
	
	@RequestMapping(value="json/getUserEvent/{userEventNo}", method=RequestMethod.GET)
	public UserEvent getUserEvent(@PathVariable int userEventNo) {
		return diaryService.getUserEvent(userEventNo);
	}
	
	@RequestMapping(value="json/getUserEventList", method=RequestMethod.POST)
	public Map<String, Object> getUserEventList(@RequestBody ViewDuration viewDuration){
		String userId = viewDuration.getUserId();
		LocalDate eventDate = viewDuration.getDate();
		return diaryService.getUserEventList(userId, eventDate);
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
	public Map<String, Object> getUserBodyInfoList(@RequestBody ViewDuration viewDuration) {
		String userId = viewDuration.getUserId();
		LocalDate startDate = viewDuration.getStartDate();
		LocalDate endDate = viewDuration.getEndDate();
		return diaryService.getUserBodyInfoList(userId, startDate, endDate);
	}
	
	@RequestMapping(value="json/updateUserBodyInfo", method=RequestMethod.POST)
	public void updateUserBodyInfo(UserBodyInfo userBodyInfo) {
		diaryService.updateUserBodyInfo(userBodyInfo);
	}
	
	@RequestMapping(value="json/getWeeklyPaper", method=RequestMethod.POST)
	public WeeklyPaper getWeeklyPaper(@RequestBody ViewDuration viewDuration) {
		LocalDate startDate = viewDuration.getStartDate();
		LocalDate endDate = viewDuration.getEndDate();
		return null;
	}
	
	@RequestMapping(value="json/getMonthlyPaper", method=RequestMethod.POST)
	public MonthlyPaper getMonthlyPaper(@RequestBody ViewDuration viewDuration) {
		LocalDate startDate = viewDuration.getStartDate();
		LocalDate endDate = viewDuration.getEndDate();
		return null;
	}
	
	//update식단기록
	//update운동기록

}
