package kr.or.fineapple.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
	String userId;
	String userName;
	String password;
	String gender;
	String cellphone;
	String userImg;
	String role; 
	int blcRgsStt;
	int userLeaveStt;
	Double height;
	Double age;
	LocalDate regDate;
	Double strdWtrIntake;
	String userSttMsg;
	LocalDateTime lastConTime;
	LocalDate blcAddDate;
	String blcAddWhy;
	int dietServiceNo;
	int exerServiceNo;
	Double trgtWeight;
	Double trgtBodyFat;
	Double trgtBodyMuscle;
	String serviceTrgt;
	
	public User() {
		
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getBlcRgsStt() {
		return blcRgsStt;
	}

	public void setBlcRgsStt(int blcRgsStt) {
		this.blcRgsStt = blcRgsStt;
	}

	public int getUserLeaveStt() {
		return userLeaveStt;
	}

	public void setUserLeaveStt(int userLeaveStt) {
		this.userLeaveStt = userLeaveStt;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getAge() {
		return age;
	}

	public void setAge(Double age) {
		this.age = age;
	}

	public LocalDate getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}

	public Double getStrdWtrIntake() {
		return strdWtrIntake;
	}

	public void setStrdWtrIntake(Double strdWtrIntake) {
		this.strdWtrIntake = strdWtrIntake;
	}

	public String getUserSttMsg() {
		return userSttMsg;
	}

	public void setUserSttMsg(String userSttMsg) {
		this.userSttMsg = userSttMsg;
	}

	public LocalDateTime getLastConTime() {
		return lastConTime;
	}

	public void setLastConTime(LocalDateTime lastConTime) {
		this.lastConTime = lastConTime;
	}

	public LocalDate getBlcAddDate() {
		return blcAddDate;
	}

	public void setBlcAddDate(LocalDate blcAddDate) {
		this.blcAddDate = blcAddDate;
	}

	public String getBlcAddWhy() {
		return blcAddWhy;
	}

	public void setBlcAddWhy(String blcAddWhy) {
		this.blcAddWhy = blcAddWhy;
	}

	public int getDietServiceNo() {
		return dietServiceNo;
	}

	public void setDietServiceNo(int dietServiceNo) {
		this.dietServiceNo = dietServiceNo;
	}

	public int getExerServiceNo() {
		return exerServiceNo;
	}

	public void setExerServiceNo(int exerServiceNo) {
		this.exerServiceNo = exerServiceNo;
	}

	public Double getTrgtWeight() {
		return trgtWeight;
	}

	public void setTrgtWeight(Double trgtWeight) {
		this.trgtWeight = trgtWeight;
	}

	public Double getTrgtBodyFat() {
		return trgtBodyFat;
	}

	public void setTrgtBodyFat(Double trgtBodyFat) {
		this.trgtBodyFat = trgtBodyFat;
	}

	public Double getTrgtBodyMuscle() {
		return trgtBodyMuscle;
	}

	public void setTrgtBodyMuscle(Double trgtBodyMuscle) {
		this.trgtBodyMuscle = trgtBodyMuscle;
	}

	public String getServiceTrgt() {
		return serviceTrgt;
	}

	public void setServiceTrgt(String serviceTrgt) {
		this.serviceTrgt = serviceTrgt;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", gender=" + gender
				+ ", cellphone=" + cellphone + ", userImg=" + userImg + ", role=" + role + ", blcRgsStt=" + blcRgsStt
				+ ", userLeaveStt=" + userLeaveStt + ", height=" + height + ", age=" + age + ", regDate=" + regDate
				+ ", strdWtrIntake=" + strdWtrIntake + ", userSttMsg=" + userSttMsg + ", lastConTime=" + lastConTime
				+ ", blcAddDate=" + blcAddDate + ", blcAddWhy=" + blcAddWhy + ", dietServiceNo=" + dietServiceNo
				+ ", exerServiceNo=" + exerServiceNo + ", trgtWeight=" + trgtWeight + ", trgtBodyFat=" + trgtBodyFat
				+ ", trgtBodyMuscle=" + trgtBodyMuscle + ", serviceTrgt=" + serviceTrgt + "]";
	}

	
	
}



