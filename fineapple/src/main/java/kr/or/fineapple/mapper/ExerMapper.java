package kr.or.fineapple.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.or.fineapple.domain.BurnningRecord;
import kr.or.fineapple.domain.ExerService;
import kr.or.fineapple.domain.common.Search;


@Mapper
@Repository
public interface ExerMapper {

		   
	   int addUserService(ExerService service);
	   
	   int getUserService(ExerService service);
	   
       int addDailyBurnning(BurnningRecord record);
       
       int updateServiceTrgt(ExerService service);
       
       int updateBodyInfo(ExerService service);
       
       List<Search> getExerList(Search search);
       
       int updateExerService(BurnningRecord record);
       
    

   }
