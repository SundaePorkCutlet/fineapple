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
	String routineName;
	int totalExerSetCount; 
	LocalTime aSetTime;
	LocalTime restTime;
	List<Exer> exer;
	
	
	public Routine() {
		
	}
}
