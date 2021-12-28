package kr.or.fineapple.service.user.impl;

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
		userMapper.addUserBodyInfo(user);
		
	}

	@Override
	public User getUser(String userId) throws Exception {
		
		return userMapper.getUser(userId);
	}

	@Override
	public void updateUser(User user) throws Exception {
	 userMapper.updateUser(user);
		
	}

	@Override
	public void updateUserLeave(User user) throws Exception {
		userMapper.updateUserLeave(user);
	}
	
	

	/*
	 * @Override public boolean checkDuplication(String userId) throws Exception {
	 * boolean result=true; User user = userMapper.getUser(userId); if(user != null)
	 * { return false; }
	 * 
	 * return result; }
	 */
	
	
	

}
