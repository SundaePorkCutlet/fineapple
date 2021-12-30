package kr.or.fineapple.domain.community;

import java.time.LocalDate;

import kr.or.fineapple.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Cmnt {
	
	private int cmntNo;
	private User user;
	private Board board;	
	private String cmntContent;
	private LocalDate cmntDate;
	private int cmntLikeCount;	
	private int updateStt;
	private int cmntLikeStt;
	
	public Cmnt() {
		System.out.println(getClass().getName()+  "»ý¼ºÇÔ");
	}
	
	
	
	
	
	

	

}
