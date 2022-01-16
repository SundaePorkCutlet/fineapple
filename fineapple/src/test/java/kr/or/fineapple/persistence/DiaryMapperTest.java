package kr.or.fineapple.persistence;

import java.time.LocalDate;
import java.time.Period;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.fineapple.service.diary.DiaryService;

@SpringBootTest
public class DiaryMapperTest {
	
	  @Autowired
	  @Qualifier("diaryServiceImpl") private DiaryService diaryService;
	 
	
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
//		LocalDate startDate = YearMonth.now().minusMonths(1).atDay(1);
//		LocalDate endDate = YearMonth.now().minusMonths(1).atEndOfMonth();
		//int week = startDate.get((TemporalField) WeekFields.WEEK_BASED_YEARS);
//		System.out.println(startDate);
//		System.out.println(endDate);
//		
//		WeekFields weekFields = WeekFields.of(Locale.getDefault());
//		String week1 = String.format("%d-%02d", startDate.getYear(), startDate.get(weekFields.weekOfYear()));
//		String week2 = String.format("%d-%02d", endDate.getYear(), endDate.get(weekFields.weekOfYear()));
//		System.out.println(week1);
//		System.out.println(week2);
//		
//		LocalDate firstday = LocalDate.of(2022, 1, 1);
//		String first1 = String.format("%d-%02d", firstday.getYear(), firstday.get(weekFields.weekOfYear()));
//		String first2 = String.format("%d-%02d", firstday.getYear(), firstday.get(weekFields.weekOfYear()));
//		System.out.println(first1);
//		System.out.println(first2);
//		int i = firstday.get(weekFields.weekOfWeekBasedYear());
//		System.out.println(i);
		
//		LocalDate first = startDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
//		LocalDate last = endDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
		
//		System.out.println(first);
//		System.out.println(first.plusDays(6));
//		System.out.println(last);
//		
//		LocalDate second = first.plusWeeks(1);
//		LocalDate third = second.plusWeeks(1);
//		
//		System.out.println(second);
//		System.out.println(third);
//		
//		ViewDuration firstWeek = new ViewDuration();
//		ViewDuration secondWeek = new ViewDuration();
//		ViewDuration thirdWeek = new ViewDuration();
//		ViewDuration fourthWeek = new ViewDuration();
//		ViewDuration fifthWeek = new ViewDuration();
//		ViewDuration sixthWeek = new ViewDuration();
//		List<ViewDuration> weekDates = new ArrayList<ViewDuration>();
//		LocalDate weekStartDate = startDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
//		
//		
//		firstWeek.setStartDate(weekStartDate);
//		firstWeek.setEndDate(weekStartDate.plusDays(6));
//		weekDates.add(firstWeek);
//		
//		secondWeek.setStartDate(weekStartDate.plusDays(7));
//		secondWeek.setEndDate(weekStartDate.plusDays(13));
//		weekDates.add(secondWeek);
//		
//		thirdWeek.setStartDate(weekStartDate.plusDays(14));
//		thirdWeek.setEndDate(weekStartDate.plusDays(20));
//		weekDates.add(thirdWeek);
//		
//		fourthWeek.setStartDate(weekStartDate.plusDays(21));
//		fourthWeek.setEndDate(weekStartDate.plusDays(27));
//		weekDates.add(fourthWeek);
//		
//		if(weekStartDate.plusDays(28).isBefore(endDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY)))) {
//			fifthWeek.setStartDate(weekStartDate.plusDays(28));
//			fifthWeek.setEndDate(weekStartDate.plusDays(34));
//			weekDates.add(fifthWeek);
//			
//			if(weekStartDate.plusDays(35).isBefore(endDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY)))) {
//				sixthWeek.setStartDate(weekStartDate.plusDays(35));
//				sixthWeek.setEndDate(weekStartDate.plusDays(41));
//				weekDates.add(sixthWeek);
//			}
//		}
		
//		System.out.println(weekDates);
		
		LocalDate today = LocalDate.now();
		LocalDate theLatestDateUserBodyInfo = diaryService.getTheLatestDateUserBodyInfo("hc@gmail.com");
		System.out.println(today);
		System.out.println(theLatestDateUserBodyInfo);
		//가장 마지막 날짜와 오늘 일자의 차 계산
		int howManyDays = Period.between(theLatestDateUserBodyInfo, today).getDays();
		
		System.out.println(howManyDays);

	}
	
}
