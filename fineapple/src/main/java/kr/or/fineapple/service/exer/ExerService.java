package kr.or.fineapple.service.exer;

import java.util.Map;

import kr.or.fineapple.domain.BurnningRecord;
import kr.or.fineapple.domain.Exer;
import kr.or.fineapple.domain.ExerServ;
import kr.or.fineapple.domain.Routine;
import kr.or.fineapple.domain.common.Search;

public interface ExerService {
	
	public int addUserService(ExerServ service)throws Exception;
	
	public ExerServ getUserService(String userId)throws Exception;
	
	public int updateUserService(ExerServ serivce)throws Exception;
	
	
	public Map<String ,Object> getExerList(Search search)throws Exception;
		
	public void addExer(Exer exer)throws Exception;
	
	public Exer getExer(int exerNo)throws Exception;
	
	public int postUpdateExer(Exer exer)throws Exception;
	
	public int deleteExer(int exerNo)throws Exception;
	
	public int addDailyBurnning(BurnningRecord record)throws Exception;
	
	
	
	public int addRoutine(Routine routine);
	
	public Routine getRoutine(int routineNo);
	
	public int updateRoutine(Routine routine);
	
	public int deleteRoutine(int routineNo);
	
	//public Map<String ,Object> getRoutineList(Search search)throws Exception;
	
	
	public int addRoutineInfo(Routine routine);
	
	public Routine getRoutineInfo(int routineInfoNo);
	
	public int deleteRoutineInfo(int routineInfoNo);
	
	//추천운동리스트
	public Map<String,Object> recommandExerList(Search search);
	
	
	//운동장소 찾기
	
	public int searchExerPlace();
	
	
	
	
	
}
