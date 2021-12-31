package kr.or.fineapple.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExerServ {

	double dailyTrgtBurnningKcal;
	double trgtBodyMuscle;
	double bodyMuscle;
	String exerServiceTrgt;
	String userId;
	int	userServiceNo;
	
	public ExerServ() {
		
	}
	
}
