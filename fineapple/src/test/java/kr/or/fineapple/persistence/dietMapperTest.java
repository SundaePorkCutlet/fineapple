package kr.or.fineapple.persistence;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.fineapple.domain.DietServ;
import kr.or.fineapple.domain.Food;
import kr.or.fineapple.domain.IntakeRecord;
import kr.or.fineapple.mapper.DietMapper;
import kr.or.fineapple.service.diet.DietService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest
class dietMapperTest {

	 @Autowired
	 private DietMapper dietMapper;

	 
		@Autowired

		@Qualifier("dietServiceImpl")
		private DietService dietService;


	
	@Test
	public void contextLoads() throws Exception {
		IntakeRecord record = new IntakeRecord();
		Food food = new Food() ;
		DietServ dietServ = new DietServ();
		
		String userId = "zzz@gmail.com";
		
		food.setFoodCarb(1.1);
		food.setFoodImg(null);
		food.setFoodIntoStt(1);
		food.setFoodKcal(313.2);
		food.setFoodName("홍진호");
		food.setFoodNo(1);
		food.setFoodProtein(23.1);
		food.setFoodSodium(1.1);
		food.setFoodSugar(33.1);
		food.setIsAPI(0);
		food.setPrice(1002320);
		food.setPurchaseConnLink(null);
		food.setServingSize(100);
		food.setStoreName(null);
		
		
		dietServ.setBodyFat(40.1);
		dietServ.setDailyTrgtIntakeCarb(35.1);
		dietServ.setDailyTrgtIntakeFat(36.1);
		dietServ.setDailyTrgtIntakeKcal(213.2);
		dietServ.setDailyTrgtIntakeProtein(23.1);
		dietServ.setDietServiceTrgt("체중증량");
		dietServ.setUserId("aaa123@naver.com");
		
		
		record.setFood(food);
		record.setUserFoodIntake(313.7);
		record.setMeal("아");
		record.setUserId("aaa123@naver.com");
	




//        log.info("addDietService : " + dietService.addDietService(dietServ));
//        log.info("addIntakeRecord : " + dietService.addIntakeRecord(record));
//        log.info("getDietService : " + dietService.getDietService(userId));
		  log.info("updatetrgt : " + dietMapper.updateBodyInfo(dietServ));

	}
}