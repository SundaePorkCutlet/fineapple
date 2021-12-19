package kr.or.fineapple.service.diet.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.or.fineapple.domain.IntakeRecord;
import kr.or.fineapple.service.diet.DietDao;
import kr.or.fineapple.service.diet.DietService;

@Service("DietServiceImpl")
public class DietServiceImpl implements DietService{

	@Autowired
	@Qualifier("dietDaoImpl")
	private DietDao dietDao;
	public void setDietDao(DietDao dietDao) {
		this.dietDao = dietDao;
	}
	
	@Override
	public void addDietService(IntakeRecord record) throws Exception {
			
	}

	
	
	
}
