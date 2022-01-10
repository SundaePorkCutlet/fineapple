package kr.or.fineapple.service.diary.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.fineapple.domain.Badge;
import kr.or.fineapple.domain.UserBodyInfo;
import kr.or.fineapple.domain.UserEvent;
import kr.or.fineapple.domain.common.ViewDuration;
import kr.or.fineapple.mapper.DiaryMapper;
import kr.or.fineapple.service.diary.DiaryService;

@Service
public class DiaryServiceImpl implements DiaryService {
	
	@Autowired
	private DiaryMapper diaryMapper;

	@Override
	public void addUserEvent(UserEvent userEvent) {
		////해당일자에 등록된 사용자 이벤트 갯수 조회
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userEvent.getUserId());
		map.put("eventDate", userEvent.getEventDate());
		////사용자 이벤트가 4개 이하일 경우에만 등록 진행(하루 5개 등록 가능)
		int eventCount = diaryMapper.getUserEventCount(map);
		if(eventCount <= 4) {
			//첫번째 이벤트일 경우 대표이벤트로 등록
			if(eventCount == 0) {
				userEvent.setKeyEventStt(0);
				diaryMapper.addUserEvent(userEvent);
			} else {
			//1개 이상의 등록한 이벤트가 존재할 경우 일반 이벤트로 등록	
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
		////사용자이벤트 모두 조회를 위한 parameter
		//viewDuration 내 userId, date
		List<Object> list = diaryMapper.getUserEventList(viewDuration);
	
		return list;
	}

	@Override
	public void updateUserEvent(UserEvent userEvent) {
		diaryMapper.updateUserEvent(userEvent);
	}

	@Override
	public void updateKeyUserEventStt(int userEventNo) {
		////해당일자에 등록한 사용자이벤트들의 대표이벤트 등록 상태를 모두 해제
		diaryMapper.updatePreKeyUserEventStt(userEventNo);
		
		////사용자가 지정한 이벤트를 대표이벤트로 설정
		diaryMapper.updateKeyUserEventStt(userEventNo);
	}

	@Override
	public void deleteUserEvent(int userEventNo) {
		////지정 이벤트 삭제
		diaryMapper.deleteUserEvent(userEventNo);
	}

	@Override
	public List<Object> getUserBodyInfoList(ViewDuration viewDuration) {
		////사용자 신체 변화 정보 조회를 위한 parameter
		//viewDuration 내 userId, startDate, endDate
		List<Object> list = diaryMapper.getUserBodyInfoList(viewDuration);
		
		return list;
	}

	@Override
	public void updateUserBodyInfo(UserBodyInfo userBodyInfo) {
		diaryMapper.updateUserBodyInfo(userBodyInfo);
	}

	@Override
	public List<Object> getBadgeList(ViewDuration viewDuration) {
		////기간 내 뱃지 모두 조회를 위한 parameter
		//viewDuration 내 userId, startDate, endDate
		List<Object> list = diaryMapper.getBadgeList(viewDuration);
		
		return list;
	}

	@Override
	public Badge getBadgeTotalCount(ViewDuration viewDuration) {
		////기간 내 획득한 뱃지 갯수 총합 조회를 위한 parameter
		//viewDuration 내 userId, startDate, endDate
		return diaryMapper.getBadgeTotalCount(viewDuration);
	}

	@Override
	public List<Object> getKeyEventTitleList(ViewDuration viewDuration) {
		////기간 내 대표이벤트 제목 모두 조회를 위한 parameter
		//viewDuration 내 userId, startDate, endDate
		List<Object> list = diaryMapper.getKeyEventTitleList(viewDuration);

		return list;
	}
}
