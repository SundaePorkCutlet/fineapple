package kr.or.fineapple.service.diet.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.fineapple.domain.DietServ;
import kr.or.fineapple.domain.Food;
import kr.or.fineapple.domain.IntakeRecord;
import kr.or.fineapple.domain.common.Search;
import kr.or.fineapple.mapper.DietMapper;
import kr.or.fineapple.service.diet.DietService;

@Service
public class DietServiceImpl implements DietService{

	@Autowired
	private DietMapper dietMapper;
	

	@Override
	public int addDietService(DietServ diet) throws Exception {
				dietMapper.updateServiceTtgt(diet);
				dietMapper.updateBodyInfo(diet);
		return	dietMapper.insertDietService(diet);
	}

	

	@Override
	public int updateDietService(DietServ diet) throws Exception {

		return dietMapper.updateDietService(diet);
	}



	@Override
	public DietServ getDietService(String userId) throws Exception {
		return dietMapper.getDietService(userId);
	}

	@Override
	public int addIntakeRecord(IntakeRecord record) throws Exception {

		// TODO Auto-generated method stub
		return dietMapper.insertIntakeRecord(record);
	}

	@Override
	public Map<String ,Object> getFoodList(Search search) throws Exception {
		
		List<Food> list = dietMapper.getFoodList(search);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("search", search);
		
		return map;
	}
	
	

	
}
