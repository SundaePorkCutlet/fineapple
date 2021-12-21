package kr.or.fineapple.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.or.fineapple.domain.community.Board;

@Mapper
@Repository
public interface CommunityMapper {
	
	public int insertPost(Board board);
	
	public List<Board> getPostList();
	
	public void updatePostViewCount(Board board);

}
