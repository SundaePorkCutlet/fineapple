package kr.or.fineapple.domain.community;

import java.time.LocalDate;

import kr.or.fineapple.domain.User;

public class Cmnt {
	
	private int cmntNo;
	private User user;
	private Board board;	
	private String cmntContent;
	private LocalDate cmntDate;
	private int cmntLikeCount;	
	private int updateStt;
	
	public Cmnt() {
		System.out.println(getClass().getName()+  "»ý¼ºÇÔ");
	}
	
	
	public int getCmntNo() {
		return cmntNo;
	}
	public void setCmntNo(int cmntNo) {
		this.cmntNo = cmntNo;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getCmntContent() {
		return cmntContent;
	}
	public void setCmntContent(String cmntContent) {
		this.cmntContent = cmntContent;
	}
	public LocalDate getCmntDate() {
		return cmntDate;
	}
	public void setCmntDate(LocalDate cmntDate) {
		this.cmntDate = cmntDate;
	}
	public int getCmntLikeCount() {
		return cmntLikeCount;
	}
	public void setCmntLikeCount(int cmntLikeCount) {
		this.cmntLikeCount = cmntLikeCount;
	}
	public int getUpdateStt() {
		return updateStt;
	}
	public void setUpdateStt(int updateStt) {
		this.updateStt = updateStt;
	}
	
	@Override
	public String toString() {
		return "Cmnt [cmntNo=" + cmntNo + ", board=" + board + ", user=" + user + ", cmntContent=" + cmntContent
				+ ", cmntDate=" + cmntDate + ", cmntLikeCount=" + cmntLikeCount + ", updateStt=" + updateStt + "]";
	}
	
	
	
	

	

}
