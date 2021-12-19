package kr.or.fineapple.domain;

import java.sql.Time;

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
	double exerKcal;
	String exerVideoName;
	Time exerVideoTime;
	
	
	public Exer() {
		
	}
}
