package kr.or.fineapple.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.or.fineapple.domain.community.Board;
import kr.or.fineapple.domain.community.Cmnt;

@Mapper
@Repository
public interface CommunityMapper {
	
	public int addPost(Board board);
	
	public List<Board> getPostList();
	
	public void updatePostViewCount(Board board);
	
	public void updatePostLike(Map map);
	
	public void updateCmntLike(Map map);

}
