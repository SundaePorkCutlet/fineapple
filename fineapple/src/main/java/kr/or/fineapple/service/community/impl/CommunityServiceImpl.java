package kr.or.fineapple.service.community.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.common.Search;
import kr.or.fineapple.domain.community.Alarm;
import kr.or.fineapple.domain.community.Battle;
import kr.or.fineapple.domain.community.Board;
import kr.or.fineapple.domain.community.Cmnt;
import kr.or.fineapple.domain.community.Group;
import kr.or.fineapple.domain.community.GroupBorad;
import kr.or.fineapple.domain.community.GroupUser;
import kr.or.fineapple.domain.community.Img;
import kr.or.fineapple.domain.community.MtmQna;
import kr.or.fineapple.domain.community.Report;
import kr.or.fineapple.mapper.CommunityMapper;
import kr.or.fineapple.service.community.CommunityService;



@Service
public class CommunityServiceImpl implements CommunityService {
	
	@Autowired
	private CommunityMapper communityMapper;

	public CommunityServiceImpl() {
		System.out.println(getClass().getName()+  "생성함");
	}

	@Override
	public void addPost(Board board, String[] times) {
		communityMapper.addPost(board);
		for (String time : times) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("postNo", board.getPostNo() + "");
			map.put("time", time);
			communityMapper.addPostImg(map);
		}
	}

	@Override
	public Map addCmnt(Cmnt cmnt) {
		communityMapper.addCmnt(cmnt);
		System.out.println(cmnt+ "add후");
		Map map = new HashMap();
		map.put("list", communityMapper.getCmntListAfterAddCmnt(cmnt));
		return map;
		
	}

	@Override
	public List<Board> getPostList() {
		return communityMapper.getPostList();
	}
	
	@Override
	public Map getPost(Board board) {
		communityMapper.updatePostViewCount(board);
		Map map = new HashMap();
		Board boardData = communityMapper.getPost(board);
		boardData.setImg(communityMapper.getPostImg(board.getPostNo()));
		map.put("list", communityMapper.getCmntList(board));
		map.put("board", boardData);
		//map.put("imgList", communityMapper.getPostImg(board.getPostNo())); // 애초에 board안에 img라는 List를 담아서 줘도 된다 하지만 나는 비교를 해서 할거다.
		
		return map;
	}
	
	@Override
	public void updatePostViewCount(Board board) {
		communityMapper.updatePostViewCount(board);
	}

	@Override
	public Board updatePostLike(Board board) {
		
		if (board.getPostLikeStt() == 0) {
			communityMapper.addPostLike(board);
		}
		else if (board.getPostLikeStt() == 1){
			communityMapper.deletePostLike(board);
		}
		
		return communityMapper.getPost(board);
	}

	@Override
	public void updateCmntLike(Cmnt cmnt) {
	}
	
	@Override
	public List<User> getGroupInterUser(GroupUser groupUser) {
		return communityMapper.getGroupInterUser(groupUser);
	}
	
	
	public List<Group> getGroupInterGroup(GroupUser groupUser){
		return communityMapper.getGroupInterGroup(groupUser);
	}
	
	public void addReport(Report report) {
		communityMapper.addReport(report);
	}

	@Override
	public void addGroup(Group group, GroupUser groupUser) {
		communityMapper.addGroup(group);
		System.out.println(group+"In service addGroup");
		groupUser.setGroup(group);
		communityMapper.addGroupUser(groupUser);
	}

	@Override
	public List getAlarmList(User user) {
	
		return communityMapper.getAlarmList(user);
	}

	@Override
	public Group checkGroupName(String groupName) {
		// TODO Auto-generated method stub
		return communityMapper.checkGroupName(groupName);
	}

	@Override
	public List<User> getUserSearchList(Search search) {
		return communityMapper.getUserSearchList(search);
	}

	@Override
	public List<Group> getGroupToUserInter(GroupUser groupUser) {
		// TODO Auto-generated method stub
		return communityMapper.getGroupToUserInter(groupUser);
	}

	@Override
	public User getUserSearch(Search search) {
		// TODO Auto-generated method stub
		return communityMapper.getUserSearch(search);
	}

	@Override
	public void addGroupToUserInter(GroupUser groupUser) {
		
		communityMapper.addGroupToUserInter(groupUser);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MtmQna> getFaqList(int cate) {
		// TODO Auto-generated method stub
		return communityMapper.getFaqList(cate);
	}

	@Override
	public void delGroupUserInter(HashMap map, int intetStt) {

		
		if (intetStt == 1) {
			communityMapper.updateGroupUserInter(map);
		}
		
		else if (intetStt == 2) {
			communityMapper.delGroupUserInter(map);
		}
		
	}

	@Override
	public void deleteAlarm(Alarm alarm) {
		communityMapper.deleteAlarm(alarm);
		
	}

	@Override
	public void deleteAlarmAll(Alarm alarm) {
		// TODO Auto-generated method stub
		communityMapper.deleteAlarmAll(alarm);
	}

	@Override
	public List<Report> getReportListAll(Report report) {
		// TODO Auto-generated method stub
		return communityMapper.getReportListAll(report);
	}

	@Override
	public List<Report> getReportListNotDispose(Report report) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Report getReport(Report report, User user) {
		
		report = communityMapper.getReport(report);
		
		report.setUser(user);
		
		// TODO Auto-generated method stub
		if (report.getReportCate().equals("1")) {
			Board board = new Board();
			board.setPostNo(report.getBoard().getPostNo());
			board = communityMapper.getPostReport(board);
			report.setBoard(board);
			
			
		} else if (report.getReportCate().equals("2")) {
			Cmnt cmnt = new Cmnt();
			cmnt.setCmntNo(report.getCmnt().getCmntNo());
			cmnt = communityMapper.getCmnt(cmnt);
			report.setCmnt(cmnt);
			
		}
		else {
			System.out.println("ㅋㅋㅋㅋㅋㅋㅋ");
		}

		
		User reportedUser = new User();
		
		reportedUser = communityMapper.getReportedUser(report);
		
		report.setReportedUser(reportedUser);
		
		
		System.out.println("서어비스"+report);
		return report;
	}

	@Override
	public void addMtmQna(MtmQna mtmQna) {
		communityMapper.addMtmQna(mtmQna);
		
	}


	
	//블랙리스트
	@Override
	public void addUserBlc(User userId) {
	
		communityMapper.addUserBlc(userId);
		
	}

	//신고처리 완료
	@Override
	public void updateReportStt(Report report) {
		
		communityMapper.updateReportStt(report);
		
	}

	@Override
	public void addFqa(MtmQna mtmQna) {
		
		communityMapper.addFaq(mtmQna);
		
	}

	@Override
	public void deleteFaq(int mtmQnaNo) {
		
		communityMapper.deleteFaq(mtmQnaNo);
		
	}

	@Override
	public User getUserBattle(User user) {
		// TODO Auto-generated method stub
		return communityMapper.getUserBattle(user);
	}

	@Override
	public void addBattleInter(Battle battle, String rivalUserId) {
		
		User rivalUser = new User();
		
		rivalUser.setUserId(rivalUserId);
		
		rivalUser = communityMapper.getUserBattle(rivalUser);
		
		battle.setRivalUser(rivalUser);
		
		communityMapper.addBattleInter(battle);
		
		
		
	}

	@Override
	public List<Battle> getMybattleInter(Battle battle) {
		// TODO Auto-generated method stub
		return communityMapper.getMybattleInter(battle);
	}

	@Override
	public void delPost(Board board) {
		communityMapper.delPost(board);
		
	}

	@Override
	public List<MtmQna> getMyMtmList(User user) {
		// TODO Auto-generated method stub
		return communityMapper.getMyMtmList(user);
	}

	@Override
	public Board updatePostView(Board board) {
		// TODO Auto-generated method stub
		return communityMapper.getPost(board);
	}

	@Override
	public Group getGroup(Group group) {
		// TODO Auto-generated method stub
		
		group = communityMapper.getGroup(group);
		
		List<GroupUser> list = communityMapper.getGroupUserList(group);
		
		group.setGroupUsers(list);
		
		return group;
		
		
		
	}

	@Override
	public List<Group> getGroupList() {
		// TODO Auto-generated method stub
		return communityMapper.getGroupList();
	}

	@Override
	public List getGroupBoard(GroupBorad groupBorad) {
		
		List<GroupBorad> list = communityMapper.getGroupPostList(groupBorad);
		
		for (Object object : list) {
			System.out.println(object);
		}
		
		for (GroupBorad groupBorad2 : list) {
			
			List<Cmnt> list1 = communityMapper.getGroupCmntList(groupBorad2);
			
			groupBorad2.setCmnt(list1);
			
		}
		
		for (GroupBorad groupBorad2 : list) {
			System.out.println(groupBorad2);
		}
		
		for (GroupBorad groupBorad2 : list) {
			 List<Img> list1 = communityMapper.getPostImg(groupBorad2.getPostNo());
			 
			 groupBorad2.setImg(list1);
		}
		
		for (GroupBorad groupBorad2 : list) {
			System.out.println(groupBorad2);
		}
		
		
		
		return list;
	}

	@Override
	public void addGroupPost(GroupBorad groupBorad, String[] times) {
		
		communityMapper.addGroupPost(groupBorad);
		for (String time : times) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("postNo", groupBorad.getPostNo() + "");
			map.put("time", time);
			communityMapper.addPostImg(map);
		}
		
		
	}

	@Override
	public void delRequestBattle(Battle battle) {
		communityMapper.delRequestBattle(battle);
		
	}

	@Override
	public Battle getBattle(Battle battle) {
		// TODO Auto-generated method stub
		return communityMapper.getBattle(battle);
	}

	@Override
	public void updateBattle(Battle battle) {
		
		Battle bbattle = new Battle();
		
		bbattle = communityMapper.getBattle(battle);
		
		battle.setBattlePeriod(bbattle.getBattlePeriod());
		communityMapper.updateBattle(battle);
		
	}
	
	
	
	
	
	
	
	
	
	
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	

	


}
