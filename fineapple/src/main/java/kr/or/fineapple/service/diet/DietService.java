package kr.or.fineapple.service.diet;

import java.util.Map;

import kr.or.fineapple.domain.DietServ;
import kr.or.fineapple.domain.IntakeRecord;
import kr.or.fineapple.domain.common.Search;


public interface DietService {

	public int addIntakeRecord(IntakeRecord record)throws Exception;
	
	public int addDietService(DietServ diet)throws Exception;
	
	public DietServ getDietService(String userId)throws Exception;
	
	public Map<String,Object> getFoodList(Search search)throws Exception;
	
}
