package kr.or.fineapple.service.community.impl;

import org.springframework.beans.factory.annotation.Autowired;

import kr.or.fineapple.domain.community.Board;
import kr.or.fineapple.mapper.CommunityMapper;
import kr.or.fineapple.service.community.CommunityService;



public class CommunityServiceImpl implements CommunityService {
	
	@Autowired
	private CommunityMapper communityMapper;

	public CommunityServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insertPost(Board board) {
		// TODO Auto-generated method stub
		communityMapper.insertPost(board);
		

	}

}
