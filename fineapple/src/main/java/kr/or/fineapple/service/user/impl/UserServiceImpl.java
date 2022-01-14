package kr.or.fineapple.service.user.impl;

import java.util.List;

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
	public void updateUserLeave(String userId) throws Exception {
		userMapper.updateUserLeave(userId);
	}

	@Override
	public void restoreUser(User user) throws Exception {
		userMapper.restoreUser(user);
		
	}

	@Override
	public String checkDuplication(User user) throws Exception {
		
		return userMapper.checkDuplication(user);
	}
	
	

	@Override
	public String checkPassword(User user) throws Exception {
		return userMapper.checkPassword(user);
	}

	@Override
	public List<Object> getUserList(User user) throws Exception {
		List<Object> list = userMapper.getUserList(user);

		return list;
	}

	@Override
	public String kakaoLogin(User user) throws Exception {
		
		return userMapper.checkDuplication(user.getUserId());
	}

	@Override
	public void changePassword(User user) throws Exception {
		userMapper.changePassword(user);
	}


	@Override
	public String kakaoStt(User user) throws Exception {
		return userMapper.kakaoStt(user);
		
	}



	
	

	
	
	
	

}
