package kr.or.fineapple.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.or.fineapple.domain.BurnningRecord;
import kr.or.fineapple.domain.Exer;
import kr.or.fineapple.domain.ExerService;
import kr.or.fineapple.domain.Routine;
import kr.or.fineapple.domain.common.Search;


@Mapper
@Repository
public interface ExerMapper {

		   
	   int addUserService(ExerService service);
	   
	   int getUserService(ExerService service);
	   
       int addDailyBurnning(BurnningRecord record);
       
       int updateServiceTrgt(ExerService service);
       
       int updateBodyInfo(ExerService service);
       
       
       //운동CRUD
       int addExer(Exer exer);
       
       Exer getExer(int exerNo);
       
       List<Search> getExerList(Search search);
       
       int updateExer(Exer exer);
       
       int deleteExer(int exerNo);
       
       
       
       //루틴CRUD
       int addRoutine(Routine routine);
      
       Routine getRoutine(int routineNo);
      
       int updateRoutine(Routine routine);
       
       int deleteRoutine(int routineNo);
       
       //List<> getRoutineList();
       
       
       //루틴에 등록할 운동 
       int addRoutineInfo(Routine routine);
       
       Routine getRoutineInfo(int routineInfoNo);
       
       int deleteRoutineInfo(int routineInfoNo);
       
       //추천운동리스트
       List<Search>recommandExerList(Search search);
       
       
       int updateExerService(BurnningRecord record);
       

   }
