package kr.or.fineapple.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FavMeal {
	
	String userId;
	int userServiceNo;
	int favMealNo;
	String foodCd;
	int favMealInfoNo;
	String favMealName;
	double favMealKcal;
	Food food;
	double Intake;
	double totalKcal;
	
	
	public FavMeal() {
		// TODO Auto-generated constructor stub
	}

}
