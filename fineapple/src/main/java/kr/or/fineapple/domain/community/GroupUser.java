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
	private Group group;
	private User user;
	private int groupStt;
	private int captainStt;

}
