package kr.or.fineapple.service.diary.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.fineapple.domain.Diary;
import kr.or.fineapple.domain.UserBodyInfo;
import kr.or.fineapple.domain.UserEvent;
import kr.or.fineapple.mapper.DiaryMapper;
import kr.or.fineapple.service.diary.DiaryService;

@Service
public class DiaryServiceImpl implements DiaryService {
	
	@Autowired
	private DiaryMapper diaryMapper;

	@Override
	public Diary getDiary(String userId, LocalDate startDate, LocalDate endDate) {
		return null;
	}

	@Override
	public void addUserEvent(UserEvent userEvent) {
		////해당일자에 등록된 사용자 이벤트 갯수 조회
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userEvent.getUserId());
		map.put("eventDate", userEvent.getEventDate());
		////사용자 이벤트가 4개 이하일 경우에만 등록 진행(하루 5개 등록 가능)
		if(diaryMapper.getUserEventCount(map) <= 4) {
			diaryMapper.addUserEvent(userEvent);			
		}
	}

	@Override
	public UserEvent getUserEvent(int userEventNo) {
		return diaryMapper.getUserEvent(userEventNo);
	}

	@Override
	public Map<String, Object> getUserEventList(String userId, LocalDate eventDate) {
		////사용자이벤트 모두 조회를 위한 parameter
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userId", userId);
		paramMap.put("eventDate", eventDate);
		List<UserEvent> list = diaryMapper.getUserEventList(paramMap);
		
		////조회 결과를 map으로 return
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		
		return map;
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
	public Map<String, Object> getUserBodyInfoList(String userId, LocalDate startDate, LocalDate endDate) {
		////사용자 신체 변화 정보 조회를 위한 parameter
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userId", userId);
		paramMap.put("startDate", startDate);
		paramMap.put("endDate", endDate);
		List<UserBodyInfo> list = diaryMapper.getUserBodyInfoList(paramMap);
		
		////조회 결과를 map으로 return
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		
		return map;
	}

	@Override
	public void updateUserBodyInfo(UserBodyInfo userBodyInfo) {
		diaryMapper.updateUserBodyInfo(userBodyInfo);
	}

}
