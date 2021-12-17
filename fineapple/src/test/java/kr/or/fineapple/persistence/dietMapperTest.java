package kr.or.fineapple.persistence;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import kr.or.fineapple.FineappleApplication;
import kr.or.fineapple.mapper.dietMapper;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest
class dietMapperTest {

	 @Autowired
	 private dietMapper dietMapper;
	
	@Test
	public void contextLoads() {
        log.info("timeMapper time: " + dietMapper.getTime());
	}
}