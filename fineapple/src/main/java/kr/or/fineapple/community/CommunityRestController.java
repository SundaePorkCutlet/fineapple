package kr.or.fineapple.community;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.community.Board;
import kr.or.fineapple.domain.community.Cmnt;
import kr.or.fineapple.service.community.CommunityService;



@RestController
@RequestMapping("/community/json/*")
public class CommunityRestController {
	
	
	@Autowired
	@Qualifier("communityServiceImpl")
	private CommunityService communityService;
	
	@RequestMapping(value = "updatePostLike", method = RequestMethod.POST)
	public Board updatePostLike(@RequestBody Board board, HttpServletRequest request) {
		

		board.setUser((User)request.getSession(true).getAttribute("user")); 
		System.out.println("=======================================================");
		System.out.println(board);
		
		board = communityService.updatePostLike(board);
		
		System.out.println("=======================================================");
		System.out.println(board);
		
		return board;
	}
	
	@RequestMapping(value = "addCmnt", method = RequestMethod.POST)
	public Map addCmnt(@RequestBody String postNoStr, HttpServletRequest request, Model model) throws JsonMappingException, JsonProcessingException {
		
		
		JSONObject jsonObject = (JSONObject)JSONValue.parse(postNoStr);
		
		Board board = new Board();
		board.setPostNo(Integer.parseInt(jsonObject.get("postNo").toString()));
		
		Cmnt cmnt = new Cmnt();
		
		cmnt.setCmntContent(jsonObject.get("cmntContent").toString());
		
		
		cmnt.setBoard(board);
		
		cmnt.setUser((User)request.getSession(true).getAttribute("user"));
		System.out.println("===============================");
		System.out.println(cmnt);
		
		Map map = communityService.addCmnt(cmnt);
		
		System.out.println("==============================");
		
		return map;
	}
	
	@PostMapping(value = "addCmnts")
	public Cmnt addCmnts(@RequestBody String postNoStr, HttpServletRequest request){
		
		
		JSONObject jsonObject = (JSONObject)JSONValue.parse(postNoStr);
		
		Board board = new Board();
		board.setPostNo(Integer.parseInt(jsonObject.get("postNo").toString()));
		
		Cmnt cmnt = new Cmnt();
		
		cmnt.setCmntContent(jsonObject.get("cmntContent").toString());
		
		
		cmnt.setBoard(board);
		cmnt.setUser((User)request.getSession(true).getAttribute("user"));
		System.out.println("===============================");
		System.out.println(cmnt);
		
		
		
		return  null;
	}
	
	
	@PostMapping(value = "addAlarm")
	public void addAlarm() {
		
	}
	
	
	
	
	
}
