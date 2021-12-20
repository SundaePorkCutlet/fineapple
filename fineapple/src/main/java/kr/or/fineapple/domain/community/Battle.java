package kr.or.fineapple.domain.community;

import java.time.LocalDate;

import kr.or.fineapple.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Battle {
	private int battleNo;
	private int battleResultCate;
	private int battleItemCate;
	private LocalDate battleStartDate;
	private LocalDate battleEndDate;
	private int battlePeriod;
	private int userScore;
	private int rivalUserScore;
	private Boolean battleStt;
	private int userTrgtKcal;
	private int rivalTrgtKcal;
	private User user;
	private User rivalUser;
	

}
