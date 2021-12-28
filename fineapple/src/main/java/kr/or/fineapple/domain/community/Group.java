package kr.or.fineapple.domain.community;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Group {
	private int groupNo;
	private String groupName;
	private String groupIntro;
	private int userCount;
	private LocalDate groupBirth;	

}
