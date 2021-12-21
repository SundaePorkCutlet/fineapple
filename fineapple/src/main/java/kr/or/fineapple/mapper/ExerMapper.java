package kr.or.fineapple.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.or.fineapple.domain.BurnningRecord;
import kr.or.fineapple.domain.ExerService;
import kr.or.fineapple.domain.User;

@Mapper
@Repository
public interface ExerMapper {

	   
	   int addUserService(ExerService service);
	   
       int addDailyBurnning(BurnningRecord record);
       
       int updateUser(User user);
       
       int updateServiceTrgt(ExerService service);
       
       int updateBodyInfo(ExerService service);
       
       
          
       int updateExerService(BurnningRecord record);
       
       int getExerService(BurnningRecord record);
   }
