package kr.or.fineapple.service.trgtHabit.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.fineapple.domain.TrgtHabit;
import kr.or.fineapple.domain.common.ViewDuration;
import kr.or.fineapple.mapper.TrgtHabitMapper;
import kr.or.fineapple.service.trgtHabit.TrgtHabitService;

@Service
public class TrgtHabitServiceImpl implements TrgtHabitService {
	
	@Autowired
	private TrgtHabitMapper trgtHabitMapper;

	@Override
	public void addTrgtHabit(String userId, TrgtHabit trgtHabit) {
		////trgtHabit에 userId 셋팅
		trgtHabit.setUserId(userId);
		////trgtHabit 추가
		trgtHabitMapper.addTrgtHabit(trgtHabit);
	}
	
	@Override
	public List<Object> getTrgtHabitList(ViewDuration viewDuration) {
		////기간 내 사용자 습관 서비스 정보 모두 조회를 위한 parameter
		//viewDuration 내 userId, startDate, endDate 또는 userId, date
		List<Object> list = trgtHabitMapper.getTrgtHabitList(viewDuration);
		
		return list;
	}

	@Override
	public int getUsingTrgtHabit(String userId, int trgtHabitCateNo) {
		///SELECT을 위한 WHERE 조건을 map에 넣어 전달
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("trgtHabitCateNo", trgtHabitCateNo);
		
		return trgtHabitMapper.getUsingTrgtHabit(map);
	}

	@Override
	public TrgtHabit getTrgtHabit(String userId, LocalDate viewDate, int trgtHabitCateNo) {
		///SELECT을 위한 WHERE 조건을 map에 넣어 전달
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("viewDate", viewDate);
		map.put("trgtHabitCateNo", trgtHabitCateNo);
		
		return trgtHabitMapper.getTrgtHabit(map);
	}
	
	@Override
	public String getUserHabitName(String userId) {
		return trgtHabitMapper.getUserHabitName(userId);
	}

	@Override
	public void endTrgtHabit(int trgtHabitServiceNo) {
		////습관 목표 일수 초기화
		trgtHabitMapper.endTrgtHabit(trgtHabitServiceNo);
	}

	@Override
	public double getWtrIntake(String userId, LocalDate date) {
		////해당일자 기록 조회
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("date", date);
		
		Double userWtrIntake = trgtHabitMapper.getWtrIntake(map);
		
		////해당일자 기록이 없는 경우 생성
		if(userWtrIntake == null) {
			trgtHabitMapper.addWtrIntake(userId);
			userWtrIntake = 0.0;
		}
		return userWtrIntake;
	}

	@Override
	public double updateWtrIntake(String userId, double userWtrIntake) {
		////사용자가 입력한 수분섭취량 업데이트
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("userWtrIntake", userWtrIntake);
		trgtHabitMapper.updateWtrIntake(map);
		
		return getWtrIntake(userId, LocalDate.now());
	}

}
