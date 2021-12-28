package kr.or.fineapple.service.diary;

import java.time.LocalDate;
import java.util.Map;

import kr.or.fineapple.domain.Diary;
import kr.or.fineapple.domain.UserBodyInfo;
import kr.or.fineapple.domain.UserEvent;

public interface DiaryService {

	public Diary getDiary(String userId, LocalDate startDate, LocalDate endDate);
	
	public void addUserEvent(UserEvent userEvent);
	
	public UserEvent getUserEvent(int userEventNo);
	
	public Map<String, Object> getUserEventList(String userId, LocalDate eventDate);
	
	public void updateUserEvent(UserEvent userEvent);
	
	public void updateKeyUserEventStt(int userEventNo);
	
	public void deleteUserEvent(int userEventNo);
	
	public Map<String, Object> getUserBodyInfoList(String userId, LocalDate startDate, LocalDate endDate);
	
	public void updateUserBodyInfo(UserBodyInfo userBodyInfo);

}
