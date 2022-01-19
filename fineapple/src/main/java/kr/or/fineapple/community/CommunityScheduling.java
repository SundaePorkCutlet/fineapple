package kr.or.fineapple.community;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.or.fineapple.domain.community.Battle;
import kr.or.fineapple.mapper.CommunityScheduleMapper;

@Component
public class CommunityScheduling {
	
	@Autowired
	private CommunityScheduleMapper mapper;
	
	@Scheduled(cron = "* * * * * *") 
	   public void updateBattle() {
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	      Date now = new Date();
	      String strDate = sdf.format(now);
	      System.out.println("Java cron job expression:: " + strDate);
	      
	      List<Battle> list = mapper.getAllBattle();
	      for (Battle battle : list) {
	    	  
	    	  
	    	if (battle.getBattleItemCate() == 1) {
	    		
	    		
	    		Integer resultUser = mapper.getUserBurningRecord(battle);
				
				//System.out.println(resultUser);
			
				Integer resultRivalUser = mapper.getRivalUserBurningRecord(battle);
				
				//System.out.println(resultRivalUser);
				
				int resultUserInt = 0;
				
				int resultRivalUserInt = 0;
				
				if (resultUser != null ) {
					resultUserInt = resultUser;
				}
				if (resultRivalUser != null ) {
					resultRivalUserInt = resultRivalUser;
				}
				
				System.out.println(resultUserInt);
				
				System.out.println(resultRivalUserInt);

				int userScore = 0;
				int rivalUserScore = 0;
				
				
				if (resultUserInt <= battle.getUserTrgtKcal()) {
					userScore = 1;
				}
				
				if (resultRivalUserInt <= battle.getRivalTrgtKcal()) {
					rivalUserScore = 1;
				}
				
				battle.setUserScore(userScore);

				battle.setRivalUserScore(rivalUserScore);
			}
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	if (battle.getBattleItemCate() == 2) {
	    		Integer resultUser = mapper.getUserIntakeRecord(battle);
				
				//System.out.println(resultUser1);
			
				Integer resultRivalUser = mapper.getRivalUserIntakeRecord(battle);
				
				//System.out.println(resultRivalUser1);
				
				int resultUserInt = 0;
				
				int resultRivalUserInt = 0;
				
				if (resultUser != null ) {
					resultUserInt = resultUser;
				};
				if (resultRivalUser != null ) {
					resultRivalUserInt = resultRivalUser;
				}
				
				
				System.out.println(resultUserInt);
				
				System.out.println(resultRivalUserInt);
				
				int userScore = 0;
				int rivalUserScore = 0;
				
				
				if (resultUserInt >= battle.getUserTrgtKcal()) {
					userScore = 1;
				}
				
				if (resultRivalUserInt >= battle.getRivalTrgtKcal()) {
					rivalUserScore = 1;
				}
				
				
				battle.setUserScore(userScore);
				
				battle.setRivalUserScore(rivalUserScore);
				
			}
	    	  

			
			System.out.println(battle);
			
			String date = battle.getBattleEndDate().toString();
			
			System.out.println(date);
			
			String sysdate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			
			if (sysdate.equals(date)) {
				System.out.println("πË∆≤≥°~~~~!!!!");
				mapper.updateBattleStt(battle);
				
			}
			
			/////////////////////////////////////////////////////////////////////
			
		}
	      
	      
	      
	   }
}
