package kr.or.fineapple.domain;

import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Exer {

	
	int exerNo;
	int exerLv;
	String exerCate;
	String exerName;
	Double exerKcal;
	String exerVideoName;
	LocalTime exerVideoTime;
	String dodbogi;
	
	public Exer() {
		
	}


	public String setExerVideoTime(int h, int m, int s) {
		
		return h+":"+m+":"+s;
	}
	
}
