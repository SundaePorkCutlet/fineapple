package kr.or.fineapple.service.user;

import java.util.List;

import kr.or.fineapple.domain.User;

public interface UserService {
	public void addUser(User user)throws Exception;
	
	public User getUser(String userId) throws Exception;
	
	public void updateUser(User user) throws Exception;
	
	public void updateUserLeave(User user) throws Exception;
	
	public void restoreUser(User user) throws Exception;
	
	public  String checkDuplication(User user) throws Exception;
	
	public 	List<Object> getUserList(User user) throws Exception;
	
	public String kakaoLogin(User user) throws Exception;
	
	public String checkPassword(User user) throws Exception;
	
	public void changePassword(User user) throws Exception;

}
