package kr.or.fineapple.service.trgtHabit;

import java.time.LocalDate;
import java.util.List;

import kr.or.fineapple.domain.TrgtHabit;
import kr.or.fineapple.domain.common.ViewDuration;

public interface TrgtHabitService {
	
	public void addTrgtHabit(String userId, TrgtHabit trgtHabit);
	
	public List<Object> getTrgtHabitList(ViewDuration viewDuration);
	
	public int getUsingTrgtHabit(String userId, int trgtHabitCateNo);
	
	public LocalDate getTheLatestInitDate(String userId, int trgtHabitCateNo);

	public TrgtHabit getTrgtHabit(String userId, LocalDate date, int trgtHabitCateNo);
	
	public String getUserHabitName(String userId);

	public void endTrgtHabit(int trgtHabitServiceNo);

	public double getWtrIntake(String userId, LocalDate date);	

	public double updateWtrIntake(String userId, double userWtrIntake);

}
