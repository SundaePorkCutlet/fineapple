package kr.or.fineapple.service.diet.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.fineapple.domain.IntakeRecord;
import kr.or.fineapple.mapper.DietMapper;
import kr.or.fineapple.service.diet.DietService;

@Service
public class DietServiceImpl implements DietService{

	@Autowired
	private DietMapper dietMapper;
	
	@Override
	public void addDietService(IntakeRecord record) throws Exception {
			dietMapper.insertDietService(record);
	}

	
}
