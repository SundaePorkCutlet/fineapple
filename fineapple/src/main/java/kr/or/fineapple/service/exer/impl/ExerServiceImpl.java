package kr.or.fineapple.service.exer.impl;

import java.time.LocalDate;
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
			exerMapper.getUserService(service.getUserId());
			//exerMapper.updateUserService(service);
			
			
			return exerMapper.addUserService(service);
			
	}
	
	@Override
	public int updateExerServiceNo(ExerServ service) throws Exception {
		
		return 	exerMapper.updateExerServiceNo(service);
		
	}

	
	
	@Override
	public ExerServ getUserService(String userId) throws Exception {
		
		
		return exerMapper.getUserService(userId);
		
		
	}
	
	@Override
	public int updateUserService(ExerServ service) throws Exception {
		
		exerMapper.updateExerServiceNo(service);
		exerMapper.updateBodyInfo(service);
		

		System.out.println("updateUserServiceImpl 들어옴");
		System.out.println(service);
		
		return exerMapper.updateUserService(service);
		
		
	}
	
	@Override
	public List<Exer> getExerListJSON() throws Exception {		
		
		return exerMapper.getExerListJSON();		
	}
	

	
	@Override
	public Map<String, Object> getExerList(Search search) throws Exception {
		
		System.out.println("getExerListService");
		
		List<Search> list = exerMapper.getExerList(search);
		
		//int totalCount = exerMapper.getTotalCount(search);
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("list", list);
		map.put("search", search);
		//map.put("totalCount",new Integer(totalCount) );
		
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
	public int updateRoutineName(Routine routine) throws Exception {
		
		return exerMapper.updateRoutineName(routine);
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
		
		exerMapper.updateRoutine(routine);
		
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
	public Routine getRoutineInfo(int routineInfoNo) throws Exception {
	
		return exerMapper.getRoutineInfo(routineInfoNo);
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
		
		return 0;
	}

	
	
	@Override
	public int addDailyBurnning(BurnningRecord record) {
		
		exerMapper.updateBurnningRecord(record);
		
		
		return exerMapper.addDailyBurnning(record);
		
	}
	
	@Override
	public List<BurnningRecord> getBurnningRecordList(int userServiceNo) throws Exception {
	
		return exerMapper.getBurnningRecordList(userServiceNo);
	}

	@Override
	public int updateBurnningRecord(BurnningRecord record) throws Exception {
		
		return exerMapper.updateBurnningRecord(record);
	}

	@Override
	public Double sumBurnningKcal(int userServiceNo) throws Exception {
	
		return exerMapper.sumBurnningKcal(userServiceNo);
	}

	@Override
	public void deleteBurnningRecord(int burnningRecordNo) throws Exception {
		
		exerMapper.deleteBurnningRecord(burnningRecordNo);
		
		
	}

	////다이어리 진행자 하리니가 작성: 특정 일자의 일일 운동량 정보 조회
	@Override
	public List<Object> getBurnningRecordListForDiary(LocalDate startDate, LocalDate endDate, int userServiceNo) {
		///SELECT을 위한 WHERE 조건을 map에 넣어 전달
		Map<String, Object> map = new HashMap<>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("userServiceNo", userServiceNo);
		return exerMapper.getBurnningRecordListForDiary(map);
	}



	


}
