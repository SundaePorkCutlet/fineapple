package kr.or.fineapple.index;

import java.time.LocalDate;
import java.time.Period;
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

import kr.or.fineapple.domain.Badge;
import kr.or.fineapple.domain.Exer;
import kr.or.fineapple.domain.IntakeRecord;
import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.common.Search;
import kr.or.fineapple.domain.community.Board;
import kr.or.fineapple.service.community.CommunityService;
import kr.or.fineapple.service.diary.DiaryService;
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
	
	@Autowired
	@Qualifier("diaryServiceImpl")
	private DiaryService diaryService;
	
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
     
     int exerNo = list.size()-1;
     
     
     System.out.println("exerNo:  "+rand.nextInt(exerNo));
     
     exer = exerService.getExer(rand.nextInt(exerNo)+1);
     
     
    // exer.setExerNo(exerNo);
     
    System.out.println(exer);
    
    //���� ���� ���� ������ ��ǰ ����
    
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
   
     	////����/����� ��ü ���� ���� ���� ��� �ڵ� insert�Ǵ� �κ�
     	if(user != null && user.getPastUpdateStt() != true) {
     		//����� userId
     		String userId = user.getUserId();
     		
     		//����� ���� ����
     		LocalDate today = LocalDate.now();
     		
     		//����� ��ü ���� ���̺� ���� ������ ��¥ ��ȸ
     		LocalDate theLatestDateUserBodyInfo = diaryService.getTheLatestDateUserBodyInfo(userId);
     		//���� ������ ��¥�� ���� ������ �� ���
     		int howManyDays = Period.between(theLatestDateUserBodyInfo, today).getDays();
     		
     		//���� ������ ���� ��쿡�� ����
     		if(howManyDays >= 1) {
     			System.out.println(theLatestDateUserBodyInfo);
	     		//���� ���� ������Ʈ
	     		for(int i=howManyDays; i>0; i--) {
	     			diaryService.addUserBodyInfo(userId, i);
	     			System.out.println(i);
	     		}
     		}
     		
     		//���� ���̺� ���� ������ ��¥ ��ȸ
     		LocalDate theLatestDateBadge = diaryService.getTheLatestDateBadge(userId);
     		//���� ������ ��¥�� ���� ������ �� ���
     		howManyDays = Period.between(theLatestDateBadge, today).getDays();
     		
     		//���� ������ ���� ��쿡�� ����
     		if(howManyDays >= 1) {
     			System.out.println(theLatestDateBadge);
	     		Badge defaultBadge = new Badge();
	     		////������� ���� Ȱ�� ���� ��ȸ�Ͽ� ���ǿ� ���� ���� ���� ������Ʈ
	     		if(user.getDietServiceNo() != 0 && user.getExerServiceNo() != 0) {
	     			//�Ĵ�, � ���� ��� �̿�
	         		for(int i=howManyDays; i>0; i--) {
	         			defaultBadge.setUserId(userId);
	         			defaultBadge.setBadgeDate(LocalDate.now().minusDays(i-1));
	         			defaultBadge.setDailyBurnningKcal(0.0);
	         			defaultBadge.setDailyIntakeKcal(0.0);
	         			diaryService.addBadge(defaultBadge);
	         			System.out.println(defaultBadge);
	         		}
	     		} else if(user.getDietServiceNo() == 0 && user.getExerServiceNo() != 0) {
	     			//�Ĵ� ���� �̿�
	     			for(int i=howManyDays; i>0; i--) {
	         			defaultBadge.setUserId(userId);
	         			defaultBadge.setBadgeDate(LocalDate.now().minusDays(i-1));
	         			defaultBadge.setDailyIntakeKcal(0.0);
	         			diaryService.addBadge(defaultBadge);
	         			System.out.println(defaultBadge);
	         		}
	     		} else if(user.getDietServiceNo() != 0 && user.getExerServiceNo() == 0) {
					//� ���񽺸� �̿�
	     			for(int i=howManyDays; i>0; i--) {
	         			defaultBadge.setUserId(userId);
	         			defaultBadge.setBadgeDate(LocalDate.now().minusDays(i-1));
	         			defaultBadge.setDailyBurnningKcal(0.0);
	         			diaryService.addBadge(defaultBadge);
	         			System.out.println(defaultBadge);
	         		}
	     		} else {
					//�Ĵ�, � ��� �̿����� ����
	     			for(int i=howManyDays; i>0; i--) {
	         			defaultBadge.setUserId(userId);
	         			defaultBadge.setBadgeDate(LocalDate.now().minusDays(i-1));
	         			diaryService.addBadge(defaultBadge);
	         			System.out.println(defaultBadge);
	         		}
	     		}
     		}
     		user.setPastUpdateStt(true);
     		System.out.println(user);
     	}
     	
     	
    	return "index/index.html";
    }
    
}
