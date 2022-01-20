package kr.or.fineapple.community;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.common.Search;
import kr.or.fineapple.domain.community.Battle;
import kr.or.fineapple.domain.community.BlackList;
import kr.or.fineapple.domain.community.Board;
import kr.or.fineapple.domain.community.Cmnt;
import kr.or.fineapple.domain.community.Group;
import kr.or.fineapple.domain.community.GroupBorad;
import kr.or.fineapple.domain.community.GroupUser;
import kr.or.fineapple.domain.community.Report;
import kr.or.fineapple.service.community.CommunityService;





@RestController
@RequestMapping("/community/json/*")
public class CommunityRestController {
	
	
	@Autowired
	@Qualifier("communityServiceImpl")
	private CommunityService communityService;
	
	@Value("${file.upload.directory.mtmQna}")
	private String mtmFilePath;
	
	
	
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
		
		

	
	@PostMapping(value = "addReport")
	public void addReport(@RequestBody String str, HttpServletRequest request) {
		
		JSONObject jsonObject = (JSONObject)JSONValue.parse(str);
		
		String reportCate = jsonObject.get("reportCate").toString();
		
		String TrgtNo = jsonObject.get("TrgtNo").toString();
		
		String time = jsonObject.get("time").toString();
		
		String reportedUserId = jsonObject.get("reportedUser").toString();
		
		String reportcontent = jsonObject.get("reportcontent").toString();
		
		System.out.println(reportCate + "+" + TrgtNo + "+" + time + "+" + reportedUserId + "+" + reportcontent);
		
		User user = new User();
		
		User reportedUser = new User();
		
		user = (User)request.getSession(true).getAttribute("user");
		
		reportedUser.setUserId(reportedUserId);
		
		Report report = new Report();
		
		report.setUser(user);
		
		report.setReportedUser(reportedUser);
		
		report.setReportCate(reportCate);
		
		report.setReportCntnt(reportcontent);
		
		report.setTrgtNo(Integer.parseInt(TrgtNo));
		
		report.setReportStt(0);
		
		
		
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy�� MM�� dd�� HH�� mm��");
		 
		 
		//report.setReportDate(LocalDate.parse(time, formatter));
		
		
		System.out.println(report);
		
		communityService.addReport(report);
		
	}
	
	
	
	@PostMapping(value = "checkGroupName")
	public String checkGroupName(@RequestBody String groupName) {
		JSONObject jsonObject = (JSONObject)JSONValue.parse(groupName);
		
		groupName = jsonObject.get("groupName").toString();
		
		Group group = communityService.checkGroupName(groupName);
		
		System.out.println(group + "oooooooooooooooooooooooooooo");
		
		if (group != null) {
			
			return "0";
			
		}
		
		if (group == null) {
			
			return "1";
			
		}
		
		return "3";
	}
	
	
	@PostMapping(value = "addGroup")
	public void addGroup(@RequestBody Group group, HttpServletRequest request) {
		
		
		User user = (User)request.getSession(true).getAttribute("user");
		
		GroupUser groupUser = new GroupUser();
		
		groupUser.setUser(user);
		
		groupUser.setCaptainStt(1);
		
		groupUser.setGroupStt(4);
		
		communityService.addGroup(group, groupUser);
		
		
		System.out.println(group);
		
		
		
		
		
	}
	
	
	@PostMapping(value = "getUserSerach")
	public List getUserSerach(@RequestBody Search search) {
		
		System.out.println("getUserSerach" + "��ħ");
		
		System.out.println(search);
		
		//System.out.println(getClass().getEnclosingMethod().getName() + "��ħ"); //==> �̰� �ּ�Ǯ�� null�ַ� �߻�
		
		List<User> list = communityService.getUserSearchList(search);
		
			
		for (User user : list) {
			System.out.println(user);
		}
		
		return communityService.getUserSearchList(search);
	}
	
	
	
	@PostMapping(value = "addGroupToUserInter")
	public void AddGroupToUserInter(@RequestBody String str) {
		
		System.out.println(str + "�ӱ⹫��~");
		
		JSONObject jsonObject = (JSONObject)JSONValue.parse(str);
		
		GroupUser groupUser = new GroupUser();
		
		Group group = new Group();
		
		group.setGroupNo(Integer.parseInt(jsonObject.get("groupNo").toString()));
		
		groupUser.setGroup(group);
		
		User interUser = new User();
		
		interUser.setUserId(jsonObject.get("interUserId").toString());
		
		groupUser.setUser(interUser);
		
		groupUser.setGroupStt(1);
		
		System.out.println(groupUser);
		
		communityService.addGroupToUserInter(groupUser);
		
		
		
		System.out.println("�� ��Ҷ�~" + group);
		
		
	}
	
	@PostMapping(value = "AcceptOrRejectGroupInter")
	public void AcceptOrRejectGroupInter(@RequestBody String str, HttpServletRequest request) {
		
		System.out.println(str);
		
		JSONObject jsonObject = (JSONObject)JSONValue.parse(str); //{"interStt":1,"groupUserNoData":"1"}
		
		int intetStt = Integer.parseInt(jsonObject.get("interStt").toString());
		
		int groupNo = Integer.parseInt(jsonObject.get("groupUserNoData").toString());
		
		User user = new User();
		
		user = (User)request.getSession(true).getAttribute("user");
		
		HashMap map = new HashMap();
		map.put("groupNo", groupNo);
		map.put("captainStt", 2);
		map.put("groupStt", 4);
		map.put("user", user);
		
		communityService.delGroupUserInter(map, intetStt);
		

		
	}
	
	
	
