package kr.or.fineapple.domain.community;

import java.time.LocalDate;

import kr.or.fineapple.domain.User;

public class Board {
	private int postNo;
	private User user;
	private String title;
	private String content;
	private LocalDate postDate;
	private int viewCount;
	private int cmntCount;
	private int postLikeCount;
	private Boolean updateStt;
	private int cateName;
	private String imgName;
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDate getPostDate() {
		return postDate;
	}
	public void setPostDate(LocalDate postDate) {
		this.postDate = postDate;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public int getCmntCount() {
		return cmntCount;
	}
	public void setCmntCount(int cmntCount) {
		this.cmntCount = cmntCount;
	}
	public int getPostLikeCount() {
		return postLikeCount;
	}
	public void setPostLikeCount(int postLikeCount) {
		this.postLikeCount = postLikeCount;
	}
	public Boolean getUpdateStt() {
		return updateStt;
	}
	public void setUpdateStt(Boolean updateStt) {
		this.updateStt = updateStt;
	}
	public int getCateName() {
		return cateName;
	}
	public void setCateName(int cateName) {
		this.cateName = cateName;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	@Override
	public String toString() {
		return "Board [postNo=" + postNo + ", user=" + user + ", title=" + title + ", content=" + content
				+ ", postDate=" + postDate + ", viewCount=" + viewCount + ", cmntCount=" + cmntCount
				+ ", postLikeCount=" + postLikeCount + ", updateStt=" + updateStt + ", cateName=" + cateName
				+ ", imgName=" + imgName + "]";
	}
	
	
}
