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
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getTrgtHabitServiceNo() {
		return trgtHabitServiceNo;
	}
	public void setTrgtHabitServiceNo(int trgtHabitServiceNo) {
		this.trgtHabitServiceNo = trgtHabitServiceNo;
	}
	public int getTrgtHabitCateNo() {
		return trgtHabitCateNo;
	}
	public void setTrgtHabitCateNo(int trgtHabitCateNo) {
		this.trgtHabitCateNo = trgtHabitCateNo;
	}
	public String getTrgtHabitCateName() {
		return trgtHabitCateName;
	}
	public void setTrgtHabitCateName(String trgtHabitCateName) {
		this.trgtHabitCateName = trgtHabitCateName;
	}
	public LocalDate getTrgtHabitStartDate() {
		return trgtHabitStartDate;
	}
	public void setTrgtHabitStartDate(LocalDate trgtHabitStartDate) {
		this.trgtHabitStartDate = trgtHabitStartDate;
	}
	public LocalDate getTrgtHabitInitDate() {
		return trgtHabitInitDate;
	}
	public void setTrgtHabitInitDate(LocalDate trgtHabitInitDate) {
		this.trgtHabitInitDate = trgtHabitInitDate;
	}
	public LocalDate getViewDate() {
		return viewDate;
	}
	public void setViewDate(LocalDate viewDate) {
		this.viewDate = viewDate;
	}
	public int getTrgtHabitSuccDayCount() {
		return trgtHabitSuccDayCount;
	}
	public void setTrgtHabitSuccDayCount(int trgtHabitSuccDayCount) {
		this.trgtHabitSuccDayCount = trgtHabitSuccDayCount;
	}
	
	
	
}
