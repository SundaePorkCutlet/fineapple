package kr.or.fineapple.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.or.fineapple.domain.TrgtHabit;
import kr.or.fineapple.domain.common.ViewDuration;

@Mapper
@Repository
public interface TrgtHabitMapper {

	////��ǥ ���� ���� ����
	void addTrgtHabit(TrgtHabit trgtHabit);
	
	////���̾ ȭ�� ����� ���� �������� ���� ��ȸ
	List<Object> getTrgtHabitList(ViewDuration viewDuration);
	
	////�ش� ���� �̹� ���������� ���� ��ȸ
	int getUsingTrgtHabit(Map map);
	
	////���� �ֽ��� ���� �ʱ�ȭ ���� ��ȸ
	LocalDate getTheLatestInitDate(Map map);
	
	////��ǥ ���� ���� ���� Ȯ��
	TrgtHabit getTrgtHabit(Map map);
	
	////��������� ��ǥ������ ��ȸ
	String getUserHabitName(String userId);
	
	////��ǥ �ʱ�ȭ
	void endTrgtHabit(int trgtHabitServiceNo);
	
	////���� ���뷮 ��� ����(��ȸ ���� ��� ������ serviceImpl�� get�޼ҵ� ������ ������)
	void addWtrIntake(String userId);

	////���� ���뷮 ��ȸ
	Double getWtrIntake(Map map);
	
	////���� ���뷮 ��� ����
	void updateWtrIntake(Map map);
}
