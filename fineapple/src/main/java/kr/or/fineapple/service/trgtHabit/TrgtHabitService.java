package kr.or.fineapple.service.trgtHabit;

import java.time.LocalDate;

import kr.or.fineapple.domain.TrgtHabit;

public interface TrgtHabitService {
	
	public void addTrgtHabit(String userId, TrgtHabit trgtHabit);

	public TrgtHabit getTrgtHabit(String userId, LocalDate date, int trgtCateNo);
	
	public String getUserHabitName(String userId);

	public void endTrgtHabit(int trgtHabitServiceNo);

	public double getWtrIntake(String userId, LocalDate date);	

	public double updateWtrIntake(String userId, double userWtrIntake);
}
