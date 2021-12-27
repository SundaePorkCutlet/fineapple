package kr.or.fineapple.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Diary {
	
	////회원정보
	String userId;
	
	////기간
	//월 기간 산정
	LocalDate startDate;
	LocalDate endDate;
	
	////다이어리 제공 정보(일별) : 뱃지, 대표사용자이벤트, 총 섭취소모 칼로리
	//일별 정보 디스플레이를 위한 일자
	LocalDate date;
	//일별 획득한 뱃지 갯수
	int dietBadgeCount;
	int exerBadgeCount;
	int wtrBadgeCount;
	int bttlBadgeCount;
	//일별 대표 사용자이벤트 제목
	String userEventTitle;
	//일별 총 섭취 소모 칼로리 
	double dailyIntakeKcal;
	double dailyBurnningKcal;
	
	////다이어리 제공 정보(월 전체) : 목표습관 진행 현황
	//목표습관카테고리 번호:이름
	int trgtHabitCateNo;
	String trgtHabitCateName;
	//목표습관 시작일자, 초기화 일자
	LocalDateTime trgtHabitStartDate;
	LocalDateTime trgtHabitInitDate;
}
