package kr.or.fineapple.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
	String userId;
	String userName;
	String password;
	String gender;
	String cellphone;
	String userImg;
	String role; 
	int blcRgsStt;
	int userLeaveStt;
	double height;
	double age;
	LocalDate regDate;
	double strdWtrIntake;
	String userSttMsg;
	LocalDateTime lastConTime;
	LocalDate blcAddDate;
	String blcAddWhy;
	int dietServiceNo;
	int exerServiceNo;
	double trgtWeight;
	double trgtBodyFat;
	double trgtBodyMuscle;
	String serviceTrgt;
	
	public User() {
		
	}

	
}



