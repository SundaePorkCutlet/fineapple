package kr.or.fineapple.domain.community;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class group {
	private int groupNo;
	private String groupName;
	private String groupIntro;
	private int userCount;

}
