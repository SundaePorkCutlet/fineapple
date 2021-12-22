package kr.or.fineapple.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Food {

	int foodNo;
	int foodIntoStt;
	String foodName;
	double servingSize;
	double foodKcal;
	double foodCarb;
	double foodProtein;
	double foodSodium;
	double foodSugar;
	int price;
	String foodImg;
	String purchaseConnLink;
	String storeName;
	int isAPI;
	
	
	
	
	public Food() {
	
	}


}
