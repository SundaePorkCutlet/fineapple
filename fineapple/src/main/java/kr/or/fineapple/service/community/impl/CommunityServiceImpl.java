package kr.or.fineapple.service.community.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.common.Search;
import kr.or.fineapple.domain.community.Alarm;
import kr.or.fineapple.domain.community.Board;
import kr.or.fineapple.domain.community.Cmnt;
import kr.or.fineapple.domain.community.Group;
import kr.or.fineapple.domain.community.GroupUser;
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
	public void addPost(Board board) {
		communityMapper.addPost(board);
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
		map.put("list", communityMapper.getCmntList(board));
		map.put("board", communityMapper.getPost(board));
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

	@Override
	public List<MtmQna> getMtmList(User user) {
		// TODO Auto-generated method stub
		return communityMapper.getMyMtmList(user);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	

	


}
