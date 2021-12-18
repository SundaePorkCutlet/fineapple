package kr.or.fineapple.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Food {

	int foodNo;
	String foodCate;
	Boolean foodIntoStt;
	String foodName;
	int servingSize;
	int foodKcal;
	int foodCarb;
	int foodProtein;
	int foodSaturatedFattyAcid;
	int foodUnsaturatedFattyAcid;
	int foodCholesterol;
	int foodTransFat;
	int foodSodium;
	int foodSugar;
	int price;
	String foodImg;
	String purchaseConnLink;
	String storeName;
	Boolean isAPI;
	
	
	
	
	public Food() {
	
	}


}
