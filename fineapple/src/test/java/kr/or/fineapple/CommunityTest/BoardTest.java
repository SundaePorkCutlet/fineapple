package kr.or.fineapple.CommunityTest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.community.Board;
import kr.or.fineapple.domain.community.Group;
import kr.or.fineapple.domain.community.GroupUser;
import kr.or.fineapple.domain.community.MtmQna;
import kr.or.fineapple.domain.community.Report;
import kr.or.fineapple.service.community.CommunityService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class BoardTest {
	
	@Autowired
	@Qualifier("communityServiceImpl")
	private CommunityService communityService;
	
	//@Test
	public void addPost() {
		Board board = new Board();
		User user = new User();
		//communityService.addPost(board);
	}
	
	//@Test
	public void getPostList() {
		List<Board> list = communityService.getPostList();
		for (Board board : list) {
			System.out.println(board);
		}
	}
	
	//@Test
	public void updatePostviewCount() {
		Board board = new Board();
		board.setPostNo(4);
		communityService.updatePostViewCount(board);

	}
	
	//@Test
	public void updatePostLike() {
		
		Board board = new Board();
		
		board.setPostNo(4);
		
	}
	//@Test
	public void groupInterUser() {
		GroupUser groupUser = new GroupUser();
		User user = new User();
		user.setUserId("aaa123@naver.com");
		groupUser.setUser(user);
		groupUser.setGroupStt(2);
		List<User> list = communityService.getGroupInterUser(groupUser);
		for (User user2 : list) {
			System.out.println(user2.getUserId());
		}
	}
	
	//@Test
	public void groupInterGroup() {
		GroupUser groupUser = new GroupUser();
		User user = new User();
		user.setUserId("bbb123@gmil.com");
		groupUser.setUser(user);
		groupUser.setGroupStt(2);
		List<Group> list = communityService.getGroupInterGroup(groupUser);
		for (Group group : list) {
			System.out.println(group);
		}
	}
	
	//@Test
	public void addReport() {
		Report report = new Report();
		User user = new User();
		User reportedUser = new User();
		user.setUserId("aaa123@naver.com");
		reportedUser.setUserId("bbb123@gmil.com");
		report.setReportCate("회원신고");
		report.setUser(user);
		report.setReportedUser(reportedUser);
		Board reportedBoard = new Board();
		reportedBoard.setPostNo(1);
		//report.setReportTrgt(reportedBoard);
		report.setReportCntnt("이 게시글이 매우 이상해요!");
		communityService.addReport(report);	
	}
	
	
	
	//@Test
	public void addGroupToUserInter() {
		User user = new User();
		
		user.setUserId("aaa123@naver.com");
		
		GroupUser groupUser = new GroupUser();
		
		groupUser.setUser(user);
		
		//groupUser.setCaptainStt(1);
		
		groupUser.setGroupStt(1);
		
		List<Group> list = communityService.getGroupToUserInter(groupUser);
		
		for (Group group : list) {
			System.out.println(group);
		}
	}
	
	
	//@Test
	public void testFQL() {
		List<MtmQna> list = communityService.getFaqList(4);
		System.out.println(list);
		for (MtmQna mtmQna : list) {
			System.out.println(mtmQna);
		}
	}
	
	//@Test
	public void TestGetReport() {
		
		Report report = new Report();
		
		report.setReportNo(44);
		
		//Report report2 =  communityService.getReport(report);
		
		//ystem.out.println(report2);
		
		
	}
}
