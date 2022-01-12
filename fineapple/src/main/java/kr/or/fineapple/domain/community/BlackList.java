package kr.or.fineapple.domain.community;

import java.time.LocalDate;

import kr.or.fineapple.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BlackList {
	
	private String addBlackWhy;
	private LocalDate blackDate;
	private User blackUser;
	

}
