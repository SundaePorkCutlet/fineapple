package kr.or.fineapple.domain;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class IntakeRecord {

	
	String userId;
	int intakeRecordNo;
	double foodKcal;
	double foodCarb;	
	double foodProtein;
	double foodFat;
	double userFoodIntake; //사용자가 입력하는 칼로리
	double userWtrIntake;
	double remainKcal; //사용자 잔여칼로리?
	String meal;
	LocalDate date;
	Food food;
	
	public IntakeRecord() {
		// TODO Auto-generated constructor stub
	}

}
