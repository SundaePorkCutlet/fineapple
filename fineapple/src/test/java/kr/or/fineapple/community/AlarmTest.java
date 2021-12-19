package kr.or.fineapple.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.fineapple.mapper.CommunityMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class AlarmTest {
	
	@Autowired
	private CommunityMapper c;

}
