package kr.or.fineapple.domain.community;

import java.time.LocalDate;

import kr.or.fineapple.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class Report {
	private int reportNo;
	private User user;
	private User reportedUser;
	private String reportCate;
	private Object reportTrgt;
	private String reportCntnt;
	private int reportStt;
	private LocalDate reportDate;
	

}
