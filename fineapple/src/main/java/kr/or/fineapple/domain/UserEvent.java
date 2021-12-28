package kr.or.fineapple.domain;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserEvent {
	
	////회원정보
	String userId;
	
	////사용자이벤트 관련 정보
	//회원의 사용자이벤트번호(한 회원이 복수의 번호 보유 가능)
	int userEventNo;
	//회원은 하루에 최대 5개의 사용자 이벤트 등록 가능
	LocalDate eventDate;
	//대표이벤트 설정(디폴트는 회원이 처음 등록한 이벤트를 대표이벤트로 설정)
	int keyEventStt;
	//사용자이벤트 제목, 내용
	String eventTitle;
	String eventCntnt;
}
