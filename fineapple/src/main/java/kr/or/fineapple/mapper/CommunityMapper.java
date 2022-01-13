package kr.or.fineapple.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.common.Search;
import kr.or.fineapple.domain.community.Alarm;
import kr.or.fineapple.domain.community.Board;
import kr.or.fineapple.domain.community.Cmnt;
import kr.or.fineapple.domain.community.Group;
import kr.or.fineapple.domain.community.GroupUser;
import kr.or.fineapple.domain.community.MtmQna;
import kr.or.fineapple.domain.community.Report;

@Mapper
@Repository
public interface CommunityMapper {
	
	public void addPost(Board board);
	
	public void addCmnt(Cmnt cmnt);
 	
	public List<Board> getPostList();
	
	public Board getPost(Board board);
	
	public List<Cmnt> getCmntList(Board board);
	
	public List<Cmnt> getCmntListAfterAddCmnt(Cmnt cmnt);
	
	public void updatePostViewCount(Board board);
	
	public void addPostLike(Board board);
	
	public void deletePostLike(Board board);
	
	public void updatePost(Board board);
	
	public void updateCmnt(Cmnt cmnt);
	
	public void addGroup(Group group);
	
	public void addGroupUser(GroupUser groupUser);
	
	public void deleteGroupUser(GroupUser groupUser);
	
	public List<Group> getGroupList();
	
	public Group getGroup(Group group);
	
	public Group checkGroupName(String groupName);
	
	public List<GroupUser> getGroupUserList(Group group);
	
	public List<Group> getMyGroupList(GroupUser groupUser);
	
	public List<Group> getGroupInterGroup(GroupUser groupUser);
	
	public List<User> getGroupInterUser(GroupUser groupUser);
	
	public void addReport(Report report);
	
	public void updateReportStt(Report report);
	
	public List<Report> getReportListNotDispose(Report report);
	
	public List getAlarmList(User user);
	
	public List<User> getUserSearchList(Search search);
	
	public User getUserSearch(Search search); 
	
	public List<Group> getGroupToUserInter(GroupUser groupUser);
	
	public void addGroupToUserInter(GroupUser groupUser);
	
	public List<MtmQna> getFaqList(int cate);
	
	public void delGroupUserInter(HashMap map);
	
	public void updateGroupUserInter(HashMap map);
	
	public void deleteAlarm(Alarm alarm);
	
	public void deleteAlarmAll(Alarm alarm);
	
	public List<Report> getReportListAll(Report report);
	
	public Report getReport(Report report);
	
	public Cmnt getCmnt(Cmnt cmnt);
	
	public User getReportedUser(Report report);
	
	public Board getPostReport(Board board);
	
	public void addMtmQna(MtmQna mtmQna);
	
	

	//Faq
	public void addFaq(MtmQna mtmQna);
	
	public void deleteFaq(int mtmQnaNo);
	
	
	//블랙리스트
	public void addUserBlc(User userId);
	

}
