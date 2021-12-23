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
	double foodSaturatedFattyAcid;
	double foodUnsaturatedFattyAcid;
	double foodCholesterol;
	double foodTransFat;
	double foodSodium;
	double foodSugar;
	int price;
	String foodImg;
	String purchaseConnLink;
	String storeName;
	int isAPI;
	
	public Food() {
	
	}

	public int getFoodNo() {
		return foodNo;
	}

	public void setFoodNo(int foodNo) {
		this.foodNo = foodNo;
	}

	public int getFoodIntoStt() {
		return foodIntoStt;
	}

	public void setFoodIntoStt(int foodIntoStt) {
		this.foodIntoStt = foodIntoStt;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public double getServingSize() {
		return servingSize;
	}

	public void setServingSize(double servingSize) {
		this.servingSize = servingSize;
	}

	public double getFoodKcal() {
		return foodKcal;
	}

	public void setFoodKcal(double foodKcal) {
		this.foodKcal = foodKcal;
	}

	public double getFoodCarb() {
		return foodCarb;
	}

	public void setFoodCarb(double foodCarb) {
		this.foodCarb = foodCarb;
	}

	public double getFoodProtein() {
		return foodProtein;
	}

	public void setFoodProtein(double foodProtein) {
		this.foodProtein = foodProtein;
	}

	public double getFoodSaturatedFattyAcid() {
		return foodSaturatedFattyAcid;
	}

	public void setFoodSaturatedFattyAcid(double foodSaturatedFattyAcid) {
		this.foodSaturatedFattyAcid = foodSaturatedFattyAcid;
	}

	public double getFoodUnsaturatedFattyAcid() {
		return foodUnsaturatedFattyAcid;
	}

	public void setFoodUnsaturatedFattyAcid(double foodUnsaturatedFattyAcid) {
		this.foodUnsaturatedFattyAcid = foodUnsaturatedFattyAcid;
	}

	public double getFoodCholesterol() {
		return foodCholesterol;
	}

	public void setFoodCholesterol(double foodCholesterol) {
		this.foodCholesterol = foodCholesterol;
	}

	public double getFoodTransFat() {
		return foodTransFat;
	}

	public void setFoodTransFat(double foodTransFat) {
		this.foodTransFat = foodTransFat;
	}

	public double getFoodSodium() {
		return foodSodium;
	}

	public void setFoodSodium(double foodSodium) {
		this.foodSodium = foodSodium;
	}

	public double getFoodSugar() {
		return foodSugar;
	}

	public void setFoodSugar(double foodSugar) {
		this.foodSugar = foodSugar;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getFoodImg() {
		return foodImg;
	}

	public void setFoodImg(String foodImg) {
		this.foodImg = foodImg;
	}

	public String getPurchaseConnLink() {
		return purchaseConnLink;
	}

	public void setPurchaseConnLink(String purchaseConnLink) {
		this.purchaseConnLink = purchaseConnLink;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public int getIsAPI() {
		return isAPI;
	}

	public void setIsAPI(int isAPI) {
		this.isAPI = isAPI;
	}


}
