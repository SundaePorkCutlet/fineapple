package kr.or.fineapple.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.or.fineapple.domain.Achievement;
import kr.or.fineapple.domain.Badge;
import kr.or.fineapple.domain.UserBodyInfo;
import kr.or.fineapple.domain.UserEvent;
import kr.or.fineapple.domain.UserServ;
import kr.or.fineapple.domain.common.ViewDuration;

@Mapper
@Repository
public interface DiaryMapper {
	
	////사용자 이벤트 추가(1일 최대 5개까지 등록)
	void addUserEvent(UserEvent userEvent);
	
	////해당일자에 등록된 사용자 이벤트의 갯수 조회(service에만 있어 ViewDuration 대신 Map 사용)
	int getUserEventCount(Map map);
	
	////사용자 이벤트 조회
	UserEvent getUserEvent(int userEventNo);
	
	////사용자 이벤트 목록 조회
	List<Object> getUserEventList(ViewDuration viewDuration);
	
	////사용자 이벤트 수정
	void updateUserEvent(UserEvent userEvent);
	
	////대표 사용자 이벤트 변경을 위한 대표이벤트 등록 모두 해제(service에만 있음)
	void updatePreKeyUserEventStt(int userEventNo);
	
	////대표 사용자 이벤트 변경
	void updateKeyUserEventStt(int userEventNo);
	
	////사용자 이벤트 삭제
	void deleteUserEvent(int userEventNo);
	
	////사용자 신체 변화 정보 조회
	List<Object> getUserBodyInfoList(ViewDuration viewDuration);
	
	////사용자 신체 변화 정보 기록 및 변경(디폴트가 최신 수치 유지이므로 사용자는 add하지 않음)
	void updateUserBodyInfo(UserBodyInfo userBodyInfo);
	
	////다이어리 화면 출력을 위한 뱃지 리스트 조회
	List<Object> getBadgeList(ViewDuration viewDuration);
	
	////기간 내 총 뱃지 갯수 조회
	Badge getBadgeTotalCount(ViewDuration viewDuration);
	
	////다이어리 화면 출력을 위한 기간 내 대표 이벤트 제목 리스트 조회
	List<Object> getKeyEventTitleList(ViewDuration viewDuration);

	////회원의 식단 서비스/운동 서비스 목표 정보 조회
	UserServ getUserServiceDetails(String userId);
	
	////회윈의 식단 서비스 목표 달성률 계산해 조회
	Achievement getDietAchievement(ViewDuration viewDuration);
	
	////회윈의 운동 서비스 목표 달성률 계산해 조회
	Integer getExerAchievement(ViewDuration viewDuration);

}
