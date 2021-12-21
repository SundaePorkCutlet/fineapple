package kr.or.fineapple.service.community.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.fineapple.domain.community.Board;
import kr.or.fineapple.domain.community.Cmnt;
import kr.or.fineapple.mapper.CommunityMapper;
import kr.or.fineapple.service.community.CommunityService;



@Service
public class CommunityServiceImpl implements CommunityService {
	
	@Autowired
	private CommunityMapper communityMapper;

	public CommunityServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int insertPost(Board board) {
		// TODO Auto-generated method stub
		System.out.println("½Ã¹ß»õ³¢");
		return communityMapper.insertPost(board);
		

	}

	@Override
	public List<Board> getPostList() {
		// TODO Auto-generated method stub
		return communityMapper.getPostList();
	}
	@Override
	public void updatePostViewCount(Board board) {
		communityMapper.updatePostViewCount(board);
	}

	@Override
	public void updatePostLike(Board board, int flag) {
		
		Map map = new HashMap();
		map.put(board, flag);
		communityMapper.updatePostLike(map);
		
	}

	@Override
	public void updateCmntLike(Cmnt cmnt, int flag) {
		
		Map map = new HashMap();
		
		map.put(cmnt, flag);
		
		communityMapper.updateCmntLike(map);
		
	}
	
	
	
	
	

	


}
