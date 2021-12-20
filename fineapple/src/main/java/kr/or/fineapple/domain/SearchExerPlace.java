package kr.or.fineapple.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchExerPlace {

	
	int userLocationX;
	int userLocationY;
	String exerPlaceAddr;
	String parkName;
	String exerPlaceCntnt;
	String parkCntnt;
	int exerLocationX;
	int exerLocationY;
	String parkAddr;

	public SearchExerPlace() {
		
		
	}
}
