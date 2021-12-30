package kr.or.fineapple.persistence;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.fineapple.domain.common.ViewDuration;
import kr.or.fineapple.service.diary.DiaryService;

@SpringBootTest
public class DiaryMapperTest {
	
	@Autowired
	@Qualifier("diaryServiceImpl")
	private DiaryService diaryService;
	
	@Test
	public void contextLoads() {

//		String userId = "hc@gmail.com";
//		LocalDate eventDate = LocalDate.now();
//		String eventTitle = "로이산책";
//		String eventCntnt = "세상의 모든냄새를 다맡고올거야!!!!";
		
		
		//addUserEvent Test
		
		/*
		 * UserEvent userEvent = new UserEvent(); userEvent.setUserId(userId);
		 * userEvent.setEventDate(eventDate); userEvent.setKeyEventStt(0);
		 * userEvent.setEventTitle(eventTitle); userEvent.setEventCntnt(eventCntnt);
		 * 
		 * diaryService.addUserEvent(userEvent);
		 */
		
		//getUserEvent Test
		
//		  UserEvent returnUserEvent = diaryService.getUserEvent(5);
//		  System.out.println(returnUserEvent);
		 
		
		//getUserEventList Test
		/*
		 * Map map = diaryService.getUserEventList(userId, eventDate);
		 * System.out.println(map);
		 */
		
		//updateUserEvent Test
//		UserEvent userEvent = diaryService.getUserEvent(6);
//		System.out.println(userEvent);
//		
//		userEvent.setEventTitle("로이산책");
//		userEvent.setEventCntnt("세상의 모든냄새를 다맡고올거야!!!!");
//		diaryService.updateUserEvent(userEvent);
		
		//updateKeyUSerEventStt
		
//		diaryService.updateKeyUserEventStt(2);
		
		//deleteUserEvent
//		diaryService.deleteUserEvent(5);
		
		//getUserBodyInfoList Test
//		LocalDate startDate = LocalDate.of(2021, 12, 21);
//		LocalDate endDate = LocalDate.of(2021, 12, 27);
//		Map map = diaryService.getUserBodyInfoList(userId, startDate, endDate);
//		System.out.println(map);
		
		//updateUserBodyInfo Test
//		UserBodyInfo userBodyInfo = new UserBodyInfo();
//		userBodyInfo.setUserId(userId);
//		userBodyInfo.setDate(LocalDate.of(2021, 12, 26));
//		userBodyInfo.setWeight(65);
//		userBodyInfo.setHeight(174);
//		diaryService.updateUserBodyInfo(userBodyInfo);
		
		//getBadgeList Test
//		ViewDuration viewDuration = new ViewDuration();
//		viewDuration.setUserId("hc@gmail.com");
//		viewDuration.setStartDate(LocalDate.of(2021, 11, 1));
//		viewDuration.setEndDate(LocalDate.of(2021, 12, 31));
//		Map map = diaryService.getBadgeList(viewDuration);
//		System.out.println(map);
		
//		ViewDuration viewDuration = new ViewDuration();
//		viewDuration.setDate(LocalDate.now());
//		LocalDate startDate = viewDuration.getDate().with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
//		LocalDate endDate = viewDuration.getDate().with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
//		System.out.println(startDate.toString());
//		System.out.println(endDate.toString());
	}

}
