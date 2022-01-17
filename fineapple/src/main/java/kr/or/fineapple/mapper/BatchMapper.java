package kr.or.fineapple.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.or.fineapple.domain.Achievement;
import kr.or.fineapple.domain.Badge;
import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.UserBodyInfo;
import kr.or.fineapple.domain.UserEvent;
import kr.or.fineapple.domain.UserServ;
import kr.or.fineapple.domain.common.ViewDuration;

//@Mapper
//@Repository
public interface BatchMapper {

	////배치프로그램 실행: 매일 전일의 기록을 디폴트로 userBodyInfo 생성
	void addUserBodyInfo(String userId);
	
	////배치프로그램 실행: 매일 뱃지 기록 insert
	void addBadge(Badge defaultBadge);
	
	////다이어리 담당자 하리니가 작성
	public List<User> getAllUsers();
}
