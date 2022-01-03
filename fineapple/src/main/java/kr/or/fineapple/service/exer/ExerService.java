package kr.or.fineapple.service.exer;

import java.util.List;
import java.util.Map;

import kr.or.fineapple.domain.BurnningRecord;
import kr.or.fineapple.domain.Exer;
import kr.or.fineapple.domain.ExerServ;
import kr.or.fineapple.domain.Routine;
import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.common.Search;

public interface ExerService {
	
	public int addUserService(ExerServ service)throws Exception;
	
	public ExerServ getUserService(String userId)throws Exception;
	
	public int updateUserService(ExerServ service)throws Exception;
	
	
	public Map<String ,Object> getExerList(Search search)throws Exception;
		
	public void addExer(Exer exer)throws Exception;
	
	public Exer getExer(int exerNo)throws Exception;
	
	public int postUpdateExer(Exer exer)throws Exception;
	
	public int deleteExer(int exerNo)throws Exception;
	
	
	
	
	public int addDailyBurnning(BurnningRecord record)throws Exception;
	
	public BurnningRecord getBurnningRecordList(int userServiceNo)throws Exception;
	
	public int updateBurnningRecord(BurnningRecord record)throws Exception;
	
	
	
	
	
	public int addRoutine(Routine routine)throws Exception;
	
	public Routine getRoutine(int routineNo)throws Exception;
	
	public int updateRoutine(Routine routine)throws Exception;
	
	public int deleteRoutine(int routineNo)throws Exception;
	
	public Map<String ,Object> getRoutineList(int exerServiceNo)throws Exception;
	
	
	
	
	public int addRoutineInfo(Routine routine)throws Exception;
	
	public Map<String ,Object> getRoutineInfoList(int routineNo)throws Exception;
	
	public int deleteRoutineInfo(int routineInfoNo)throws Exception;
	
	public int updateRoutineInfo(Routine routine)throws Exception;
	
	
	
	//추천운동리스트
	public List<Exer> recommandExerList(Double overKcal)throws Exception;
	
	public Double sumIntakeKcal(String userId)throws Exception;
	
	public User needDaliyIntakeKcal(String userId)throws Exception;
	
	
	
	//운동장소 찾기
	
	public int searchExerPlace()throws Exception;
	
	
	
	
	
}
