package kr.or.fineapple.service.exer.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.fineapple.domain.BurnningRecord;
import kr.or.fineapple.domain.Exer;
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
	public void addDailyBurnning(BurnningRecord record) {

		
	}
	
	@Override
	public void updateServiceTrgt(ExerService service) {
		
		
	}
	
	@Override
	public void updateBodyInfo(ExerService serivce) {
		
		
	}
	
	@Override
	public Map<String ,Object> getExerList(Search search) {
		
		List<Search> list = exerMapper.getExerList(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("list", list);
		map.put("search", search);
		
		return map;
		
	}
	
	@Override
	public Exer getExer(String exerName) {
		
		return exerMapper.getExer(exerName);
			
	}
	
	
	@Override
	public void updateUserService(ExerService serivce) throws Exception {
		
		
	}

}
