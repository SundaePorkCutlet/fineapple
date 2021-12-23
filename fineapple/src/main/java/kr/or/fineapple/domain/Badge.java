package kr.or.fineapple.domain;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Badge {

	////È¸¿øÁ¤º¸
	String userId;
	////¸ÅÀÏ(¹îÁö¸¦ È¸¼ö ÇÏÁö ¾ÊÀ½)
	LocalDate date;
	////ÇØ´ç ÀÏ È¹µæÇÑ ¹îÁö °¹¼ö
	int dietBadgeCount;
	int exerBadgeCount;
	int wtrBadgeCount;
	int bttlBadgeCount;
	////¹îÁö È¹µæ½ÃÀÇ ±â·Ï
	double dailyIntakeKcal;
	double dailyBurnningKcal;
	double dailyWtrIntake;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getDietBadgeCount() {
		return dietBadgeCount;
	}
	public void setDietBadgeCount(int dietBadgeCount) {
		this.dietBadgeCount = dietBadgeCount;
	}
	public int getExerBadgeCount() {
		return exerBadgeCount;
	}
	public void setExerBadgeCount(int exerBadgeCount) {
		this.exerBadgeCount = exerBadgeCount;
	}
	public int getWtrBadgeCount() {
		return wtrBadgeCount;
	}
	public void setWtrBadgeCount(int wtrBadgeCount) {
		this.wtrBadgeCount = wtrBadgeCount;
	}
	public int getBttlBadgeCount() {
		return bttlBadgeCount;
	}
	public void setBttlBadgeCount(int bttlBadgeCount) {
		this.bttlBadgeCount = bttlBadgeCount;
	}
	public double getDailyIntakeKcal() {
		return dailyIntakeKcal;
	}
	public void setDailyIntakeKcal(double dailyIntakeKcal) {
		this.dailyIntakeKcal = dailyIntakeKcal;
	}
	public double getDailyBurnningKcal() {
		return dailyBurnningKcal;
	}
	public void setDailyBurnningKcal(double dailyBurnningKcal) {
		this.dailyBurnningKcal = dailyBurnningKcal;
	}
	public double getDailyWtrIntake() {
		return dailyWtrIntake;
	}
	public void setDailyWtrIntake(double dailyWtrIntake) {
		this.dailyWtrIntake = dailyWtrIntake;
	}
	
	
	
	
	
}
