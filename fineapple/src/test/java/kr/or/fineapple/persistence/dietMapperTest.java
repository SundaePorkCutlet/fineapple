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
        log.info("timeMapper time: ");
	}
}