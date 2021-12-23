package kr.or.fineapple.persistence;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.fineapple.domain.TrgtHabit;
import kr.or.fineapple.mapper.TrgtHabitMapper;
import kr.or.fineapple.service.trgtHabit.TrgtHabitService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class TrgtHabitMapperTest {
	
	//@Autowired
	private TrgtHabitMapper trgtHabitMapper;
	
	//@Autowired
	//@Qualifier("trgtHabitServiceImpl")
	private TrgtHabitService trgtHabitService;
	
	//@Test
	public void contextLoads() {
		
		//addTrgtHabit Test
		/*
		 * String userId = "mark@gmail.com"; TrgtHabit trgtHabit = new TrgtHabit();
		 * trgtHabit.setUserId(userId); trgtHabit.setTrgtHabitCateNo(3);
		 * trgtHabit.setTrgtHabitCateName("±Ý¿¬");
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
		 
	}
}
