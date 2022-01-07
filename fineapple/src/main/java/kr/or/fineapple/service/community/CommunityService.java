package kr.or.fineapple.service.community;

import java.util.List;
import java.util.Map;

import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.community.Board;
import kr.or.fineapple.domain.community.Cmnt;
import kr.or.fineapple.domain.community.Group;
import kr.or.fineapple.domain.community.GroupUser;
import kr.or.fineapple.domain.community.Report;

public interface CommunityService {
	
	public void addPost(Board board);
	
	public Map addCmnt(Cmnt cmnt);
	
	public List<Board> getPostList();
	
	public Map getPost(Board board);
	
	public void updatePostViewCount(Board board);
	
	public Board updatePostLike(Board board);
	
	public void updateCmntLike(Cmnt cmnt);
	
	public List<Group> getGroupInterGroup(GroupUser groupUser);
	
	public List<User> getGroupInterUser(GroupUser groupUser);
	
	public void addReport(Report report);
	
	public void addGroup(Group group, GroupUser groupUser);
	
	public Group checkGroupName(String groupName);
	
	public List getAlarmList();
}
