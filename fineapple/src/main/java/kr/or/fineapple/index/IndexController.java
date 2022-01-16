package kr.or.fineapple.index;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.or.fineapple.domain.Exer;
import kr.or.fineapple.domain.IntakeRecord;
import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.common.Search;
import kr.or.fineapple.domain.community.Board;
import kr.or.fineapple.service.community.CommunityService;
import kr.or.fineapple.service.diet.DietService;
import kr.or.fineapple.service.exer.ExerService;
import kr.or.fineapple.service.user.UserService;

@Controller
public class IndexController {

	
	@Autowired
	@Qualifier("ExerServiceImpl")
	private ExerService exerService;
	
	@Autowired
	@Qualifier("dietServiceImpl")
	private DietService dietService;

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired
	@Qualifier("communityServiceImpl")
	private CommunityService communityService;
	
    @GetMapping("/")
    public String index(Model model,HttpSession session) throws Exception{
     System.out.println("index");
     
     
     
     Exer exer = new Exer();
     Random rand = new Random();
     Search search = new Search();
     
     search.setSearchCondition(0);
     search.setSearchKeyword("");
     
     
     Map<String, Object> map = exerService.getExerList(search);
	 
     List list  = new ArrayList();
     
     list = (List) map.get("list");
     
     int exerNo = list.size()+1;
     
     
     System.out.println("exerNo:  "+rand.nextInt(exerNo));
     
     exer = exerService.getExer(rand.nextInt(exerNo));
     
     
    // exer.setExerNo(exerNo);
     
    System.out.println(exer);
    
    //내가 가장 많이 섭취한 식품 시작
    
    User user = (User)session.getAttribute("user");
    
    List<IntakeRecord> list2 = new ArrayList<IntakeRecord>();
    if(user!=null) {
    list2 = dietService.FavIntake(user.getUserId());
    }
    System.out.println(list2);
    
    List<Board> list3 = new ArrayList<Board>();

    list3= communityService.getPostList();

    List<Board> list4 = new ArrayList<Board>();
    if(list3.size()>3) {
    list4 = list3.subList(0, 3);
    }else {
    	
    	list4 = list3;
    }
     model.addAttribute("exer", exer);
     model.addAttribute("list2",list2);
     model.addAttribute("list3",list4 );
     model.addAttribute("NavName1","Home");
     model.addAttribute("NavName2","Main");
     
     
     
     
    	return "index/index.html";
    }
    
    
    

}
