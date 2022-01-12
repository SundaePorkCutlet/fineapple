package kr.or.fineapple.domain;

import lombok.Getter;
import lombok.ToString;
import lombok.Setter;

@Getter
@Setter
@ToString
public class TotalRecord {
	//항상 계산해서 가져오는 값으로 사용자가 기록 수정시 변할 수 있음(badge도에인에 담기는 값은 변하지 않음)
	Double totalIntakeKcal;
	Double totalIntakeCarb;
	Double totalIntakeProtein;
	Double totalIntakeFat;
}
