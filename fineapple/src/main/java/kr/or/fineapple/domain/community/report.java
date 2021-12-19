package kr.or.fineapple.domain.community;

import java.time.LocalDate;

import kr.or.fineapple.domain.User;

public class report {
	private int reportNo;
	private User user;
	private User reportedUser;
	private int reportCate;
	private Object reportTrgt;
	private String reportCntnt;
	private Boolean reportStt;
	private LocalDate reportDate;
	public int getReportNo() {
		return reportNo;
	}
	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getReportedUser() {
		return reportedUser;
	}
	public void setReportedUser(User reportedUser) {
		this.reportedUser = reportedUser;
	}
	public int getReportCate() {
		return reportCate;
	}
	public void setReportCate(int reportCate) {
		this.reportCate = reportCate;
	}
	public Object getReportTrgt() {
		return reportTrgt;
	}
	public void setReportTrgt(Object reportTrgt) {
		this.reportTrgt = reportTrgt;
	}
	public String getReportCntnt() {
		return reportCntnt;
	}
	public void setReportCntnt(String reportCntnt) {
		this.reportCntnt = reportCntnt;
	}
	public Boolean getReportStt() {
		return reportStt;
	}
	public void setReportStt(Boolean reportStt) {
		this.reportStt = reportStt;
	}
	public LocalDate getReportDate() {
		return reportDate;
	}
	public void setReportDate(LocalDate reportDate) {
		this.reportDate = reportDate;
	}
	@Override
	public String toString() {
		return "report [reportNo=" + reportNo + ", user=" + user + ", reportedUser=" + reportedUser + ", reportCate="
				+ reportCate + ", reportTrgt=" + reportTrgt + ", reportCntnt=" + reportCntnt + ", reportStt="
				+ reportStt + ", reportDate=" + reportDate + "]";
	}
	

}
