package kr.or.fineapple.community;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/community/json/*")
public class CommunityRestController {
	
	@RequestMapping(value = "updatePostLike", method = RequestMethod.POST)
	public String updatePostLike() {
		
		return null;
	}
	
	
}
