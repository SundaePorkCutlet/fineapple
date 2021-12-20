package kr.or.fineapple.service.exer;

import java.util.List;

import kr.or.fineapple.domain.BurnningRecord;
import kr.or.fineapple.domain.Exer;
import kr.or.fineapple.domain.User;

public interface ExerService {
	
	public void addUserService(ExerService service)throws Exception;
	
	public void addDailyBurnning(BurnningRecord record);
	
	public void updateUser(User user);
	
	public void updateServiceTrgt(ExerService service);
    
	public void updateBodyInfo(ExerService serivce);
	
	
	
	public void updateExerService(BurnningRecord record)throws Exception;
	
	public void getExerService(BurnningRecord record)throws Exception;
	
	public List<Exer> ExerList(Exer exer);
	
}
