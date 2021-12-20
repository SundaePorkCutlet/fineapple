package kr.or.fineapple.domain;

import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BurnningRecord {

	String userId;
	int burnningRecordNo;
	double dailyTrgtBurnningKcal;
	double recommendExerKcal;
	String exerServiceTrgt;
	double trgtWeight;
	double bodyMuscle;
	double trgtBodyMuscle;
	int anExerTime;
	LocalTime dailyExerTime;
	double dailyExerKcal;
	/*ist<Exer> exer;*/
	Exer exer;
	int exerLv;
	double userExerBurnning;
	
	public BurnningRecord() {
		
	}

	
	public String setAnExerTime(int h, int m, int s) {
		
		return h+":"+m+":"+s;
		
		
	}


	public String setDailyExerTime(int h, int m, int s) {
		
		return h+":"+m+":"+s;
		
	}
	
	
}
