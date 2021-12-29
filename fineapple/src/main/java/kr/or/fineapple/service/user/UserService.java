package kr.or.fineapple.service.user;

import kr.or.fineapple.domain.User;

public interface UserService {
	public void addUser(User user)throws Exception;
	
	public User getUser(String userId) throws Exception;
	
	public void updateUser(User user) throws Exception;
	
	public void updateUserLeave(User user) throws Exception;
	
	/*
	 * public boolean checkDuplication (String userId) throws Exception;
	 */	
  
}
