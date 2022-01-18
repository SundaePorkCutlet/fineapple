package kr.or.fineapple.exer;

import java.io.File;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.fineapple.domain.BurnningRecord;
import kr.or.fineapple.domain.Exer;
import kr.or.fineapple.domain.ExerServ;
import kr.or.fineapple.domain.Routine;
import kr.or.fineapple.domain.TotalRecord;
import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.UserServ;
import kr.or.fineapple.domain.common.Search;
import kr.or.fineapple.service.diary.DiaryService;
import kr.or.fineapple.service.exer.ExerService;
import kr.or.fineapple.service.user.UserService;


@Controller
@RequestMapping("/exer/*")
public class ExerController {
	
	@Autowired
	@Qualifier("ExerServiceImpl")
	private ExerService exerService;

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired
	@Qualifier("diaryServiceImpl")
	private DiaryService diaryService;
	
	
	public ExerController() {
	}


	
	/*
	 * @GetMapping("addUserService") public String addUserService(HttpServletRequest
	 * request,Model model) throws Exception {
	 * 
	 * System.out.println("get: addUserService");
	 * 
	 * User user =(User)request.getSession(true).getAttribute("user"); user =
	 * userService.getUser(user.getUserId()); System.out.println(user);
	 * 
	 * ExerServ serv = exerService.getUserService(user.getUserId());
	 * 
	 * System.out.println(serv);
	 * 
	 * if (serv != null) { if(serv.getUserServiceNo()!=0) {
	 * 
	 * model.addAttribute("user",user); model.addAttribute("exerServ",serv);
	 * model.addAttribute("NavName1","�����");
	 * model.addAttribute("NavName2","� ���� Ȱ��ȭ");
	 * 
	 * return "exer/getUserService.html";
	 * 
	 * }else { model.addAttribute("user",user);
	 * model.addAttribute("NavName1","�����");
	 * model.addAttribute("NavName2","� ���� Ȱ��ȭ");
	 * 
	 * return "exer/addUserService.html"; } }
	 * 
	 * model.addAttribute("user",user); model.addAttribute("NavName1","�����");
	 * model.addAttribute("NavName2","� ���� Ȱ��ȭ");
	 * 
	 * return "exer/addUserService.html"; }
	 */


