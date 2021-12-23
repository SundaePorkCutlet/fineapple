package kr.or.fineapple.CommunityTest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.community.Board;
import kr.or.fineapple.domain.community.Cmnt;
import kr.or.fineapple.service.community.CommunityService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class BoardTest {
	
	@Autowired
	@Qualifier("communityServiceImpl")
	private CommunityService communityService;
	
	
	//@Test
	public void Boardtest() {
		
		
	}
	
	//@Test
	public void addPost() {
		
		Board board = new Board();
		User user = new User();
		/*
		 * user.setUserId("aaa123@naver.com"); user.setUserName("È«±æµ¿");
		 * board.setUser(user); board.setTitle("¾È³çÇÏ¼¼¿ä"); board.setContent("¹Ý°©½À´Ï´Ù.");
		 * board.setPostDate(LocalDate.now()); board.setCateName(2);
		 */

		communityService.addPost(board);
		
	//	int postNo = board.getPostNo();
		
	//	System.out.println(postNo +"aaaaaaaaaa");
		
	}
	
	@Test
	public void getPostList() {
		
		List<Board> list = communityService.getPostList();
		
		for (Board board : list) {
			System.out.println(board);
		}
		
	}
	
	
	
	//@Test
	public void updatePostviewCount() {
		
		Board board = new Board();
		
		board.setPostNo(4);
		
		communityService.updatePostViewCount(board);

	}
	
	//@Test
	public void updatePostLike() {
		
		Board board = new Board();
		
		board.setPostNo(4);
		
	}
	
	//@Test
	public void updateCmntLike() {
		
		Cmnt cmnt = new Cmnt();

	}
	
	
	
	
	
	
	
	
	
	
	
	

}
