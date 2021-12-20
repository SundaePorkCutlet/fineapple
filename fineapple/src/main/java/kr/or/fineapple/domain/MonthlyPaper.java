package kr.or.fineapple.domain;

import java.time.LocalDate;

public class MonthlyPaper {

	////회원정보
	String userId;
	
	////기간
	LocalDate startingDate;
	LocalDate endingDate;
	
	////제공정보(Weekly와 동일)
	UserBodyInfo userBodyInfo;
	UserEvent userEvent;
	Badge badge;
	TrgtHabit trgtHabit;
	
	////Monthly에 추가로 제공되는 정보
	//주별 일평균 섭취 소모 칼로리(리스트써야함)
	double dailyAverageIntakeKcalPerWeek;
	double dailyAverageBurnningKcalPerWeek;
	//섭취 소모 칼로리가 가장 높은 주의 총 섭취 소모 칼로리(추가 가공후 필드 필요할수잇음..)
	double theHighestIntakeKcalWeek;
	double theHighestBurnningKcalWeek;
	//월 중 가장 섭취 소모 칼로리가 높은 날의 섭취 소모 칼로리
	double theHighestIntakeKcalDay;
	double theHighestBurnningKcalDay;
}
