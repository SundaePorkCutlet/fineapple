package kr.or.fineapple.domain;

import java.time.LocalTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BurnningRecord {

	
	int burnningRecordNo;
	double dailyTrgtBurnningKcal;
	double recommendExerKcal;
	String exerServiceTrgt;
	int trgtWeight;
	int bodyMuscle;
	int trgtBodyMuscle;
	LocalTime anExerTime;
	LocalTime dailyExerTime;
	double dailyExerKcal;
	List<Exer> exer;
	
	public BurnningRecord() {
		
	}
}
