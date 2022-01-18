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
		////trgtHabit�� userId ����
		trgtHabit.setUserId(userId);
		////trgtHabit �߰�
		trgtHabitMapper.addTrgtHabit(trgtHabit);
	}
	
	@Override
	public List<Object> getTrgtHabitList(ViewDuration viewDuration) {
		////�Ⱓ �� ����� ���� ���� ���� ��� ��ȸ�� ���� parameter
		//viewDuration �� userId, startDate, endDate �Ǵ� userId, date
		List<Object> list = trgtHabitMapper.getTrgtHabitList(viewDuration);
		
		return list;
	}

	@Override
	public int getUsingTrgtHabit(String userId, int trgtHabitCateNo) {
		///SELECT�� ���� WHERE ������ map�� �־� ����
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("trgtHabitCateNo", trgtHabitCateNo);
		
		return trgtHabitMapper.getUsingTrgtHabit(map);
	}

	@Override
	public LocalDate getTheLatestInitDate(String userId, int trgtHabitCateNo) {
		///SELECT�� ���� WHERE ������ map�� �־� ����
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("trgtHabitCateNo", trgtHabitCateNo);
		return trgtHabitMapper.getTheLatestInitDate(map);
	}

	@Override
	public TrgtHabit getTrgtHabit(String userId, LocalDate viewDate, int trgtHabitCateNo) {
		///SELECT�� ���� WHERE ������ map�� �־� ����
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
		////���� ��ǥ �ϼ� �ʱ�ȭ
		trgtHabitMapper.endTrgtHabit(trgtHabitServiceNo);
	}

	@Override
	public double getWtrIntake(String userId, LocalDate date) {
		////�ش����� ��� ��ȸ
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("date", date);
		
		Double userWtrIntake = trgtHabitMapper.getWtrIntake(map);
		
		////�ش����� ����� ���� ��� ����(==>����� �α��� �� ����� �����ǹǷ� ������� ����(userWtrIntake�� null�� ��� ����))
		if(userWtrIntake == null) {
			trgtHabitMapper.addWtrIntake(userId);
			userWtrIntake = 0.0;
		}
		return userWtrIntake;
	}

	@Override
	public double updateWtrIntake(String userId, double userWtrIntake) {
		////����ڰ� �Է��� ���м��뷮 ������Ʈ
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("userWtrIntake", userWtrIntake);
		trgtHabitMapper.updateWtrIntake(map);
		
		return getWtrIntake(userId, LocalDate.now());
	}

}
