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
	Double recommendExerKcal;
	Double trgtWeight;
	LocalTime anExerTime;
	LocalTime dailyExerTime;
	Double dailyExerKcal;
	/*ist<Exer> exer;*/
	Exer exer;
	int exerLv;
	Double userExerBurnning;
	
	
	public BurnningRecord() {
		
	}

	
	public String setAnExerTime(int h, int m, int s) {
		
		return h+":"+m+":"+s;
		
		
	}


	public String setDailyExerTime(int h, int m, int s) {
		
		return h+":"+m+":"+s;
		
	}
	
	
}
