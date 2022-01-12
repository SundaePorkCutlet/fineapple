package kr.or.fineapple.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Achievement {
	
	////식단 목표 달성률
	Integer intakeKcalInPercentage;
	Integer intakeCarbInPercentage;
	Integer intakeProteinInPercentage;
	Integer intakeFatInPercentage;
	
	////운동 목표 달성률
	Integer burnningKcalInPercentage;
}
