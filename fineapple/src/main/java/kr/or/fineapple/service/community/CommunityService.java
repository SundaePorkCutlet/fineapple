package kr.or.fineapple.service.community;

import java.util.List;

import kr.or.fineapple.domain.community.Board;
import kr.or.fineapple.domain.community.Cmnt;

public interface CommunityService {
	
	public int addPost(Board board);
	
	public List<Board> getPostList();
	
	public void updatePostViewCount(Board board);
	
	public void updatePostLike(Board board, int flag);
	
	public void updateCmntLike(Cmnt cmnt, int flag);
	
	public Board getPost(Board board);

}
