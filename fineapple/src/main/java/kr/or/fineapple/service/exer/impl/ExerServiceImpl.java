package kr.or.fineapple.service.exer.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.fineapple.domain.BurnningRecord;
import kr.or.fineapple.domain.Exer;
import kr.or.fineapple.domain.Routine;
import kr.or.fineapple.domain.common.Search;
import kr.or.fineapple.mapper.ExerMapper;
import kr.or.fineapple.service.exer.ExerService;


@Service("ExerServiceImpl")
public class ExerServiceImpl implements ExerService {

	
	@Autowired
	private ExerMapper exerMapper;

	
	@Override
	public void addUserService(ExerService service) throws Exception {
	
		
	}
	
	@Override
	public void getUserService(ExerService service) throws Exception {
		
		
	}
	
	
	@Override
	public void updateServiceTrgt(ExerService service) {
		
		
	}
	
	@Override
	public void updateBodyInfo(ExerService serivce) {
		
		
	}
	
	@Override
	public Map<String ,Object> getExerList(Search search) {
		
		System.out.println("getExerListService");
		
		List<Search> list = exerMapper.getExerList(search);
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("list", list);
		map.put("search", search);
		
		return map;
		
	}
	
	@Override
	public Exer getExer(int exerNo) {
		
		return exerMapper.getExer(exerNo);
			
	}
	
	@Override
	public void updateUserService(ExerService serivce) throws Exception {
		
		
	}

	@Override
	public int postUpdateExer(Exer exer) {
		return exerMapper.updateExer(exer);
		
	}

	@Override
	public int deleteExer(int exerNo) {
		return exerMapper.deleteExer(exerNo);
			
	}

	@Override
	public void addExer(Exer exer) throws Exception {

		exerMapper.addExer(exer);
		
	}
	
	@Override
	public void addDailyBurnning(BurnningRecord record) {
		
		
	}

	
	@Override
	public int addRoutine(Routine routine) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Routine getRoutine(int routineNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateRoutine(Routine routine) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteRoutine(int routineNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addRoutineInfo(Routine routine) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Routine getRoutineInfo(int routineInfoNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteRoutineInfo(int routineInfoNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Object> recommandExerList(Search search) {

		System.out.println("recommandExerListtService");
		
		
		
		
		return null;
	}

	@Override
	public int searchExerPlace() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	


}
