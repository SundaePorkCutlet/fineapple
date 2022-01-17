package kr.or.fineapple.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.or.fineapple.domain.BurnningRecord;
import kr.or.fineapple.domain.Exer;
import kr.or.fineapple.domain.ExerServ;
import kr.or.fineapple.domain.Routine;
import kr.or.fineapple.domain.TotalRecord;
import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.common.Search;


@Mapper
@Repository
public interface ExerMapper {

	   //운동서비스 활성화 CRUD
	   int addUserService(ExerServ service);
	   
	   ExerServ getUserService(String userId);
	   
	   
	   int updateUserService(ExerServ service);
	   
       
       int updateServiceTrgt(ExerServ service);
       
       
       int updateBodyInfo(ExerServ service);

       
       int updateExerServiceNo(ExerServ service);
       
       
       //일일 운동량 CRUD
       int addDailyBurnning(BurnningRecord record);
       
       List<BurnningRecord> getBurnningRecordList(int userServiceNo);
       
       int updateBurnningRecord(BurnningRecord record);
       
       Double sumBurnningKcal(int userServiceNo);
       
       void deleteBurnningRecord(int burnningRecordNo);
       
       
       
       //운동CRUD
       int addExer(Exer exer);
       
       Exer getExer(int exerNo);
       
       List<Search> getExerList(Search search);
       
       List<Exer> getExerListJSON();
       
       int updateExer(Exer exer);
       
       int deleteExer(int exerNo);
       
       int getTotalCount(Search search) throws Exception ;
       
       
       //루틴CRUD
       int addRoutine(Routine routine);
      
       Routine getRoutine(int routineNo);
      
       int updateRoutine(Routine routine);
       
       int updateRoutineName(Routine routine);
       
       int deleteRoutine(int routineNo);
       
       List<Routine> getRoutineList(int exerServiceNo);
       
       
       
       //루틴에 등록할 운동 
       int addRoutineInfo(Routine routine);
       
       Routine getRoutineInfo(int routineInfoNo);
       
       int deleteRoutineInfo(int routineInfoNo);
       
       int updateRoutineInfo(Routine routine);
       
       List<Routine> getRoutineInfoList(int routineNo);
       
       
       
       //추천운동리스트
       List<Exer>recommandExerList(Double overKcal);
       
       Double sumIntakeKcal(String userId);
   
       User needDaliyIntakeKcal(String userId);
       
       ////다이어리 진행자 하리니가 작성: 특정 일자의 일일 운동량 정보 조회
       List<Object> getBurnningRecordListForDiary(Map map);
       
       ////다이어리 진행자 하리니가 작성: 기간 내 총 운동 칼로리, 총 운동 시간
       TotalRecord getTotalExerRecord(Map map);

   }
