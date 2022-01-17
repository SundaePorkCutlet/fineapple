package kr.or.fineapple.service.user.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.fineapple.domain.Badge;
import kr.or.fineapple.domain.User;
import kr.or.fineapple.mapper.DiaryMapper;
import kr.or.fineapple.mapper.UserMapper;
import kr.or.fineapple.service.user.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private DiaryMapper diaryMapper;
	
	@Override
	public void addUser(User user) throws Exception {
		userMapper.addUser(user);
	}
	
	public void afterAddOrRestoreUser(User user) throws Exception {
		userMapper.addUserBodyInfo(user);
		Badge defaultBadge = new Badge();
		if(user.getDietServiceNo() != 0 && user.getExerServiceNo() != 0) {
			//식단, 운동 서비스 모두 이용
			defaultBadge.setUserId(user.getUserId());
			defaultBadge.setBadgeDate(LocalDate.now());
			defaultBadge.setDailyBurnningKcal(0.0);
 			defaultBadge.setDailyIntakeKcal(0.0);
			diaryMapper.addBadge(defaultBadge);
		} else if(user.getDietServiceNo() == 0 && user.getExerServiceNo() != 0) {
			//식단 서비스 이용
			defaultBadge.setUserId(user.getUserId());
			defaultBadge.setBadgeDate(LocalDate.now());
			defaultBadge.setDailyIntakeKcal(0.0);
			diaryMapper.addBadge(defaultBadge);
		} else if(user.getDietServiceNo() != 0 && user.getExerServiceNo() == 0) {
			//운동 서비스만 이용
			defaultBadge.setUserId(user.getUserId());
			defaultBadge.setBadgeDate(LocalDate.now());
			defaultBadge.setDailyBurnningKcal(0.0);
			diaryMapper.addBadge(defaultBadge);
		} else {
			//식단, 운동 모두 이용하지 않음 or 최초 회원가입 회원
			defaultBadge.setUserId(user.getUserId());
			defaultBadge.setBadgeDate(LocalDate.now());
			diaryMapper.addBadge(defaultBadge);			
		}
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
