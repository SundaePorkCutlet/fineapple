package kr.or.fineapple.mapper;

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
		
		public void updateUserLeave(User user) throws Exception;
	}

