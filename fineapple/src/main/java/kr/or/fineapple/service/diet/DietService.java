package kr.or.fineapple.service.diet;

import kr.or.fineapple.domain.IntakeRecord;
import kr.or.fineapple.domain.DietServ;


public interface DietService {

	public int addIntakeRecord(IntakeRecord record)throws Exception;
	
	public int addDietService(DietServ diet)throws Exception;
	
	public DietServ getDietService(String userId)throws Exception;
}
