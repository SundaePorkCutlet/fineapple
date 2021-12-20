package kr.or.fineapple.persistence;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.fineapple.mapper.TrgtHabitMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class TrgtHabitMapperTest {
	
	@Autowired
	private TrgtHabitMapper trgtHabitMapper;
	
	@Test
	public void contextLoads() {
		
		//addTrgtHabit Test
		/*
		 * String userId = "markgmail.com"; TrgtHabit trgtHabit = new TrgtHabit();
		 * trgtHabit.setUserId(userId); trgtHabit.setTrgtHabitCateNo(1);
		 * trgtHabit.setTrgtHabitCateName("Ä¿ÇÇ");
		 * trgtHabit.setTrgtHabitStartDate(LocalDate.of(2021, 11, 3));
		 * 
		 * trgtHabitMapper.addTrgtHabit(trgtHabit);
		 */
		
		
		//getTrgtHabit Test
		/*
		 * String userId = "hc@gmail.com"; LocalDate viewDate = LocalDate.now(); int
		 * trgtHabitCateNo = 1;
		 * 
		 * Map<String, Object> map = new HashMap<>(); map.put("userId", userId);
		 * map.put("viewDate", viewDate); map.put("trgtHabitCateNo", trgtHabitCateNo);
		 * 
		 * log.info(trgtHabitMapper.getTrgtHabit(map).toString());
		 */
		
		//endTrgtHabit Test
		/*
		 * int trgtHabitServiceNo = 2; trgtHabitMapper.endTrgtHabit(trgtHabitServiceNo);
		 */
		
		//addWtrIntake Test
		/*
		 * String userId = "hc@gmail.com"; trgtHabitMapper.addWtrIntake(userId);
		 */
		
		//getWtrIntake Test
		/*
		 * String userId = "hc@gmail.com"; //LocalDate date = LocalDate.of(2021,12,20);
		 * // LocalDate date = LocalDate.now(); Map<String, Object> map = new
		 * HashMap<>(); map.put("userId", userId); map.put("date", date);
		 * 
		 * log.info(trgtHabitMapper.getWtrIntake(map).toString());
		 */
		
		//updateWtrIntake Test
		String userId = "hc@gmail.com";
		LocalDate date = LocalDate.of(2021,12,20);
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("date", date);
		double userWtrIntake = trgtHabitMapper.getWtrIntake(map);
		System.out.println(userWtrIntake);
		userWtrIntake = userWtrIntake + 0.25;
		System.out.println(userWtrIntake);
		trgtHabitMapper.updateWtrIntake(userWtrIntake);	
	}
}
