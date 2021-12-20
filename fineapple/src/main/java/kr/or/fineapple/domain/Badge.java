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
	
}
