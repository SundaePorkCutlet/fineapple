package kr.or.fineapple.service.community;

import java.util.List;

import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.community.Board;
import kr.or.fineapple.domain.community.Cmnt;
import kr.or.fineapple.domain.community.Group;
import kr.or.fineapple.domain.community.GroupUser;
import kr.or.fineapple.domain.community.Report;

public interface CommunityService {
	
	public int addPost(Board board);
	
	public List<Board> getPostList();
	
	public void updatePostViewCount(Board board);
	
	public void updatePostLike(Board board, int flag);
	
	public void updateCmntLike(Cmnt cmnt, int flag);
	
	public Board getPost(Board board);
	
	public List<Group> getGroupInterGroup(GroupUser groupUser);
	
	public List<User> getGroupInterUser(GroupUser groupUser);
	
	public void addReport(Report report);
	
	public void addGroup(Group group);
}
