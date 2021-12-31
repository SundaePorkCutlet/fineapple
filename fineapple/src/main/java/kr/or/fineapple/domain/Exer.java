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
	Double recommTime;


	public Exer() {
		
	}
	
	
	
}
