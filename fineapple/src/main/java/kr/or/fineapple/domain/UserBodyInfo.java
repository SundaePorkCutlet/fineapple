package kr.or.fineapple.domain;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserBodyInfo {

	////회원정보
	String userId;

	////일자
	LocalDate date;
	
	////회원의 체중, 체지방량, 골격근량
	double weight;
	double bodyFat;
	double bodyMuscle;
}
