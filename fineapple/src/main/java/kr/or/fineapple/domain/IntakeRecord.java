package kr.or.fineapple.domain;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class IntakeRecord {

	int intakeRecordNo;
	float dailyTrgtIntakeKcal;
	float dailyTrgtIntakeCarb;
	float dailyTrgtIntakeProtein;
	float dailyTrgtIntakeFat;
	float foodKcal;
	float foodCarb;
	float foodProtein;
	float foodFat;
	String dietServiceTrgt;
	float userFoodIntake;
	float remainKcal;
	String meal;
	float bodyFat;
	float trgtBodyFat;
	LocalDate date;
	Food food;
	
	
	
	
	public IntakeRecord() {
		// TODO Auto-generated constructor stub
	}

}
