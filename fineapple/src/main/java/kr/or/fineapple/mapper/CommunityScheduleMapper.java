package kr.or.fineapple.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.or.fineapple.domain.community.Battle;

@Mapper
@Repository
public interface CommunityScheduleMapper {
	
	public List<Battle> getAllBattle();
	
	public Integer getUserBurningRecord(Battle battle);
	
	public Integer getRivalUserBurningRecord(Battle battle);
	
	public Integer getUserIntakeRecord(Battle battle);
	
	public Integer getRivalUserIntakeRecord(Battle battle);
	
	public void updateBattleStt(Battle battle);
	
	public void updateBattleScore(Battle battle);
	
	public Battle getBattle(Battle battle);
	
}
