package kr.or.fineapple.persistence;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.fineapple.domain.BurnningRecord;
import kr.or.fineapple.domain.Exer;
import kr.or.fineapple.domain.ExerServ;
import kr.or.fineapple.domain.Routine;
import kr.or.fineapple.mapper.ExerMapper;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest
class exerMapperTest {

	@Autowired
	private ExerMapper exerMapper;
	
	
	@Test
	public void test() {
		
		Exer exer = new Exer();
				
		exer.setExerCate("유산소");
		exer.setExerKcal(130.5);
		exer.setExerName("턱걸이");	
		exer.setExerNo(1);
		exer.setExerVideoName("턱걸이 영상");
		//exer.setExerVideoTime("1분");
		
		
		  BurnningRecord record = new BurnningRecord();
		 
		  //addDailyBurnning
		  record.setUserExerBurnning(170.7);	  
		  record.setAnExerTime("2:30:00"); 
		  record.setUserId("hc@gmail.com");
		  record.setExer(exer);
		  
		 // log.info("exerMapper 확인 addDailyBurnning " + exerMapper.addDailyBurnning(record));
  
		  
			/*
			 * //updateUserService record.setDailyTrgtBurnningKcal(200.5);
			 * record.setTrgtBodyMuscle(50.0);
			 * 
			 * //updateUsers record.setExerServiceTrgt("체중유지");
			 */
		  
		  //로직에서 필요함
		  record.setDailyExerKcal(150.5); 
		  record.setDailyExerTime("1:00:00");
		  record.setRecommendExerKcal(200.0);
		  
		  
		  
		  //service.setExerServiceTrgt("체중증량");
		
		  ExerServ service = new ExerServ();
		
			/*
			 * service.setDailyTrgtBurnningKcal(100.2); service.setTrgtBodyMuscle(30.2);
			 * service.setUserId("hc@gmail.com");
			 * 
			 * // log.info("exerMapper 확인 addUserService " +
			 * exerMapper.addUserService(service));
			 * 
			 * 
			 * service.setExerServiceTrgt("체중유지"); service.setUserId("hc@gmail.com");
			 * //log.info("exerMapper 확인 updateServiceTrgt " +
			 * exerMapper.updateServiceTrgt(service));
			 * 
			 * 
			 * //userBodyInfo service.setBodyMuscle(10.4);
			 * service.setUserId("hc@gmail.com");
			 */
		  
		  //log.info("exerMapper 확인 updateBodyInfo " + exerMapper.updateBodyInfo(service));
		  
		
		 // SeaWrch search = new Search();
		  
		  Routine routine = new Routine();
		  
		  routine.setRoutineNo(9);
		  routine.setExer(exer);
		  routine.setExerNo(1);
		  routine.setRoutineTime("2시간 30분");
		  
		  
		  
		  log.info("exWerMapper 확인 updateRoutine " + exerMapper.addRoutineInfo(routine));

		  
		  
}	
}
