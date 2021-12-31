package kr.or.fineapple.domain.common;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ViewDuration {
	
	private String userId;
	private LocalDate date;
	private LocalDate startDate;
	private LocalDate endDate;
}
