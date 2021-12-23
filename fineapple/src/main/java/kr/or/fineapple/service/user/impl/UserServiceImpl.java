package kr.or.fineapple.service.user.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.fineapple.domain.User;
import kr.or.fineapple.mapper.UserMapper;
import kr.or.fineapple.service.user.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public void addUser(User user) throws Exception {
		userMapper.addUser(user);
		
	}

	@Override
	public User getUser(String userId) throws Exception {
		return userMapper.getUser(userId);
	}
	
	
	
	

}
