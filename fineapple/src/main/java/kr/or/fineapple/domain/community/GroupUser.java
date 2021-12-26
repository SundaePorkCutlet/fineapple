package kr.or.fineapple.domain.community;

import kr.or.fineapple.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GroupUser {
	
	private Group group;
	private int groupUserNo;
	private User user;
	private int userStt;
	private int userCaptainStt;

}
