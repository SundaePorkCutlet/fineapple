package kr.or.fineapple.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString(exclude = "routineTime")
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
	String hour;
	String min;
	String sec;
	String anExerTime;
	Double anExerKcal;
	
	
	public Routine() {
		
	}


	public String getRoutineTime() {
		return routineTime;
	}


	public void setRoutineTime(String hour, String min, String sec) {
		
		this.routineTime = hour + min + sec;
		
		
		/*if(routineTime !=null) {
			// JSON ==> Domain Object  Binding을 위해 추가된 부분
			//this.setRoutineTime( routineTime.split(":")[0]
													+":"+ routineTime.split(":")[1]
													+ ":" +routineTime.split(":")[2] );
		}
		
		*/
	}


	
	

	
	
}
