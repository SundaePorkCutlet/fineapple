package kr.or.fineapple.domain;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TrgtHabit {

	////회원정보
	String userId;
	
	////목표습관서비스 관련 정보
	//회원의 목표습관서비스번호(한 회원이 복수의 번호 보유 가능)
	int trgtHabitServiceNo;
	//목표습관카테고리 번호:이름
	int trgtHabitCateNo;
	String trgtHabitCateName;
	//목표습관 시작일자, 초기화 일자
	LocalDate trgtHabitStartDate;
	LocalDate trgtHabitInitDate;
	//조회일자
	LocalDate viewDate;
	//한 목표습관의 성공일수(조회일자-시작일자의 차)
	int trgtHabitSuccDayCount;
}
