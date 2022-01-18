package kr.or.fineapple.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.or.fineapple.domain.Achievement;
import kr.or.fineapple.domain.Badge;
import kr.or.fineapple.domain.UserBodyInfo;
import kr.or.fineapple.domain.UserEvent;
import kr.or.fineapple.domain.UserServ;
import kr.or.fineapple.domain.common.ViewDuration;

@Mapper
@Repository
public interface DiaryMapper {
	
	////����� �̺�Ʈ �߰�(1�� �ִ� 5������ ���)
	void addUserEvent(UserEvent userEvent);
	
	////�ش����ڿ� ��ϵ� ����� �̺�Ʈ�� ���� ��ȸ(service���� �־� ViewDuration ��� Map ���)
	int getUserEventCount(Map map);
	
	////����� �̺�Ʈ ��ȸ
	UserEvent getUserEvent(int userEventNo);
	
	////����� �̺�Ʈ ��� ��ȸ
	List<Object> getUserEventList(ViewDuration viewDuration);
	
	////����� �̺�Ʈ ����
	void updateUserEvent(UserEvent userEvent);
	
	////��ǥ ����� �̺�Ʈ ������ ���� ��ǥ�̺�Ʈ ��� ��� ����(service���� ����)
	void updatePreKeyUserEventStt(int userEventNo);
	
	////��ǥ ����� �̺�Ʈ ����
	void updateKeyUserEventStt(int userEventNo);
	
	////����� �̺�Ʈ ����
	void deleteUserEvent(int userEventNo);
	
	////����� ��ü ��ȭ ���� ��ȸ
	List<Object> getUserBodyInfoList(ViewDuration viewDuration);
	
	////����� ��ü ��ȭ ���� ��� �� ����(����Ʈ�� �ֽ� ��ġ �����̹Ƿ� ����ڴ� add���� ����)
	void updateUserBodyInfo(UserBodyInfo userBodyInfo);
	
	////���̾ ȭ�� ����� ���� ���� ����Ʈ ��ȸ
	List<Object> getBadgeList(ViewDuration viewDuration);
	
	////�Ⱓ �� �� ���� ���� ��ȸ
	Badge getBadgeTotalCount(ViewDuration viewDuration);
	
	////���̾ ȭ�� ����� ���� �Ⱓ �� ��ǥ �̺�Ʈ ���� ����Ʈ ��ȸ
	List<Object> getKeyEventTitleList(ViewDuration viewDuration);

	////ȸ���� �Ĵ� ����/� ���� ��ǥ ���� ��ȸ
	UserServ getUserServiceDetails(String userId);
	
	////ȸ���� �Ĵ� ���� ��ǥ �޼��� ����� ��ȸ
	Achievement getDietAchievement(ViewDuration viewDuration);
	
	////ȸ���� � ���� ��ǥ �޼��� ����� ��ȸ
	Integer getExerAchievement(ViewDuration viewDuration);
	
	////�Ⱓ �� ���� ���� Į�θ��� ū ���� �� ���� ��ǥ �̺�Ʈ ��ȸ
	UserEvent getTheHighestIntakeKcalDayUserEvent(ViewDuration viewDuration);

	////���� ���̺� ��ϵ� ���� ������ ��¥ ��ȸ
	LocalDate getTheLatestDateBadge(String userId);
	
	////����� ��ü ���� ���̺� ��ϵ� ���� ������ ��¥ ��ȸ
	LocalDate getTheLatestDateUserBodyInfo(String userId);
	
	////���� ���̺� ��� ����ȭ(�Ĵ�)
	void updateBadgeByDiet(Map map);

	////���� ���̺� ��� ����ȭ(�)
	void updateBadgeByExer(Map map);
	
	////��ġ���α׷� ����: ���� ������ ����� ����Ʈ�� userBodyInfo ����
	void addUserBodyInfo(Map map);
	
	////��ġ���α׷� ����: ���� ���� ��� insert
	void addBadge(Badge defaultBadge);
	
}
