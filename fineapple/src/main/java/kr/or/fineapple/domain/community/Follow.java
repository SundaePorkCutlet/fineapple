package kr.or.fineapple.domain.community;

import kr.or.fineapple.domain.User;

public class Follow {
	private int followNo;
	private User toUser;
	private User fromUser;
	private int followStt;
	public int getFollowNo() {
		return followNo;
	}
	public void setFollowNo(int followNo) {
		this.followNo = followNo;
	}
	public User getToUser() {
		return toUser;
	}
	public void setToUser(User toUser) {
		this.toUser = toUser;
	}
	public User getFromUser() {
		return fromUser;
	}
	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}
	public int getFollowStt() {
		return followStt;
	}
	public void setFollowStt(int followStt) {
		this.followStt = followStt;
	}
	@Override
	public String toString() {
		return "Follow [followNo=" + followNo + ", toUser=" + toUser + ", fromUser=" + fromUser + ", followStt="
				+ followStt + "]";
	}
	
	
	
	
	

}
