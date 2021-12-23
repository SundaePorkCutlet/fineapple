package kr.or.fineapple.domain;

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
	String exerVideoTime;
	String dodbogi;
	String exerLink;
	
	


	public Exer() {
		
	}


	public String setExerVideoTime(int h, int m, int s) {
		
		return h+":"+m+":"+s;
	}
	
}
