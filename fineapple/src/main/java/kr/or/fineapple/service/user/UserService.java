package kr.or.fineapple.service.user;

import kr.or.fineapple.domain.User;

public interface UserService {
	public void addUser(User user)throws Exception;
	
	public User getUser(String userId) throws Exception;

}
