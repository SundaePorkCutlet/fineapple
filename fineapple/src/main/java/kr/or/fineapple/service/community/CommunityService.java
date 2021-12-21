package kr.or.fineapple.service.community;

import java.util.List;

import kr.or.fineapple.domain.community.Board;

public interface CommunityService {
	
	public int insertPost(Board board);
	
	public List<Board> getPostList();
	
	public void updatePostViewCount(Board board);

}
