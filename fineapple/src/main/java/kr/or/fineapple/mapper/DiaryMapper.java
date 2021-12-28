package kr.or.fineapple.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.or.fineapple.domain.Diary;
import kr.or.fineapple.domain.UserBodyInfo;
import kr.or.fineapple.domain.UserEvent;

@Mapper
@Repository
public interface DiaryMapper {
	
	////다이어리(캘린더포맷) 조회
	Diary getDiary(Map map);
	
	////사용자 이벤트 추가(1일 최대 5개까지 등록)
	void addUserEvent(UserEvent userEvent);
	
	////해당일자에 등록된 사용자 이벤트의 갯수 조회(service에만 있음)
	int getUserEventCount(Map map);
	
	////사용자 이벤트 조회
	UserEvent getUserEvent(int userEventNo);
	
	////사용자 이벤트 목록 조회
	List<UserEvent> getUserEventList(Map map);
	
	////사용자 이벤트 수정
	void updateUserEvent(UserEvent userEvent);
	
	////대표 사용자 이벤트 변경을 위한 대표이벤트 등록 모두 해제(service에만 있음)
	void updatePreKeyUserEventStt(int userEventNo);
	
	////대표 사용자 이벤트 변경
	void updateKeyUserEventStt(int userEventNo);
	
	////사용자 이벤트 삭제
	void deleteUserEvent(int userEventNo);
	
	////사용자 신체 변화 정보 조회
	List<UserBodyInfo> getUserBodyInfoList(Map map);
	
	////사용자 신체 변화 정보 기록 및 변경(디폴트가 최신 수치 유지이므로)
	void updateUserBodyInfo(UserBodyInfo userBodyInfo);
	
}
