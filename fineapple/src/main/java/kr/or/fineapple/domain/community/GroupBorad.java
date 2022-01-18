package kr.or.fineapple.domain.community;

import java.time.LocalDate;
import java.util.List;

import kr.or.fineapple.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GroupBorad {
	
	
	private int postNo;
	private User user;
	private Group group;
	private String content;
	private LocalDate postDate;
	private int cmntCount;
	private int postLikeCount;
	private int postLikeStt;
	private int updateStt;
	private List<Cmnt> cmnt;
	private List<Img> img;
	
	

}
