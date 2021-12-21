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

	double dailyTrgtIntakeKcal;
	double dailyTrgtIntakeCarb;
	double dailyTrgtIntakeProtein;
	double dailyTrgtIntakeFat;
	double foodKcal;
	double foodCarb;
	double foodProtein;
	double foodFat;
	String dietServiceTrgt;

	double userFoodIntake;
	double userWtrIntake;
	double remainKcal;
	String meal;

	double bodyFat;
	double trgtBodyFat;


	LocalDate date;
	Food food;
	
	
	
	
	public IntakeRecord() {
		// TODO Auto-generated constructor stub
	}

}
