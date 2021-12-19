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
	String gender;
	int blcRgsStt;
	int userLeaveStt;
	String userImg;
	String cellNum;
	String userName;
	String password;
	float heignt;
	float age;
	LocalDate regDate;
	float strdWtrIntake;
	String UserSttMsg;
	LocalDateTime lastConTime;
	LocalDate blcAddDate;
	String blcAddWhy;
	int dietServiceNo;
	int exerServiceNo;
	String role; 
	float trgtweight;
	float trgtbodyFat;
	float trgtbodyMuscle;
	
}
