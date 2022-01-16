package kr.or.fineapple.service.diary;

import java.time.LocalDate;
import java.util.List;

import kr.or.fineapple.domain.Achievement;
import kr.or.fineapple.domain.Badge;
import kr.or.fineapple.domain.UserBodyInfo;
import kr.or.fineapple.domain.UserEvent;
import kr.or.fineapple.domain.UserServ;
import kr.or.fineapple.domain.common.ViewDuration;

public interface DiaryService {
	
	public void addUserEvent(UserEvent userEvent);
	
	public UserEvent getUserEvent(int userEventNo);
	
	public List<Object> getUserEventList(ViewDuration viewDuration);
	
	public void updateUserEvent(UserEvent userEvent);
	
	public void updateKeyUserEventStt(int userEventNo);
	
	public void deleteUserEvent(int userEventNo);
	
	public List<Object> getUserBodyInfoList(ViewDuration viewDuration);
	
	public void updateUserBodyInfo(UserBodyInfo userBodyInfo);
	
	public List<Object> getBadgeList(ViewDuration viewDuration);
	
	public Badge getBadgeTotalCount(ViewDuration viewDuration);
	
	public List<Object> getKeyEventTitleList(ViewDuration viewDuration);

	public UserServ getUserServiceDetails(String userId);
	
	public Achievement getDietAchievement(ViewDuration viewDuration);
	
	public Integer getExerAchievement(ViewDuration viewDuration);
	
	public UserEvent getTheHighestIntakeKcalDayUserEvent(ViewDuration viewDuration);
	
	public LocalDate getTheLatestDateBadge(String userId);
	
	public LocalDate getTheLatestDateUserBodyInfo(String userId);

}
