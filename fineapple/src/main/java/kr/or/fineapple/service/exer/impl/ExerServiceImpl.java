package kr.or.fineapple.service.exer.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.fineapple.domain.BurnningRecord;
import kr.or.fineapple.domain.Exer;
import kr.or.fineapple.domain.ExerServ;
import kr.or.fineapple.domain.Routine;
import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.common.Search;
import kr.or.fineapple.mapper.ExerMapper;
import kr.or.fineapple.service.exer.ExerService;



@Service("ExerServiceImpl")
public class ExerServiceImpl implements ExerService {

	
	@Autowired
	private ExerMapper exerMapper;

	
	@Override
	public int addUserService(ExerServ service) throws Exception {
	
			exerMapper.updateServiceTrgt(service);
			exerMapper.updateBodyInfo(service);
			
			return exerMapper.addUserService(service);
			
	}
	
	@Override
	public ExerServ getUserService(String userId) throws Exception {
		
		
		return exerMapper.getUserService(userId);
		
		
	}
	
	@Override
	public int updateUserService(ExerServ serivce) throws Exception {
		
		return exerMapper.updateUserService(serivce);
		
		
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
	public int addDailyBurnning(BurnningRecord record) {
		
		
		return exerMapper.addDailyBurnning(record);
		
		
	}

	
	@Override
	public int addRoutine(Routine routine) {
		
		return exerMapper.addRoutine(routine);
	}

	@Override
	public Routine getRoutine(int routineNo) {
		
		return exerMapper.getRoutine(routineNo);
	}

	@Override
	public int updateRoutine(Routine routine) {
		
		return exerMapper.updateRoutine(routine);
	}

	@Override
	public int deleteRoutine(int routineNo) {
		
		return exerMapper.deleteRoutine(routineNo);
	}

	@Override
	public Map<String, Object> getRoutineList(int exerServiceNo) throws Exception {
		
		List<Routine> list = exerMapper.getRoutineList(exerServiceNo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		
		return map;
	}
	
	
	@Override
	public int addRoutineInfo(Routine routine) {

		return exerMapper.addRoutineInfo(routine);
	}

	
	@Override
	public int deleteRoutineInfo(int routineInfoNo) {

		return exerMapper.deleteRoutineInfo(routineInfoNo);
	}
	
	@Override
	public int updateRoutineInfo(Routine routine) {
		
		return exerMapper.updateRoutineInfo(routine);
	}
	
	@Override
	public Map<String, Object> getRoutineInfoList(int routineNo) {
		
		List<Routine> list = exerMapper.getRoutineInfoList(routineNo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("list", list);
		
		return map;
	}
	

	@Override
	public List<Exer> recommandExerList(Double overKcal) {

		System.out.println("recommandExerListtService");
	
		List list = exerMapper.recommandExerList(overKcal);
		 
		
		return list;
		
	}

	@Override
	public Double sumIntakeKcal(String userId) throws Exception {
		
		
		return exerMapper.sumIntakeKcal(userId);
	}
	
	@Override
	public User needDaliyIntakeKcal(String userId) throws Exception {
		
		
		return exerMapper.needDaliyIntakeKcal(userId);
	}

	
	


	@Override
	public int searchExerPlace() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	


}
