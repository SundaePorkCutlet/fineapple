package kr.or.fineapple.domain;

import java.time.LocalTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Routine {

	
	int routineNo;
	int exerNo;
	String routineName;
	int totalExerSetCount; 
	Double routineKcal;
	LocalTime routineTime;
	LocalTime aSetTime;
	LocalTime restTime;
	Exer exer;
	String userId;
	LocalTime anExerTime;
	
	public Routine() {
		
	}
}
