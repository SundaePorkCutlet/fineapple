package kr.or.fineapple.persistence;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.fineapple.domain.BurnningRecord;
import kr.or.fineapple.domain.Exer;
import kr.or.fineapple.domain.ExerService;
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
		exer.setExerKcal(130);
		exer.setExerLv(1);
		exer.setExerName("턱걸이");	
		exer.setExerNo(1);
		exer.setExerVideoName("턱걸이 영상");
		exer.setExerVideoTime(00,10,00);
		
		
		  BurnningRecord record = new BurnningRecord();
		 
		  //addDailyBurnning
		  record.setExerLv(3); 
		  record.setUserExerBurnning(170.7);	  
		  record.setAnExerTime(LocalTime.of(00,55,00)); 
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
		  record.setDailyExerTime(00,30,00);
		  record.setRecommendExerKcal(200.0);
		  
		  
		
		  //service.setExerServiceTrgt("체중증량");
		
		  ExerService service = new ExerService();
		
		  service.setDailyTrgtBurnningKcal(100.2);
		  service.setTrgtBodyMuscle(30.2);
		  service.setUserId("hc@gmail.com");
		
		  
		 // log.info("exerMapper 확인 addUserService " + exerMapper.addUserService(service));
		  
		  
		  
		  service.setExerServiceTrgt("체중유지");
		  service.setUserId("hc@gmail.com");
		  //log.info("exerMapper 확인 updateServiceTrgt " + exerMapper.updateServiceTrgt(service));

		  
		  //userBodyInfo
		  service.setBodyMuscle(10.5);
		  service.setUserId("hc@gmail.com");
		  
		  log.info("exerMapper 확인 updateBodyInfo " + exerMapper.updateBodyInfo(service));
		  
}	
	
	
}
