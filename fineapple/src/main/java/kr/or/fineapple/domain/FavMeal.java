package kr.or.fineapple.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FavMeal {

	int favMealNo;
	String favMealName;
	int favMealKcal;
	Food food;
	
	
	public FavMeal() {
		// TODO Auto-generated constructor stub
	}

}
