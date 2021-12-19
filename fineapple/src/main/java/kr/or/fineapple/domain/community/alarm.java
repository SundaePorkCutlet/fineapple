package kr.or.fineapple.domain.community;

import java.time.LocalDate;

import kr.or.fineapple.domain.User;

public class alarm {
	private int alarmNo;
	private User user;
	private User otherUser;
	private String alarmTitle;
	private String alarmCntnt;
	private LocalDate alarmTime;
	public int getAlarmNo() {
		return alarmNo;
	}
	public void setAlarmNo(int alarmNo) {
		this.alarmNo = alarmNo;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getOtherUser() {
		return otherUser;
	}
	public void setOtherUser(User otherUser) {
		this.otherUser = otherUser;
	}
	public String getAlarmTitle() {
		return alarmTitle;
	}
	public void setAlarmTitle(String alarmTitle) {
		this.alarmTitle = alarmTitle;
	}
	public String getAlarmCntnt() {
		return alarmCntnt;
	}
	public void setAlarmCntnt(String alarmCntnt) {
		this.alarmCntnt = alarmCntnt;
	}
	public LocalDate getAlarmTime() {
		return alarmTime;
	}
	public void setAlarmTime(LocalDate alarmTime) {
		this.alarmTime = alarmTime;
	}
	@Override
	public String toString() {
		return "alarm [alarmNo=" + alarmNo + ", user=" + user + ", otherUser=" + otherUser + ", alarmTitle="
				+ alarmTitle + ", alarmCntnt=" + alarmCntnt + ", alarmTime=" + alarmTime + "]";
	}
	
	

}
