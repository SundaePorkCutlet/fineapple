package kr.or.fineapple.service.community.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.common.Search;
import kr.or.fineapple.domain.community.Board;
import kr.or.fineapple.domain.community.Cmnt;
import kr.or.fineapple.domain.community.Group;
import kr.or.fineapple.domain.community.GroupUser;
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
	public List getAlarmList() {
	
		return communityMapper.getAlarmList();
	}

	@Override
	public Group checkGroupName(String groupName) {
		// TODO Auto-generated method stub
		return communityMapper.checkGroupName(groupName);
	}

	@Override
	public List getUserSearchList(Search search) {
		return communityMapper.getUserSearchList(search);
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	

	


}
