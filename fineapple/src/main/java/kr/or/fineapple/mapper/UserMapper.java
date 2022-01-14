package kr.or.fineapple.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.or.fineapple.domain.User;

@Mapper
@Repository
public interface UserMapper{
		public void addUser(User user) throws Exception;
		
		public void addUserBodyInfo(User user) throws Exception;
		
		public User getUser(String userId) throws Exception;
		
		public void updateUser(User user) throws Exception;
		
		public void updateUserLeave(String userId) throws Exception;
		
		public void restoreUser(User user) throws Exception;
		
		public String checkDuplication(User user) throws Exception;
		
		public List<Object> getUserList(User user) throws Exception;

		public String checkDuplication(String userId);
		
		public String checkPassword(User user) throws Exception;
		
		public void changePassword(User user) throws Exception;
		
		public String kakaoStt(User user) throws Exception;
		
	}

