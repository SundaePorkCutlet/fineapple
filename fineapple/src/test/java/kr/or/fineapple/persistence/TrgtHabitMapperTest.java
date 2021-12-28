package kr.or.fineapple.persistence;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.fineapple.domain.common.ViewDuration;
import kr.or.fineapple.service.trgtHabit.TrgtHabitService;

//@Slf4j
@SpringBootTest
public class TrgtHabitMapperTest {
	
//	@Autowired
//	private TrgtHabitMapper trgtHabitMapper;
	
	@Autowired
	@Qualifier("trgtHabitServiceImpl")
	private TrgtHabitService trgtHabitService;
	
	@Test
	public void contextLoads() {
		
		//addTrgtHabit Test
		/*
		 * String userId = "mark@gmail.com"; TrgtHabit trgtHabit = new TrgtHabit();
		 * trgtHabit.setUserId(userId); trgtHabit.setTrgtHabitCateNo(3);
		 * trgtHabit.setTrgtHabitCateName("금연");
		 * trgtHabit.setTrgtHabitStartDate(LocalDate.of(2021, 11, 8));
		 * 
		 * trgtHabitService.addTrgtHabit(userId, trgtHabit);
		 * 
		 */
		
		//getTrgtHabit Test
		/*
		 * String userId = "hc@gmail.com"; LocalDate viewDate = LocalDate.now(); int
		 * trgtHabitCateNo = 1;
		 * 
		 * // Map<String, Object> map = new HashMap<>(); map.put("userId", userId);
		 * //map.put("viewDate", viewDate); map.put("trgtHabitCateNo", trgtHabitCateNo);
		 * 
		 * log.info(trgtHabitService.getTrgtHabit(userId, viewDate,
		 * trgtHabitCateNo).toString());
		 */
		 
		//endTrgtHabit Test
		
		/*
		 * int trgtHabitServiceNo = 3;
		 * trgtHabitService.endTrgtHabit(trgtHabitServiceNo);
		 */
		 
		
		//addWtrIntake Test
		/*
		 * String userId = "hc@gmail.com"; trgtHabitMapper.addWtrIntake(userId);
		 */
		
		//getWtrIntake Test
		/*
		 * String userId = "hc@gmail.com"; //LocalDate date = LocalDate.of(2021,12,20);
		 * LocalDate date = LocalDate.now(); // Map<String, Object> map = new
		 * HashMap<>(); // map.put("userId", userId); // map.put("date", date);
		 * 
		 * log.info(((Double)trgtHabitService.getWtrIntake(userId, date)).toString());
		 */
		 
		
		//updateWtrIntake Test
		
		/*
		 * String userId = "hc@gmail.com"; // LocalDate date = LocalDate.of(2021,12,20);
		 * // Map<String, Object> map = new HashMap<>(); map.put("userId", userId); //
		 * map.put("date", date); // double userWtrIntake =
		 * trgtHabitMapper.getWtrIntake(map); // System.out.println(userWtrIntake);
		 * double userWtrIntake = 0.0; trgtHabitService.updateWtrIntake(userId,
		 * userWtrIntake);
		 */
		
		//getTrgtHabitList Test (startDate != null and endDate != null 경우)
//		ViewDuration viewDuration = new ViewDuration();
//		viewDuration.setUserId("hc@gmail.com");
//		viewDuration.setStartDate(LocalDate.of(2021, 11, 1));
//		viewDuration.setEndDate(LocalDate.of(2021, 12, 31));
//		List list = trgtHabitService.getTrgtHabitList(viewDuration);
//		System.out.println(list);
		//getTrgtHabitList Test (date != null 경우)
//		ViewDuration viewDuration = new ViewDuration();
//		viewDuration.setUserId("hc@gmail.com");
//		viewDuration.setDate(LocalDate.now());
//		List list = trgtHabitService.getTrgtHabitList(viewDuration);
//		System.out.println(list);
		
	}
}
