package kr.or.fineapple.community;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.community.Board;
import kr.or.fineapple.domain.community.Cmnt;
import kr.or.fineapple.service.community.CommunityService;
import oracle.net.aso.b;


@RestController
@RequestMapping("/community/json/*")
public class CommunityRestController {
	
	
	@Autowired
	@Qualifier("communityServiceImpl")
	private CommunityService communityService;
	
	@RequestMapping(value = "updatePostLike", method = RequestMethod.POST)
	public Board updatePostLike(@RequestBody Board board) {
		
		User user = new User();
		user.setUserId("bbb123@gmil.com");
		board.setUser(user); 
		System.out.println("=======================================================");
		System.out.println(board);
		
		board = communityService.updatePostLike(board);
		
		System.out.println("=======================================================");
		System.out.println(board);
		
		return board;
	}
	
	@RequestMapping(value = "addCmnt", method = RequestMethod.POST)
	public Cmnt addCmnt(@RequestBody Cmnt cmnt, @RequestParam("postNo") String postNoStr) {
		
		Board board = new Board();
		board.setPostNo(Integer.parseInt(postNoStr));
		cmnt.setBoard(board);
		User user = new User();
		user.setUserId("bbb123@gmil.com");
		cmnt.setUser(user);
		System.out.println("===============================");
		System.out.println(cmnt);
		
		communityService.addCmnt(cmnt);
		System.out.println("==============================");
		System.out.println(cmnt);
		return cmnt;
	}
	
	
}
