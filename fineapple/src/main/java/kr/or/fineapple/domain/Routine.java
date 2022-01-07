package kr.or.fineapple.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class Routine {

	int routineInfoNo;
	int routineNo;
	int exerNo;
	String exerLv;
	String exerName;
	String routineName;
	int totalExerSetCount; 
	Double routineKcal;
	String routineTime;
	Exer exer;
	String userId;
	
	String anExerTime;
	Double anExerKcal;
	
	
	public Routine() {
		
	}



	
	

	
	
}
