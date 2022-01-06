package kr.or.fineapple.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BurnningRecord {

	String userId;
	int exerNo;
	int burnningRecordNo;
	int userServiceNo;
	String exerName;
	int exerKcal;
	Double recommendExerKcal;
	Double trgtWeight;
	String anExerTime;
	String dailyExerTime;
	Double dailyExerKcal;
	/*ist<Exer> exer;*/
	Exer exer;
	String exerLv;
	Double userExerBurnning;
	LocalDate date;	
	String hour;
	String min;
	String sec;
	LocalDate burnningRecordDate;
	Double anExerKcal;
	
	
	
	public BurnningRecord() {
		
	}


	public String getAnExerTime() {
		return anExerTime;
	}


	public void setAnExerTime(String hour, String min, String sec) {
		
		this.anExerTime =  hour + min + sec;
		
		/*
		 * if(anExerTime !=null) { // JSON ==> Domain Object Binding을 위해 추가된 부분
		 * this.setAnExerTime( anExerTime.split(":")[0] +":"+ anExerTime.split(":")[1] +
		 * ":" +anExerTime.split(":")[2] ); }
		 */
	}
		
	


	public String getDailyExerTime() {
		return dailyExerTime;
	}


	public void setDailyExerTime(String dailyExerTime) {
		
		this.dailyExerTime = dailyExerTime;
		
		/*
		 * if(dailyExerTime !=null) { // JSON ==> Domain Object Binding을 위해 추가된 부분
		 * this.setDailyExerTime( dailyExerTime.split(":")[0] +":"+
		 * dailyExerTime.split(":")[1] + ":" +dailyExerTime.split(":")[2] ); }
		 */
		
	}

}
