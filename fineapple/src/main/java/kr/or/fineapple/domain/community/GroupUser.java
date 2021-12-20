package kr.or.fineapple.domain.community;

import kr.or.fineapple.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GroupUser {
	
	private int groupUserNo;
	private User user;
	private int userStt;
	private Boolean userCaptainStt;

}
