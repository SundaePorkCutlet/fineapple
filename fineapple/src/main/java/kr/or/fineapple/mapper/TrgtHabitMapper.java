package kr.or.fineapple.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.or.fineapple.domain.TrgtHabit;
import kr.or.fineapple.domain.common.ViewDuration;

@Mapper
@Repository
public interface TrgtHabitMapper {

	////목표 습관 관리 시작
	void addTrgtHabit(TrgtHabit trgtHabit);
	
	////다이어리 화면 출력을 위한 습관서비스 정보 조회
	List<Object> getTrgtHabitList(ViewDuration viewDuration);
	
	////해당 습관 이미 진행중인지 여부 조회
	int getUsingTrgtHabit(Map map);
	
	////목표 습관 관리 진행 확인
	TrgtHabit getTrgtHabit(Map map);
	
	////사용자정의 목표습관명 조회
	String getUserHabitName(String userId);
	
	////목표 초기화
	void endTrgtHabit(int trgtHabitServiceNo);
	
	////수분 섭취량 기록 시작(조회 이전 기록 없을시 serviceImpl의 get메소드 내에서 실행함)
	void addWtrIntake(String userId);

	////수분 섭취량 조회
	Double getWtrIntake(Map map);
	
	////수분 섭취량 기록 변경
	void updateWtrIntake(Map map);
}