	@PostMapping(value = "addBlackList")
	public void addBlackList(@RequestBody String str,  HttpServletRequest request) {
		
		System.out.println("post::addBlackList");
		
		
		System.out.println(str);
		
		JSONObject jsonObject = (JSONObject)JSONValue.parse(str);
		
		BlackList blackList = new BlackList();
		
		User user = (User)request.getSession(true).getAttribute("user");
		
		user.setUserId(jsonObject.get("blackUserId").toString());
		
		blackList.setBlackUser(user);
		
		//blackList.setAddBlackWhy(jsonObject.get("blackcontent").toString());
		
		user.setBlcAddWhy(jsonObject.get("blackcontent").toString());
		
		

		communityService.addUserBlc(user);
		
		//User user = (User)request.getSession(true).getAttribute("user");
		
		
			
		//System.out.println("blackUser�� user ����:   " + user);
		
	}
	
	@RequestMapping(value = "addBattle", method = RequestMethod.POST)
	public void addBattle(@RequestBody String str, HttpServletRequest request) {
		//���� "battleCate":"1" �� ���� ���� Ŭ���̾�Ʈ���� ��԰� Id�� �Ȱ����Դ� �׷����� value�� null�� battleCate�� ������ request�� body�� ��ܼ� �Դ�. 
		System.out.println(str);
		
		JSONObject jsonObject = (JSONObject)JSONValue.parse(str);
		
		String rivalUserId = jsonObject.get("userId").toString();
		
		//String battleCate = jsonObject.get("battleCate").toString();
		
		//String battleTerm = jsonObject.get("battleTerm").toString();
		
		//String trgtKcal = jsonObject.get("trgtKcal").toString();
		
		int intTrgtKcal = Integer.parseInt(jsonObject.get("trgtKcal").toString());
		
		int battleTerm = Integer.parseInt( jsonObject.get("battleTerm").toString());
		
		int battleCate = Integer.parseInt(jsonObject.get("battleCate").toString());
		
		//valueOF = Integer
		//parseInt = int
		
		Battle battle = new Battle();
		
		battle.setUser((User)request.getSession(true).getAttribute("user"));
		
		battle.setBattleItemCate(battleCate);
		
		battle.setBattlePeriod(battleTerm);
		
		battle.setUserTrgtKcal(intTrgtKcal);
		battle.setBattleStt(1);
		
		communityService.addBattleInter(battle, rivalUserId);
		 
		
	}
	
	@PostMapping(value = "addGroupPost")
	public void addGroupPost(@RequestParam("content") String str, @RequestParam("uploadFile") MultipartFile[] files, @RequestParam("groupNo") String str2, HttpServletRequest request) throws IllegalStateException, IOException {
			String[] times = new String[files.length];
			
		
			
			int i = 0;
			for (MultipartFile multipartFile : files) {
			
			
			System.out.println(multipartFile.getOriginalFilename());
			String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY_MM_DD_HH_mm_ss_SSS"))+multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().indexOf("."), multipartFile.getOriginalFilename().length());
			multipartFile.transferTo(new File(mtmFilePath, time));
			times[i] = time; 
			i += 1;
		}
		System.out.println(str + "����");
		
		System.out.println(str2 + "�ұ׷� ��ȣ");
		
		
		GroupBorad groupBorad = new GroupBorad();
		
		groupBorad.setUser((User)request.getSession(true).getAttribute("user"));
		
		groupBorad.setContent(str);
		
		Group group = new Group();
		
		group.setGroupNo(Integer.parseInt(str2));
		
		groupBorad.setGroup(group);
		
		
		
		communityService.addGroupPost(groupBorad, times);
		

		
		
	}
	
	
	@PostMapping(value = "delRequestBattle")
	public void delRequestBattle(@RequestBody Battle battle) {
		
		System.out.println(battle);

		communityService.delRequestBattle(battle);
	}
	

	
	@PostMapping(value = "delReceviedBattleReject")
	public void delReceviedBattleReject(@RequestBody Battle battle) {
		
		System.out.println(battle);

		communityService.delRequestBattle(battle);

	}
	
	
	@PostMapping(value = "addBattleAccept")
	public void addBattleAccept(@RequestBody String str, HttpServletRequest request) {
		
		
		System.out.println(str);
		
		JSONObject jsonObject = (JSONObject)JSONValue.parse(str);
		
		
		Battle battle = new Battle();
		
		battle.setBattleNo(Integer.parseInt(jsonObject.get("battleNo").toString()));
		
		battle.setRivalTrgtKcal(Integer.parseInt(jsonObject.get("rivalTrgtKcal").toString()));
		
		System.out.println(battle);
		
		battle.setBattleStt(2);
		
		communityService.updateBattle(battle);
		
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
























