package kr.or.fineapple.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.or.fineapple.domain.Badge;
import kr.or.fineapple.domain.User;
import kr.or.fineapple.mapper.DiaryMapper;
import kr.or.fineapple.mapper.UserMapper;

//@Component
public class MidnightBatchJob {
	
	//@Autowired
	private DiaryMapper diaryMapper;
	
	//@Autowired
	private UserMapper userMapper;
	
	//@Scheduled(cron="0 * * * * *")
	public void updateUserBodyInfoTask() {
		
		Badge defaultBadge = new Badge();
		List<User> allUserList = userMapper.getAllUsers();
		
		for(User user : allUserList) {
			
			////UserBodyInfo 테이블 로우 추가
			//diaryMapper.addUserBodyInfo(user.getUserId());
			System.out.println(user);
			////Badge 테이블 로우 추가
			if(user.getDietServiceNo() != 0 && user.getExerServiceNo() != 0) {
				//식단, 운동 서비스 모두 이용
				defaultBadge.setUserId(user.getUserId());
				defaultBadge.setDailyBurnningKcal(0.0);
				defaultBadge.setDailyIntakeKcal(0.0);
				diaryMapper.addBadge(defaultBadge);
				System.out.println(defaultBadge);
			} else if(user.getDietServiceNo() == 0 && user.getExerServiceNo() != 0) {
				//식단 서비스 이용
				defaultBadge.setUserId(user.getUserId());
				defaultBadge.setDailyIntakeKcal(0.0);
				diaryMapper.addBadge(defaultBadge);
				System.out.println(defaultBadge);
			} else if(user.getDietServiceNo() != 0 && user.getExerServiceNo() == 0) {
				//운동 서비스만 이용
				defaultBadge.setUserId(user.getUserId());
				defaultBadge.setDailyBurnningKcal(0.0);
				diaryMapper.addBadge(defaultBadge);
				System.out.println(defaultBadge);
			} else {
				//식단, 운동 모두 이용하지 않음
				defaultBadge.setUserId(user.getUserId());
				diaryMapper.addBadge(defaultBadge);
				System.out.println(defaultBadge);
			}
		}
	}
	
}
