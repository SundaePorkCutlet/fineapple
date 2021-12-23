package kr.or.fineapple.community;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.fineapple.domain.community.Board;
import kr.or.fineapple.service.community.CommunityService;
import oracle.net.aso.c;

@Controller
@RequestMapping("/community/*")
public class CommunityController {
	
	@Autowired
	@Qualifier("communityServiceImpl")
	private CommunityService communityService;
	
	@GetMapping(value = "getPost")
	public String getViewTest(@ModelAttribute Board board, Model model) {
		
		System.out.println(board.getPostNo() + "dddddddddddddddddddddddddd");
		
		board =  communityService.getPost(board);
		
		System.out.println(board + "getPost Service °ÅÄ£ ÈÄ");
		
		model.addAttribute("board", board);
		
		return "community/getPost.html";
	}
	
	@RequestMapping(value = "getViewTest", method = RequestMethod.GET)
	public String getPostList(Model model) {
		
		
		List<Board> list = communityService.getPostList();
		
		model.addAttribute("list", list);
		
		return "community/Borad.html";
		
	}
	

	@PostMapping(value = "addPost")
	public String addPost(@ModelAttribute Board board) {
		System.out.println(board);
		communityService.addPost(board);
		return null;
	}
	
	
	
}

