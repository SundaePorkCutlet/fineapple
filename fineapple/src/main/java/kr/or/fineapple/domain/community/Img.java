package kr.or.fineapple.domain.community;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Img {
	
	private int imgNo;
	private String imgName;
	private int postNo;

	
	public Img() {
		System.out.println("IMG »ý¼º");
	}	

}

