package kr.or.fineapple.service.community;

import kr.or.fineapple.domain.community.Board;

public interface CommunityService {
	
	public int insertPost(Board board);
	
	public int getPostNo();

}