	@GetMapping("addUserService")
	public String addUserService(HttpServletRequest request,Model model) throws Exception  {
		
	    System.out.println("get: addUserService");


		if((User)request.getSession(true).getAttribute("user")!=null) {
			
			User user =(User)request.getSession(true).getAttribute("user");
			
			System.out.println(user);
		
			ExerServ serv = exerService.getUserService(user.getUserId());
			
			System.out.println(serv);
			
			if(serv!=null) {
					if(serv.getUserServiceNo()!=0) {
			
						model.addAttribute("user",user);
						model.addAttribute("exerServ",serv);
						model.addAttribute("NavName1","�����");
						model.addAttribute("NavName2","� ���� Ȱ��ȭ");
						
						return "exer/getUserService.html";
						
						}else {
							
							model.addAttribute("NavName1","�����");
							model.addAttribute("NavName2","� ���� Ȱ��ȭ");
							
							return "exer/addUserService.html";
							
							}
					
			}else {
				model.addAttribute("user",user);
				model.addAttribute("NavName1","�����");
				model.addAttribute("NavName2","� ���� Ȱ��ȭ");
				
				
				return "exer/addUserService.html";}
			
		}else {
			
			return "user/login";
			
		}
		
	}
    
@PostMapping("addUserService")
public String addUserService(@ModelAttribute("ExerServ")ExerServ serv,
							HttpServletRequest request,
							Model model,HttpSession session) throws Exception{
	

	System.out.println("post:addUserService");
	
	System.out.println(serv);
	
	
	User user =(User)request.getSession(true).getAttribute("user");
	String userId = user.getUserId();
	serv.setUserId(userId);
	
	
	if(exerService.getUserService(userId)==null) {
		
		exerService.addUserService(serv);

		
	 }else {
		 ExerServ serv11 = exerService.getUserService(userId);
		 serv11.setBodyMuscle(serv.getBodyMuscle());
		 serv11.setDailyTrgtBurnningKcal(serv.getDailyTrgtBurnningKcal());
		 serv11.setTrgtBodyMuscle(serv.getTrgtBodyMuscle());
		 
		 exerService.updateUserService(serv11);
	 }
	
	


	serv = exerService.getUserService(userId);
	
	
	System.out.println(serv.getUserServiceNo());
	serv.setExerServiceNo(serv.getUserServiceNo());
	

	exerService.updateExerServiceNo(serv);
	
	model.addAttribute("exerServ",serv);
	model.addAttribute("user",user);
	
	
	System.out.println("��� id: " + userId);
	
	
	user = userService.getUser(userId);
	
	
	System.out.println("session ��� ����" + user);
	
	
	session.setAttribute("user",user);


	return "exer/getUserService.html";

}


@GetMapping("getUserService")
public String getUserService(Model model, HttpServletRequest request) {
	
	model.addAttribute("NavName1","�����");
	model.addAttribute("NavName2","� ���� Ȱ��ȭ ��ȸ");
	
	return "exer/getUserService.html";
}




@GetMapping("updateUserService")
public String getUpdateUserService(Model model, HttpServletRequest request) throws Exception {
	
	
	System.out.println("get:updateUserService");
	
	User user =(User)request.getSession(true).getAttribute("user");
	System.out.println(user);

	ExerServ serv = exerService.getUserService(user.getUserId());
	
	
	
	model.addAttribute("user",user);
	model.addAttribute("exerServ",serv);
	model.addAttribute("NavName1","�����");
	model.addAttribute("NavName2","� ���� Ȱ��ȭ ����");
	
	
	return "exer/updateUserService.html";
		
	
}
	


@PostMapping("updateUserService")
public String postUpdateUserService(@ModelAttribute("ExerServ")ExerServ serv, Model model, HttpServletRequest request) throws Exception {
	
	
	 System.out.println("post:updateUserService");
	 ExerServ serv11 = new ExerServ();
	
	 System.out.println(serv);
     User user =(User)request.getSession(true).getAttribute("user");
     String userId = user.getUserId();
 	 serv11.setUserId(userId);
 	 
	if(exerService.getUserService(userId)==null) {
		
		exerService.addUserService(serv);
		
	 }else {

		 System.out.println("111111111111111" );
		 	
	     serv11.setExerServiceNo(user.getDietServiceNo());
		 System.out.println(user.getDietServiceNo());
		 serv11.setBodyMuscle(serv.getBodyMuscle());
		 serv11.setDailyTrgtBurnningKcal(serv.getDailyTrgtBurnningKcal());
		 serv11.setTrgtBodyMuscle(serv.getTrgtBodyMuscle());
		 System.out.println(serv11);
		 
		 exerService.updateUserService(serv11);
	 }
	
 
 	 exerService.updateExerServiceNo(serv11);
 	
 	 System.out.println("������Ʈ �� ���� ����Ȱ��ȭ ����:  "+serv11);

 	 
		/*
		 * user = userService.getUser(userId);
		 * 
		 * exerService.updateExerServiceNo(serv);
		 */
		 
		
	model.addAttribute("exerServ",serv);	
	model.addAttribute("user",user);
	model.addAttribute("NavName1","�����");
	model.addAttribute("NavName2","� ���� Ȱ��ȭ ��ȸ");
	
	
	return "redirect:../exer/addUserService";
		
	
}





@RequestMapping("getExerList")
public String getExerList( @ModelAttribute("search") Search search, Model model) throws Exception {
	
	  System.out.println("getExerList");
 
	    search.setPageSize(30);
		//search.setSearchCondition(0);
		

		System.out.println(search);
		int page = 1; 
		if(search.getCurrentPage()>0) {
			page = search.getCurrentPage();
		};
		search.setStartNum(1);
		search.setEndNum(14);
			  
		/*
		 * int pageSize = 3; int pageUnit = 5;
		 * 
		 * if(search.getCurrentPage() ==0 ){ search.setCurrentPage(1); }
		 * 
		 * search.setPageSize(pageSize);
		 */
	
		
	  Map<String, Object> map = exerService.getExerList(search);
	  
	  
		/*
		 * Page resultPage = new Page( search.getCurrentPage(),
		 * ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		 * 
		 * 
		 * System.out.println(resultPage);
		 */  
	  	
	  
	    model.addAttribute("list", map.get("list"));
		model.addAttribute("search", search);
		model.addAttribute("NavName1","�����");
		model.addAttribute("NavName2","�����Ʈ");
		
		
		
	return "exer/getExerList.html";	
	
}



@GetMapping("getExer")
public String getExer(@ModelAttribute("exer") Exer exer, Model model) throws Exception{
	
	
	exer = exerService.getExer(exer.getExerNo());
	
	System.out.println("getExer");
	
	if(exer.getExerVideoName() == null) {
		exer.setExerVideoName("null");
		
		System.out.println("exerVideoName�� nullüũ" + exer);
		
		model.addAttribute("exer", exer);
		
	}else {
	
	System.out.println("exerVideoName�� nullüũ" + exer);
		
	model.addAttribute("exer", exer);
	model.addAttribute("NavName1","�����");
	model.addAttribute("NavName2","� �� ����");
	
	}
	
	return "exer/getExer.html";
	
	
}



@GetMapping("getUpdateExer")
public String getUpdateExer(@ModelAttribute("exer") Exer exer, Model model) throws Exception {
	
	
	exer= exerService.getExer(exer.getExerNo());
	
	model.addAttribute("exer", exer);
	model.addAttribute("NavName1","�����");
	model.addAttribute("NavName2","� �� ���� ����");
	
	
	System.out.println("getUpdateExer");
	
	
	return "exer/updateExer.html";
	
}


private static String FILE_SERVER_PATH = "C:\\Users\\82105\\git\\fineapple\\fineapple\\src\\main\\resources\\templates\\assets\\video";


@PostMapping("postUpdateExer")
public String postUpdateExer(@ModelAttribute("exer") Exer exer , Model model, @RequestParam("exerFile") MultipartFile exerFile ) throws Exception {
	
	if(!exerFile.getOriginalFilename().isEmpty()) {
		
		exerFile.transferTo(new File(FILE_SERVER_PATH, exerFile.getOriginalFilename()));
		
		model.addAttribute("msg", "File uploaded successfully");
		
		System.out.println("����� �������ε� ����~");
		
	}else {
		
		model.addAttribute("msg", "Please select a valid mediaFile..");
		
	}
	
	exer.setExerVideoName(exerFile.getOriginalFilename());
	
	System.out.println("postUpdateExer");
	
	exerService.postUpdateExer(exer);
	

	model.addAttribute("exer", exer);
	model.addAttribute("NavName1","�����");
	model.addAttribute("NavName2","� �� ����");
	
	return "exer/getExer";
	
}

@RequestMapping("deleteExer")
public String deleteExer(@ModelAttribute("exer") Exer exer , Model model) throws Exception {
	
	System.out.println("deleteExer");
	
	exerService.deleteExer(exer.getExerNo());
	
	Search search = new Search();
	
	search.setCurrentPage(1);
	search.setPageSize(1);
	search.setSearchCondition(0);
	search.setSearchKeyword("");
	
	  Map<String, Object> map = exerService.getExerList(search);
	  
	  
	    model.addAttribute("list", map.get("list"));
		model.addAttribute("search", search);
		model.addAttribute("NavName1","�����");
		model.addAttribute("NavName2","�����Ʈ");
		model.addAttribute("exer", exer);
	
	return "exer/getExerList";
}


@PostMapping("postAddExer")
public String postAddExer(@ModelAttribute("exer") Exer exer , Model model, @RequestParam("exerFile") MultipartFile exerFile ) throws Exception {

	
	if(!exerFile.getOriginalFilename().isEmpty()) {
		
		exerFile.transferTo(new File(FILE_SERVER_PATH, exerFile.getOriginalFilename()));
		
		model.addAttribute("msg", "File uploaded successfully");
		
		System.out.println("����� ��Ͼ��ε� ����~");
		
	}else {
		
		model.addAttribute("msg", "Please select a valid mediaFile..");
		
	}
	
	exer.setExerVideoName(exerFile.getOriginalFilename());
	
	exerService.addExer(exer);
	
	System.out.println("addExer");
	
	Search search = new Search();
	
	search.setCurrentPage(1);
	search.setPageSize(1);
	search.setSearchCondition(0);
	search.setSearchKeyword("");
	
	  Map<String, Object> map = exerService.getExerList(search);
	  
	  
	    model.addAttribute("list", map.get("list"));
		model.addAttribute("search", search);
		model.addAttribute("NavName1","�����");
		model.addAttribute("NavName2","� ����Ʈ");
		
	
	model.addAttribute("exer", exer);
	
	//����Ʈ�� �ٷ� �ȶߴ� ������ �����, Ű���� �������� �������� �ʱ� ������ �ٽ� ���� ���༭ ������!!!
	return "redirect:../exer/getExerList";
}


@GetMapping("getAddExer")
public String getAddExer(@ModelAttribute("exer") Exer exer, Model model) throws Exception {
	
	
	System.out.println("getAddExer");
	
	model.addAttribute("NavName1","�����");
	model.addAttribute("NavName2","���ο� � ���");
	
	
	return "exer/addExer.html";	
	
}





@RequestMapping(value="searchExerPlace")
public String searchExerPlace(Model model) {
	
	
	System.out.println("searchExerPlace");

	
	model.addAttribute("NavName1","�����");
	model.addAttribute("NavName2","� ��� ã��");
	
	
	return "exer/searchExerPlace.html";
	
	
}



@RequestMapping(value="exerIndex")
public String timer(Model model,HttpServletRequest request) {
	
	System.out.println("timer");
	
	
	User user =(User)request.getSession(true).getAttribute("user");
 
	
	model.addAttribute("user", user);
	
	
	return "exer/exerIndex.html";
	
	
}



@GetMapping("getRoutineList")
public String getRoutineList(Model model,HttpServletRequest request) throws Exception {
	
	
	System.out.println("getRoutineList");
	
    
	User user =(User)request.getSession(true).getAttribute("user");

	ExerServ serv = exerService.getUserService(user.getUserId());
	
	System.out.println(serv);

	Map<String,Object> map = new HashMap<String,Object>();
	
	
	map = exerService.getRoutineList(serv.getUserServiceNo());
	
	System.out.println(map);
	
	model.addAttribute("list", map.get("list"));
	model.addAttribute("NavName1","�����");
	model.addAttribute("NavName2","� ��ƾ ����Ʈ");
	
	
	return "exer/getRoutineList :: getRoutineList";

}



@GetMapping("getRoutine")
public String getRoutineInfoList(@ModelAttribute("routine") Routine routine, Model model
								, @RequestParam("routineNo") String routineNo) throws Exception {

	
	routine = exerService.getRoutine(Integer.parseInt(routineNo));
	
	
	System.out.println("getRoutine");
	System.out.println("��ƾ��ȣ    " + routineNo);
	
	Map<String,Object> map = new HashMap<String,Object>();
	
	
	map = exerService.getRoutineInfoList(Integer.parseInt(routineNo));
		
	
	
	model.addAttribute("list", map.get("list"));
	model.addAttribute("routine", routine);
	model.addAttribute("routineNo", routineNo);
	model.addAttribute("NavName1","�����");
	model.addAttribute("NavName2","��ƾ �� ����");
	
	
	
	return "exer/getRoutine:: getRoutine";
	

}



@GetMapping("addDailyBurnning")
public String getAddDailyBurnning(Model model, @RequestParam("addDailyBurnning")int exerNo,HttpServletRequest request) throws Exception {

	System.out.println("getAddDailyBurnning");
	
	Exer exer = new Exer();

	exer = exerService.getExer(exerNo);


	System.out.println(exer);
  
	/*
	 * Map<String,Object> map = new HashMap<String,Object>();
	 * 
	 * map = exerService.getRoutineList(serv.getUserServiceNo());
	 * 
	 * System.out.println(map);
	 * 
	 * model.addAttribute("list",map.get("list"));
	 */
  model.addAttribute("exer", exer);
  model.addAttribute("NavName1","�����");
  model.addAttribute("NavName2","���� ��� ���� �Է�");

return "exer/addDailyBurnning :: addDailyBurnning";


}



@PostMapping("addDailyBurnning")
public String postAddDailyBurnning(Model model, @RequestParam("exerNo")int exerNo,
		@RequestParam("anExerKcal")Double anExerKcal ,
		@RequestParam("exerLv")String exerLv,
		@RequestParam("anExerTime")String anExerTime,
		HttpServletRequest request) throws Exception {

	
System.out.println("postAddDailyBurnning");


System.out.println("���Ӹ�~~~" + exerNo + anExerKcal + exerLv + anExerTime);


BurnningRecord record = new BurnningRecord();
Exer exer = new Exer();

exer = exerService.getExer(exerNo);


/*
 * int hour;// 60
 * 
 * if( record.getDailyExerTime().split(":")[0] == null){
 * 
 * hour = 0; }else {
 * 
 * hour = Integer.parseInt(record.getDailyExerTime().split(":")[0]) * 60;
 * 
 * } int min = Integer.parseInt(record.getDailyExerTime().split(":")[1]) + hour;
 * // 90
 * 
 * 
 * System.out.println(hour); // 60 System.out.println(min); // 90
 * 
 * System.out.println("��������������" + min); //01:30:00
 */
//int routineTime = min + Integer.parseInt(anExerTime); // 150


int routineTime = Integer.parseInt(anExerTime); // 150


System.out.println(routineTime);



String resultTime1;
String resultTime2;


int a = Integer.parseInt(anExerTime) / 60;
int b =Integer.parseInt(anExerTime) % 60;

System.out.println(a);
System.out.println(b);


if(b == 0) {
	
	resultTime1 = a + ":" + b + "0" + ":" + "00";
	
} else {

	resultTime1 = a + ":" + b + ":" + "00";

}


System.out.println(	resultTime1);


int c = (routineTime) / 60;

int d = (routineTime) % 60;

System.out.println(c);
System.out.println(d);

if(d == 0) {
	
	 resultTime2 = c + ":" + d +"0"+ ":" + "00";
	
}

 resultTime2 = c + ":" + d + ":" + "00";

 
System.out.println(resultTime2);




User user =(User)request.getSession(true).getAttribute("user");


System.out.println(user);

List<BurnningRecord> list = new ArrayList<BurnningRecord>();

ExerServ serv = exerService.getUserService(user.getUserId());

list= exerService.getBurnningRecordList(serv.getUserServiceNo());



record.setExerNo(exerNo);

exer.setExerNo(record.getExerNo());
record.setUserId(user.getUserId());
record.setExer(exer);
record.setExerLv(exerLv);
record.setUserExerBurnning(anExerKcal);
record.setAnExerTime(resultTime2);

record.setDailyExerKcal(record.getUserExerBurnning() + anExerKcal);
record.setDailyExerTime(resultTime2);

System.out.println("�ԷµǴ� ���� ���~~~"+record);

exerService.addDailyBurnning(record);

////������� ���� ����

//�ʿ��� parameter
String userId = user.getUserId();
LocalDate startDate = LocalDate.now();
LocalDate endDate = LocalDate.now();
LocalDate date = LocalDate.now();

//������ �� �Ҹ� Į�θ� ��ȸ
TotalRecord totalExerRecord = exerService.getTotalExerRecord(startDate, endDate, serv.getUserServiceNo());
//������� ��ǥ �Ҹ� Į�θ� ��ȸ
UserServ userServ = diaryService.getUserServiceDetails(userId);

if(totalExerRecord.getTotalBurnningKcal() >= userServ.getDailyTrgtBurnningKcal()) {	
//������ �� �Ҹ� Į�θ��� ��ǥ �Ҹ� Į�θ� �̻��Ͻ�
	diaryService.updateBadgeByExer(userId, 1, totalExerRecord.getTotalBurnningKcal(), date);
} else {
	diaryService.updateBadgeByExer(userId, 0, totalExerRecord.getTotalBurnningKcal(), date);
}


model.addAttribute("NavName1","�����");
model.addAttribute("NavName2","���� ��� ���� ��ȸ");




return "exer/getDailyBurnning.html";


}

@GetMapping("deleteDailyBurnning")
public String deleteDailyBurnning(Model model, HttpServletRequest request, @RequestParam("burnningRecordNo") int burnningRecordNo)
		throws Exception {
	
	
	System.out.println("deleteDailyBurnning");
	
	
	List<BurnningRecord> list = new ArrayList<BurnningRecord>();

	System.out.println(burnningRecordNo);
	
	
	exerService.deleteBurnningRecord(burnningRecordNo);
	
	
	int radio = 0;
	
	User user = (User) request.getSession(true).getAttribute("user");
	
	System.out.println(user);

	ExerServ exerServ = exerService.getUserService(user.getUserId());
	
	list = exerService.getBurnningRecordList(exerServ.getUserServiceNo());
	
	System.out.println(list);
	
	System.out.println(radio);
	
	
	////������� ���� ����
	
	//�ʿ��� parameter
	String userId = user.getUserId();
	LocalDate startDate = LocalDate.now();
	LocalDate endDate = LocalDate.now();
	LocalDate date = LocalDate.now();
	
	//������ �� �Ҹ� Į�θ� ��ȸ
	TotalRecord totalExerRecord = exerService.getTotalExerRecord(startDate, endDate, exerServ.getUserServiceNo());
	//������� ��ǥ �Ҹ� Į�θ� ��ȸ
	UserServ userServ = diaryService.getUserServiceDetails(userId);
	
	if(totalExerRecord.getTotalBurnningKcal() >= userServ.getDailyTrgtBurnningKcal()) {	
	//������ �� �Ҹ� Į�θ��� ��ǥ �Ҹ� Į�θ� �̻��Ͻ�
		diaryService.updateBadgeByExer(userId, 1, totalExerRecord.getTotalBurnningKcal(), date);
	} else {
		diaryService.updateBadgeByExer(userId, 0, totalExerRecord.getTotalBurnningKcal(), date);
	}
	
	
	model.addAttribute("list", list);
	model.addAttribute("serv", exerServ);
	model.addAttribute("radio", radio);
	model.addAttribute("NavName1","�����");
	model.addAttribute("NavName2","���� ��� ���� ����");

	return "redirect:../exer/getDailyBurnning";

}












@GetMapping("getDailyBurnning")
public String getDailyBurnning(@ModelAttribute("exerServ") ExerServ exerServ,
							   Model model, HttpServletRequest request, HttpSession session,
							   @RequestParam(value = "radio", required = false) String radio) throws Exception {

	
System.out.println("getDailyBurnning");


if (radio == null) {
	radio = "0";
	
}


User user =(User)request.getSession(true).getAttribute("user");

/*
 * Exer exer = new Exer();
 * 
 * exer = exerService.getExer(exerNo);
 */



exerServ = exerService.getUserService(user.getUserId());

Double daily = exerServ.getDailyTrgtBurnningKcal();

System.out.println(daily);


List<BurnningRecord> list = new ArrayList<BurnningRecord>();

list= exerService.getBurnningRecordList(exerServ.getUserServiceNo());
System.out.println(list);

Double userExerBurnning =  exerService.sumBurnningKcal(exerServ.getUserServiceNo());
System.out.println(userExerBurnning);

Double sumIntakeKcal = exerService.sumIntakeKcal(user.getUserId());
System.out.println(sumIntakeKcal);

if(sumIntakeKcal == null) {
	
	sumIntakeKcal = 0.0;
}

System.out.println(sumIntakeKcal);




user = exerService.needDaliyIntakeKcal(user.getUserId());



System.out.println(user);


Double  dailyIntakeKcal	= 0.0;
Double 	totaldailyIntakeKcal = 0.0;

if(user.getGender().equals("male")) {
	
  
  dailyIntakeKcal	= 66 + (13.7 * user.getWeight() + 5 * user.getHeight() - 6.8 * user.getAge());
	
	
} else {
	
 dailyIntakeKcal = 655 + (9.6 * user.getWeight() + 1.8 * user.getHeight() - 4.7 * user.getAge());
	
	
}

System.out.println(dailyIntakeKcal);

if(user.getServiceTrgt().equals("ü������")) {
	
	totaldailyIntakeKcal= dailyIntakeKcal * 1.55;
	
} if (user.getServiceTrgt().equals("ü������")){
	
	totaldailyIntakeKcal= dailyIntakeKcal * 1.375;
	
} else {
	
	totaldailyIntakeKcal = dailyIntakeKcal * 1.2;
	
}

System.out.println(totaldailyIntakeKcal);



if(userExerBurnning == null) {
	
	userExerBurnning  = 0.0;
}


Double remainKcal = 0.0 ;


	remainKcal = totaldailyIntakeKcal - sumIntakeKcal + userExerBurnning;
	

remainKcal = Math.round(remainKcal*100)/100.0;

Double totaldailyIntakeKcal1 = Math.round(totaldailyIntakeKcal*100)/100.0;


System.out.println(" �ܿ� Į�θ� �Դϴ�   " + remainKcal);


if(remainKcal >= 0) {
	
	remainKcal = 0.0;
	
}if(remainKcal < 0) {
	
	remainKcal = remainKcal * (-1);
	
}





List list1 = exerService.recommandExerList(Math.abs(remainKcal));



model.addAttribute("list", list1);
model.addAttribute("totaldailyIntakeKcal", totaldailyIntakeKcal1);
model.addAttribute("overKcal", remainKcal);
model.addAttribute("list", list);
model.addAttribute("exerServ", exerServ);
model.addAttribute("userExerBurnning", userExerBurnning);
model.addAttribute("radio", radio);
model.addAttribute("daily", daily);
model.addAttribute("NavName1","�����");
model.addAttribute("NavName2","���� ��� ���� ��ȸ");


return "exer/getDailyBurnning.html";


}





@GetMapping("addRoutineInfo")
public String getAddRoutineInfo(Model model, @RequestParam("routineInfo")int exerNo,HttpServletRequest request) throws Exception {
	
	
	System.out.println("getAddRoutineInfo");
	
	Exer exer = new Exer();
	
	exer = exerService.getExer(exerNo);
	
	
	System.out.println(exer);
	
	
	User user =(User)request.getSession(true).getAttribute("user");
	

	System.out.println(user);
	

	ExerServ serv = exerService.getUserService(user.getUserId());
	

	System.out.println(serv);
	
	Map<String,Object> map = new HashMap<String,Object>();
	
	map = exerService.getRoutineList(serv.getUserServiceNo());

	//System.out.println(map);
	
	model.addAttribute("list",map.get("list"));
	model.addAttribute("exer", exer);
	
	
	if(serv.equals(null)) {
		
		return "user/login :: hong";
		
	}else {
	
	return "exer/addRoutineInfo :: addRoutineInfo";
	
	}

}

@PostMapping("addRoutineInfo")
public String postAddRoutineInfo(Model model, @RequestParam("exerNo")int exerNo,
		@RequestParam("anExerKcal")Double anExerKcal ,
		@RequestParam("routineNo")int routineNo, 
		@RequestParam("exerLv")String exerLv,
		@RequestParam("anExerTime")String anExerTime) throws Exception {
	


	
	System.out.println("postAddRoutineInfo");
	
	System.out.println("���Ӹ�~~~" + exerNo + anExerKcal + routineNo + exerLv + anExerTime);
	

	Routine routine = new Routine();
	
	Exer exer = new Exer();
	
	routine = exerService.getRoutine(routineNo);
	
	if(routine.getRoutineTime() == null || routine.getRoutineKcal() ==null ) {
		
	routine.setRoutineTime("0:00:00");
	routine.setRoutineKcal(0.0);
	
	}
	
	System.out.println("1"+routine);
	
	int hour = Integer.parseInt(routine.getRoutineTime().split(":")[0]) * 60;   // 0
	int min = Integer.parseInt(routine.getRoutineTime().split(":")[1]) + hour; // 50
	
	
	System.out.println(hour); // 60
	System.out.println(min);  // 90
	
	System.out.println("������  " + min);  //50

	int routineTime = min + Integer.parseInt(anExerTime); // 50 + 70= 120
	
	
	System.out.println(routineTime); //120
	
	

	String resultTime1;
	String resultTime2;
	
	
	int a = Integer.parseInt(anExerTime) / 60; //2
	
	int b =Integer.parseInt(anExerTime) % 60; //0
	
	
	System.out.println(a); // 1
	System.out.println(b);  //10
	
	
	if(b == 0) {
		
		resultTime1 = a + ":" + b + "0" + ":" + "00";
		
	}else { 
	
	resultTime1 = a  + ":" + b + ":" + "00";
	}
	
	
	System.out.println(resultTime1);

	
	
	int c = (routineTime) / 60; //2
	int d = (routineTime) % 60; //0
	
	System.out.println(c);
	System.out.println(d);
	
	if(d == 0) {
		
		 resultTime2 = c + ":" + d +"0"+ ":" + "00";
		
	}else {
	
	 resultTime2 = c + ":" + d + ":" + "00";
	}
	 
	System.out.println(resultTime2);
	
	
	routine.setExerNo(exerNo);
	
	exer.setExerNo(routine.getExerNo());
	
	routine.setExer(exer);
	routine.setExerLv(exerLv);
	routine.setAnExerKcal(anExerKcal);
	routine.setRoutineKcal(routine.getRoutineKcal() + anExerKcal);
	routine.setAnExerTime(resultTime1);
	routine.setRoutineTime(resultTime2);
	
	
	System.out.println("2"+routine);
	
	
	exerService.addRoutineInfo(routine);
	
	
	return "redirect:../exer/getRoutineList";

	
}


@GetMapping("deleteRoutineInfo")
public String deleteRoutineInfo(Model model, @RequestParam("routineInfoNo")String routineInfoNo,
								@RequestParam("routineNo") String routineNo,
								@RequestParam("routineName") String routineName,
								HttpServletRequest request) throws Exception {


	System.out.println("deleteRoutineInfo");
	
		
	System.out.println(Integer.parseInt(routineInfoNo)); //������ ��ƾ���� ��ȣ
	
	System.out.println(Integer.parseInt(routineNo)); // ��ƾ ��ȣ

	
	Routine routineInfo = new Routine();
	
	Routine routine = new Routine();
	
	
	
	routineInfo = exerService.getRoutineInfo(Integer.parseInt(routineInfoNo)); //������ ��ƾ���� ��������
	routine = exerService.getRoutine(Integer.parseInt(routineNo)); // ��ƾ ��������
	
	
	
	//������ ��ƾ���� �ð� ex) 00:30:00
	int routineHour = Integer.parseInt(routineInfo.getAnExerTime().split(":")[0]) * 60;   // 0
	int routineMin = Integer.parseInt(routineInfo.getAnExerTime().split(":")[1]) + routineHour; // 30
	
	System.out.println("routineMin   " + routineMin);
	
	
	
	//��ƾ �� �ð� 1:30:00
	int hour = Integer.parseInt(routine.getRoutineTime().split(":")[0]) * 60; // 90
	int min = Integer.parseInt(routine.getRoutineTime().split(":")[1]) + hour; // 90
	
	
	System.out.println("min  "+ min);
	
	
	int finalMin = min  - routineMin;
	
	
	System.out.println("�����ϰ� ������ ��ƾ���� �ð�" + finalMin );  //1:30:00

	
	//��ƾ �� �ð� 90�� �ٽ� 1:30:00 ���·� �ٲٱ�
	String resultTime1;
	
	
	int a = finalMin / 60;  // 1   
	
	int b = finalMin % 60;  // 30   ���࿡ �� 60�̶� ���� 0�̶�� �ڿ� 0�� ����ƽ�ϰ� �ھ�����
	
	
	System.out.println("�ð�  " + a);
	System.out.println("��  " + b);
	
	
	if(b == 0) {
		
		resultTime1 = a + ":" + b + "0" + ":" + "00";
		
	} else {
	
	resultTime1 = a + ":"  + b + ":" + "00";
	
	}
	
	

	System.out.println(	resultTime1);
	
	
	
	Exer exer  = new Exer();
	
	routine.setRoutineKcal(routine.getRoutineKcal() - routineInfo.getAnExerKcal());
	routine.setRoutineTime(resultTime1);
	
	
	System.out.println("�����ϰ� ������ߵ� ��ƾ"+routine);
	
	
	
	
	
	exerService.deleteRoutineInfo(Integer.parseInt(routineInfoNo));
		
	
	exerService.updateRoutine(routine);
	
	
	
	String name = URLEncoder.encode(routineName, "UTF-8");

	
	System.out.println(name);
	
	
	return "redirect:../exer/getRoutine?routineInfoNo=" + Integer.parseInt(routineInfoNo)+ "&routineName=" + name + "&routineNo=" + routineNo;
}





@GetMapping("routineInfoAddBurnningRecord")
public String routineInfoAddBurnningRecord(Model model, @RequestParam("routineInfoNo") String routineInfoNo,
										HttpServletRequest request) throws Exception {

	
		User user = (User) request.getSession(true).getAttribute("user");
	
	
		Routine routine = new Routine();
		Exer exer = new Exer();
		BurnningRecord record = new BurnningRecord();
		

		routine = exerService.getRoutineInfo(Integer.parseInt(routineInfoNo));
		
		System.out.println("��ƾ���� ���Ͽ���� ���� �  " + Integer.parseInt(routineInfoNo));

		exer = exerService.getExer(routine.getExerNo());
		
		ExerServ exerServ = exerService.getUserService(user.getUserId());
		
		
		//exerService.getUserService(user.getUserId());
		
	
		exer.setExerNo(routine.getExerNo());
		
		
		record.setUserId(user.getUserId());
		record.setExer(exer);
		record.setExerNo(routine.getExerNo());
		//record.setExerLv(exerLv);
		record.setUserExerBurnning(routine.getAnExerKcal());
		record.setAnExerTime(routine.getAnExerTime());
		record.setExerName(routine.getExerName());
		record.setExerLv(routine.getExerLv());
		record.setUserServiceNo(exerServ.getUserServiceNo());;
		record.setDailyExerKcal(record.getUserExerBurnning() + routine.getAnExerKcal());
		//record.setDailyExerTime(resultTime2);
		
		System.out.println("��ƾ���� �ԷµǴ� ���� ���~~~"+record);
		
		exerService.addDailyBurnning(record);
		
		////������� ���� ����
		
		//�ʿ��� parameter
		String userId = user.getUserId();
		LocalDate startDate = LocalDate.now();
		LocalDate endDate = LocalDate.now();
		LocalDate date = LocalDate.now();
		
		//������ �� �Ҹ� Į�θ� ��ȸ
		TotalRecord totalExerRecord = exerService.getTotalExerRecord(startDate, endDate, exerServ.getUserServiceNo());
		//������� ��ǥ �Ҹ� Į�θ� ��ȸ
		UserServ userServ = diaryService.getUserServiceDetails(userId);
		
		if(totalExerRecord.getTotalBurnningKcal() >= userServ.getDailyTrgtBurnningKcal()) {	
		//������ �� �Ҹ� Į�θ��� ��ǥ �Ҹ� Į�θ� �̻��Ͻ�
			diaryService.updateBadgeByExer(userId, 1, totalExerRecord.getTotalBurnningKcal(), date);
		} else {
			diaryService.updateBadgeByExer(userId, 0, totalExerRecord.getTotalBurnningKcal(), date);
		}


	return "redirect:../exer/getDailyBurnning";
}











@PostMapping("addRoutine")
public String addRoutine(Model model,@RequestParam("routineName")String routineName,
						HttpServletRequest request) throws Exception {

	
	
	System.out.println("addRoutine");
	
	User user = (User) request.getSession(true).getAttribute("user");
	
	Routine routine = new Routine();
	
	String a = "";
	
	if(routine.getRoutineTime() == null ) {
		
		 a = routine.getRoutineTime();
		
		 a = "0:00:00";
		
	}
	
	routine.setRoutineTime(a);
	routine.setRoutineName(routineName);
	routine.setUserId(user.getUserId());
	exerService.addRoutine(routine);

	return "redirect:../exer/getRoutineList?menu=0";
	
}

@PostMapping("updateRoutine")
public String updateRoutine(Model model,@RequestParam("routineNo")String routineNo,
							@RequestParam("routineName")String routineName) throws Exception {
	
	System.out.println("updateRoutine");
	
	
	Routine routine = new Routine();
	
	int no = Integer.parseInt(routineNo);
	
	routine.setRoutineNo(no);
	routine.setRoutineName(routineName);
	
	exerService.updateRoutineName(routine);
	
	
	return "redirect:../exer/getRoutineList?menu=0";
}



@PostMapping("deleteRoutine")
public String deleteRoutine(Model model,@RequestParam("routineNo")int routineNo,
						HttpServletRequest request) throws Exception {
	
	
	System.out.println("deleteRoutine");
	
	exerService.deleteRoutine(routineNo);
	
	return "redirect:../exer/getRoutineList?menu=0";
}





@GetMapping("recommandExerList")
public String recommandExerList(Model model, HttpServletRequest request, HttpSession session) throws Exception {
	
	System.out.println("recommandExerList");
	
	User user =(User)request.getSession().getAttribute("user");

	
	System.out.println(user.getUserId());
	Double sumIntakeKcal = exerService.sumIntakeKcal(user.getUserId());
	
	if(sumIntakeKcal == null) {
		
		sumIntakeKcal = 0.0;
	}

	
	System.out.println(sumIntakeKcal);
	
	user = exerService.needDaliyIntakeKcal(user.getUserId());
	System.out.println(user);
	
	
	Double  dailyIntakeKcal	= 0.0;
	Double 	totaldailyIntakeKcal = 0.0;
	
	if(user.getGender().equals("male")) {
		
	  
	  dailyIntakeKcal	= 66 + (13.7 * user.getWeight() + 5 * user.getHeight() - 6.8 * user.getAge());
		
		
	} else {
		
	 dailyIntakeKcal = 655 + (9.6 * user.getWeight() + 1.8 * user.getHeight() - 4.7 * user.getAge());
		
		
	}
	
	System.out.println(dailyIntakeKcal);
	
	if(user.getServiceTrgt().equals("ü������")) {
		
		totaldailyIntakeKcal= dailyIntakeKcal * 1.55;
		
	} if (user.getServiceTrgt().equals("ü������")){
		
		totaldailyIntakeKcal= dailyIntakeKcal * 1.375;
		
	} else {
		
		totaldailyIntakeKcal = dailyIntakeKcal * 1.2;
		
	}
	
	System.out.println(totaldailyIntakeKcal);
	
	
	
	Double remainKcal = (totaldailyIntakeKcal - sumIntakeKcal) ;
	
	
	
	remainKcal = Math.round(remainKcal*100)/100.0;
	
	
	System.out.println(" �ܿ� Į�θ� �Դϴ�   " + remainKcal);
	
	
	if(remainKcal >= 0) {
		
		remainKcal = 0.0;
		
	}if(remainKcal < 0) {
		
		remainKcal = remainKcal * (-1);
		
	}
	
	List list = exerService.recommandExerList(Math.abs(remainKcal));
	
	
	model.addAttribute("overKcal", remainKcal);
	model.addAttribute("list", list);
	model.addAttribute("NavName1","�����");
	model.addAttribute("NavName2","� ��õ ����Ʈ");
	session.setAttribute("overKcal", remainKcal);
	
	return "exer/recommandExerList.html";
	
	
}


}
