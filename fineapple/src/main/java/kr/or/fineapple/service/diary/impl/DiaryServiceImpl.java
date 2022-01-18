package kr.or.fineapple.service.diary.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.fineapple.domain.Achievement;
import kr.or.fineapple.domain.Badge;
import kr.or.fineapple.domain.BurnningRecord;
import kr.or.fineapple.domain.UserBodyInfo;
import kr.or.fineapple.domain.UserEvent;
import kr.or.fineapple.domain.UserServ;
import kr.or.fineapple.domain.common.ViewDuration;
import kr.or.fineapple.mapper.DiaryMapper;
import kr.or.fineapple.service.diary.DiaryService;

@Service
public class DiaryServiceImpl implements DiaryService {
	
	@Autowired
	private DiaryMapper diaryMapper;

	@Override
	public void addUserEvent(UserEvent userEvent) {
		////�ش����ڿ� ��ϵ� ����� �̺�Ʈ ���� ��ȸ
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userEvent.getUserId());
		map.put("eventDate", userEvent.getEventDate());
		////����� �̺�Ʈ�� 4�� ������ ��쿡�� ��� ����(�Ϸ� 5�� ��� ����)
		int eventCount = diaryMapper.getUserEventCount(map);
		if(eventCount <= 4) {
			//ù��° �̺�Ʈ�� ��� ��ǥ�̺�Ʈ�� ���
			if(eventCount == 0) {
				userEvent.setKeyEventStt(0);
				diaryMapper.addUserEvent(userEvent);
			} else {
			//1�� �̻��� ����� �̺�Ʈ�� ������ ��� �Ϲ� �̺�Ʈ�� ���	
			userEvent.setKeyEventStt(1);
			diaryMapper.addUserEvent(userEvent);
			}
		}
	}

	@Override
	public UserEvent getUserEvent(int userEventNo) {
		return diaryMapper.getUserEvent(userEventNo);
	}

	@Override
	public List<Object> getUserEventList(ViewDuration viewDuration) {
		////������̺�Ʈ ��� ��ȸ�� ���� parameter
		//viewDuration �� userId, date
		List<Object> list = diaryMapper.getUserEventList(viewDuration);
	
		return list;
	}

	@Override
	public void updateUserEvent(UserEvent userEvent) {
		diaryMapper.updateUserEvent(userEvent);
	}

	@Override
	public void updateKeyUserEventStt(int userEventNo) {
		////�ش����ڿ� ����� ������̺�Ʈ���� ��ǥ�̺�Ʈ ��� ���¸� ��� ����
		diaryMapper.updatePreKeyUserEventStt(userEventNo);
		
		////����ڰ� ������ �̺�Ʈ�� ��ǥ�̺�Ʈ�� ����
		diaryMapper.updateKeyUserEventStt(userEventNo);
	}

	@Override
	public void deleteUserEvent(int userEventNo) {
		////���� �̺�Ʈ ����
		diaryMapper.deleteUserEvent(userEventNo);
	}

	@Override
	public List<Object> getUserBodyInfoList(ViewDuration viewDuration) {
		////����� ��ü ��ȭ ���� ��ȸ�� ���� parameter
		//viewDuration �� userId, startDate, endDate
		List<Object> list = diaryMapper.getUserBodyInfoList(viewDuration);
		
		return list;
	}

	@Override
	public void updateUserBodyInfo(UserBodyInfo userBodyInfo) {
		diaryMapper.updateUserBodyInfo(userBodyInfo);
	}

	@Override
	public List<Object> getBadgeList(ViewDuration viewDuration) {
		////�Ⱓ �� ���� ��� ��ȸ�� ���� parameter
		//viewDuration �� userId, startDate, endDate
		List<Object> list = diaryMapper.getBadgeList(viewDuration);
		
		return list;
	}

	@Override
	public Badge getBadgeTotalCount(ViewDuration viewDuration) {
		////�Ⱓ �� ȹ���� ���� ���� ���� ��ȸ�� ���� parameter
		//viewDuration �� userId, startDate, endDate
		return diaryMapper.getBadgeTotalCount(viewDuration);
	}

	@Override
	public List<Object> getKeyEventTitleList(ViewDuration viewDuration) {
		////�Ⱓ �� ��ǥ�̺�Ʈ ���� ��� ��ȸ�� ���� parameter
		//viewDuration �� userId, startDate, endDate
		List<Object> list = diaryMapper.getKeyEventTitleList(viewDuration);

		return list;
	}

	@Override
	public UserServ getUserServiceDetails(String userId) {
		//ȸ���� �Ĵ� ����/� ���� ��ǥ ���� ��ȸ 
		return diaryMapper.getUserServiceDetails(userId);
	}

	@Override
	public Achievement getDietAchievement(ViewDuration viewDuration) {
		////ȸ���� �Ĵ� ���� ��ǥ �޼��� ����� ��ȸ
		return diaryMapper.getDietAchievement(viewDuration);
	}

	@Override
	public Integer getExerAchievement(ViewDuration viewDuration) {
		////ȸ���� � ���� ��ǥ �޼��� ����� ��ȸ
		return diaryMapper.getExerAchievement(viewDuration);
	}

	@Override
	public UserEvent getTheHighestIntakeKcalDayUserEvent(ViewDuration viewDuration) {
		////�Ⱓ �� ���� ���� Į�θ��� ū ���� �� ���� ��ǥ �̺�Ʈ ��ȸ
		return diaryMapper.getTheHighestIntakeKcalDayUserEvent(viewDuration);
	}

	@Override
	public LocalDate getTheLatestDateBadge(String userId) {
		return diaryMapper.getTheLatestDateBadge(userId);
	}

	@Override
	public LocalDate getTheLatestDateUserBodyInfo(String userId) {
		return diaryMapper.getTheLatestDateUserBodyInfo(userId);
	}
	
	@Override
	public void updateBadgeByDiet(String userId, int badgeCount, double inputKcal, LocalDate date) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("badgeCount", badgeCount);
		map.put("inputKcal", inputKcal);
		map.put("date", date);
		diaryMapper.updateBadgeByDiet(map);
	}

	@Override
	public void updateBadgeByExer(String userId, int badgeCount, double inputKcal, LocalDate date) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("badgeCount", badgeCount);
		map.put("inputKcal", inputKcal);
		map.put("date", date);
		diaryMapper.updateBadgeByExer(map);
	}

	@Override
	public void addUserBodyInfo(String userId, int howManyDays) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("inputDays", howManyDays-1);
		map.put("howManyDays", howManyDays);
		diaryMapper.addUserBodyInfo(map);
	}

	@Override
	public void addBadge(Badge defaultBadge) {
		diaryMapper.addBadge(defaultBadge);
	}
	
}
