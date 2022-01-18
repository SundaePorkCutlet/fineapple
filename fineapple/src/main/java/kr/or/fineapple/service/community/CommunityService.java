package kr.or.fineapple.service.community;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.common.Search;
import kr.or.fineapple.domain.community.Alarm;
import kr.or.fineapple.domain.community.Battle;
import kr.or.fineapple.domain.community.Board;
import kr.or.fineapple.domain.community.Cmnt;
import kr.or.fineapple.domain.community.Group;
import kr.or.fineapple.domain.community.GroupBorad;
import kr.or.fineapple.domain.community.GroupUser;
import kr.or.fineapple.domain.community.MtmQna;
import kr.or.fineapple.domain.community.Report;

public interface CommunityService {
	
	public void addPost(Board board, String[] times);
	
	public Map addCmnt(Cmnt cmnt);
	
	public List<Board> getPostList();
	
	public Map getPost(Board board);
	
	public void updatePostViewCount(Board board);
	
	public Board updatePostLike(Board board);
	
	public void updateCmntLike(Cmnt cmnt);
	
	public void delPost(Board board);
	
	public List<Group> getGroupInterGroup(GroupUser groupUser);
	
	public List<User> getGroupInterUser(GroupUser groupUser);
	
	public void addReport(Report report);
	
	public void addGroup(Group group, GroupUser groupUser);
	
	public Group checkGroupName(String groupName);
	
	public List<User> getUserSearchList(Search search);
	
	public User getUserSearch(Search search); 
	
	public List<Group> getGroupToUserInter(GroupUser groupUser);
	
	public void addGroupToUserInter(GroupUser groupUser);
	
	public List<MtmQna> getFaqList(int cate);
	
	public void delGroupUserInter(HashMap map, int intetStt);
		
	public List getAlarmList(User user);
	
	public void deleteAlarm(Alarm alarm);
	
	public void deleteAlarmAll(Alarm alarm);
	
	public List<Report> getReportListAll(Report report);
	
	public List<Report> getReportListNotDispose(Report report);
	
	public Report getReport(Report report, User user);
	
	public void addMtmQna(MtmQna mtmQna);
	
	public void addFqa(MtmQna mtmQna);
	
	public void deleteFaq(int mtmQnaNo);

	public void addUserBlc(User userId);

	public void updateReportStt(Report report);
	
	public User getUserBattle(User user);
	
	public void addBattleInter(Battle battle, String rivalUserId);
	
	public List<Battle> getMybattleInter(Battle battle);
	
	public List<MtmQna> getMyMtmList(User user);
	
	public Board updatePostView (Board board);
	
	public Group getGroup(Group group);
	
	public List<Group> getGroupList();
	
	public List getGroupBoard(GroupBorad groupBorad);
	
	
	
}
