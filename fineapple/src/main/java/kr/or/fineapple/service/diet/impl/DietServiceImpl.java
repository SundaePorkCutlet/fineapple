package kr.or.fineapple.service.diet.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.fineapple.domain.DietServ;
import kr.or.fineapple.domain.IntakeRecord;
import kr.or.fineapple.mapper.DietMapper;
import kr.or.fineapple.service.diet.DietService;

@Service
public class DietServiceImpl implements DietService{

	@Autowired
	private DietMapper dietMapper;
	
	
	@Override
	public int addDietService(DietServ diet) throws Exception {
		return	dietMapper.insertDietService(diet);
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

	
}
