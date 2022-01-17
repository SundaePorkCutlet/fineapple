package kr.or.fineapple.domain;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TotalRecord {
	
	////식단 서비스
	LocalDate theHighestIntakeDate;
	
	//항상 계산해서 가져오는 값으로 사용자가 기록 수정시 변할 수 있음(badge도에인에 담기는 값은 변하지 않음)
	Double totalIntakeKcal;
	Double totalIntakeCarb;
	Double totalIntakeProtein;
	Double totalIntakeFat;
	
	////운동 서비스
	int totalExerTime;
	Double totalBurnningKcal;
	
}
