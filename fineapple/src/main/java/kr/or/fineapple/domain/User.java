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
	Double height;
	Double age;
	LocalDate regDate;
	Double strdWtrIntake;
	String userSttMsg;
	LocalDateTime lastConTime;
	LocalDate blcAddDate;
	String blcAddWhy;
	int dietServiceNo;
	int exerServiceNo;
	Double trgtWeight;
	Double trgtBodyFat;
	Double trgtBodyMuscle;
	String serviceTrgt;
	Double weight;
	
	public User() {
		
	}

}



