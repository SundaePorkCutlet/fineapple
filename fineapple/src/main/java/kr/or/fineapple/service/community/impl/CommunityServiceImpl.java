package kr.or.fineapple.service.community.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.fineapple.domain.community.Board;
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
	
	public void updatePostViewCount(Board board) {
		communityMapper.updatePostViewCount(board);
	}
	
	

	
	
	

}
