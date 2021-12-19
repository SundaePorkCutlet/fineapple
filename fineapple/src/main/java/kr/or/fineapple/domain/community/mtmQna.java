package kr.or.fineapple.domain.community;

import java.time.LocalDate;

import kr.or.fineapple.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class mtmQna {
	private int mtmQnaNo;
	private int mtmCate;
	private User user;
	private LocalDate mtmQnaDate;
	private String mtmQnaTitle;
	private String mtmQnaCntnt;
	private LocalDate mtmAnswrCntntDate;
	private String mtmAnswrCntnt;
	private Boolean mtmAnswrStt;
}
