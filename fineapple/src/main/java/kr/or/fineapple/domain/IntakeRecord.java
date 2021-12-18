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
	int dailyTrgtIntakeKcal;
	int dailyTrgtIntakeCarb;
	int dailyTrgtIntakeProtein;
	int dailyTrgtIntakeFat;
	int foodKcal;
	int foodCarb;
	int foodProtein;
	int foodFat;
	String dietServiceTrgt;
	int userFoodIntake;
	int remainKcal;
	String meal;
	int bodyFat;
	int trgtBodyFat;
	LocalDate date;
	Food food;
	
	
	
	
	public IntakeRecord() {
		// TODO Auto-generated constructor stub
	}

}
