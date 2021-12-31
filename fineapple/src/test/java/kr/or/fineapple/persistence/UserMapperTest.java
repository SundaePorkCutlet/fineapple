package kr.or.fineapple.persistence;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.fineapple.domain.User;
import kr.or.fineapple.mapper.UserMapper;
import kr.or.fineapple.service.user.UserService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest
public class UserMapperTest{

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	//@Test
	public void checkDuplication() throws Exception{
		User user = new User();
		String userId = "user123@naver.com";
		
		user.setUserId(userId);
		String result = userService.checkDuplication(user);
		
		log.info("result : " + result);
	}
	
	@Test
	public void getUserList() throws Exception{
		User user = new User();
		
		String userName="ÇöÁ¤";
		String cellphone = "01098958180";

		user.setUserName(userName);
		user.setCellphone(cellphone);
		
		List<Object> list = userService.getUserList(user);
		
		log.info("list : " + list);
	}
	
}

/*
 * @Autowired private UserMapper
 * 
 * @Autowired private UserService userService;
 * 
 * 
 */