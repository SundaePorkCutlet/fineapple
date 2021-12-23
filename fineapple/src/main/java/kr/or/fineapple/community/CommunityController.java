package kr.or.fineapple.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.fineapple.domain.community.Board;
import kr.or.fineapple.service.community.CommunityService;
import oracle.net.aso.c;

@Controller
@RequestMapping("/community/*")
public class CommunityController {
	
	@Autowired
	@Qualifier("communityServiceImpl")
	private CommunityService communityService;
	
	
	
	
	
	@GetMapping(value = "getViewTest")
	public String getViewTest() {
		System.out.println("getViewTest 컨트롤러 거침");
		return "community/getPost.html";
	}
	
	
	@PostMapping(value = "addPost")
	public String addPost(@ModelAttribute Board board) {
		System.out.println(board);
		communityService.addPost(board);
		return null;
	}
	
	
	
}

