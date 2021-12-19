package kr.or.fineapple.persistence;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.fineapple.domain.Food;
import kr.or.fineapple.domain.IntakeRecord;
import kr.or.fineapple.mapper.DietMapper;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest
class dietMapperTest {

	 @Autowired
	 private DietMapper dietMapper;
	
	@Test
	public void contextLoads() {
		IntakeRecord record = null;
		Food food = null;
		food.setFoodCarb(1.1);
		food.setFoodCholesterol(2.1);
		food.setFoodImg(null);
		food.setFoodIntoStt(1);
		food.setFoodKcal(313.2);
		food.setFoodName("홍진호");
		food.setFoodNo(22);
		food.setFoodProtein(23.1);
		food.setFoodSaturatedFattyAcid(2.1);
		food.setFoodSodium(1.1);
		food.setFoodSugar(33.1);
		food.setFoodTransFat(0.7);
		food.setFoodUnsaturatedFattyAcid(0.31);
		food.setIsAPI(0);
		food.setPrice(1002320);
		food.setPurchaseConnLink(null);
		food.setServingSize(100);
		food.setStoreName(null);
		
		
		record.setBodyFat(34.1);
		record.setDailyTrgtIntakeCarb(35.1);
		record.setDailyTrgtIntakeFat(36.1);
		record.setDailyTrgtIntakeKcal(213.2);
		record.setDailyTrgtIntakeProtein(23.1);
		record.setDietServiceTrgt("체중증량");
		record.setFood(food);
		record.setUserFoodIntake(313);
		record.setMeal("아침");
		
		
        log.info("timeMapper time: " + dietMapper.insertDietService(record));
	}
}