package kr.or.fineapple.domain;

import java.time.LocalDate;

public class WeeklyPaper {

	////회원정보
	String userId;
	
	////기간
	LocalDate startingDate;
	LocalDate endingDate;
	
	////제공정보(Monthly에도 제공)
	UserBodyInfo userBodyInfo;
	UserEvent userEvent;
	Badge badge;
	TrgtHabit trgtHabit;
}
