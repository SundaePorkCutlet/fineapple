package kr.or.fineapple.domain;

import java.time.LocalDate;

public class WtrIntake {
	
	////회원정보
	String userId;
	
	////수분섭취 관련 정보
	//일자
	LocalDate date;
	//사용자 수분섭취량
	double userWtrIntake;
}
