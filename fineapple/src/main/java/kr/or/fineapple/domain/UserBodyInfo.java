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

	////신체 변화 정보 번호
	int userBodyInfoNo;
	////일자
	LocalDate date;
	
	////회원의 체중, 체지방량, 골격근량
	Double weight;
	Double bodyFat;	//null 허용 위해 wrapper class 사용
	Double bodyMuscle;	//null 허용 위해 wrapper class 사용
	Double height;
}
