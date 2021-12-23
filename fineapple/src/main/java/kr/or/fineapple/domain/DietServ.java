package kr.or.fineapple.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DietServ {

	String UserId;
	int	userServiceNo;
	double dailyTrgtIntakeKcal;
	double dailyTrgtIntakeCarb;
	double dailyTrgtIntakeProtein;
	double dailyTrgtIntakeFat;
	String dietServiceTrgt;
	double bodyFat;
	double trgtBodyFat;
	String serviceTrgt;
	
	
	public DietServ() {
	}

}
