package kr.or.fineapple.domain.community;

public class Img {
	
	private int imgNo;
	private String imgName;
	public int getImgNo() {
		return imgNo;
	}
	public void setImgNo(int imgNo) {
		this.imgNo = imgNo;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	@Override
	public String toString() {
		return "Img [imgNo=" + imgNo + ", imgName=" + imgName + "]";
	}
	
	

}
