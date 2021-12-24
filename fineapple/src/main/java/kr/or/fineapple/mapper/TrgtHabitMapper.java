package kr.or.fineapple.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.or.fineapple.domain.TrgtHabit;

@Mapper
@Repository
public interface TrgtHabitMapper {

	////목표 습관 관리 시작
	void addTrgtHabit(TrgtHabit trgtHabit);
	
	////목표 습관 관리 진행 확인
	TrgtHabit getTrgtHabit(Map map);
	
	////사용자정의 목표습관명 조회
	String getUserHabitName(String userId);
	
	////목표 초기화
	void endTrgtHabit(int trgtHabitServiceNo);
	
	////수분 섭취량 기록 시작
	void addWtrIntake(String userId);

	////수분 섭취량 조회
	Double getWtrIntake(Map map);
	
	////수분 섭취량 기록 변경
	void updateWtrIntake(Map map);
}
