package kr.or.fineapple.domain;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserBodyInfo {

	
	////ȸ������
	String userId;

	////��ü ��ȭ ���� ��ȣ
	int userBodyInfoNo;
	////����
	LocalDate date;
	
	////ȸ���� ü��, ü���淮, ��ݱٷ�
	Double weight;
	Double bodyFat;	//null ��� ���� wrapper class ���
	Double bodyMuscle;	//null ��� ���� wrapper class ���
	Double height;
}
