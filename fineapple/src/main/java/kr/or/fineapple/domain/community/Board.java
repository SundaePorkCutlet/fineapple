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
public class Board {
	private int postNo;
	private User user;
	private String title;
	private String content;
	private LocalDate postDate;
	private int viewCount;
	private int cmntCount;
	private int postLikeCount;
	private int updateStt;
	private int cateName;
	private int groupNo;
	private List<Img> img;
	
	
	public Board() {
		System.out.println("Board 도메인 객체 생성");
	}
	
}
