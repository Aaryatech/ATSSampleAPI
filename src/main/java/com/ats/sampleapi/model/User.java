package com.ats.sampleapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="m_user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int userId;
	
	private String userName;
	
	private String userPass;
	
	private int empId;
	private String userMobNo;
	private String userEmail;
	
	private int isEnrolled;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getUserMobNo() {
		return userMobNo;
	}

	public void setUserMobNo(String userMobNo) {
		this.userMobNo = userMobNo;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public int getIsEnrolled() {
		return isEnrolled;
	}

	public void setIsEnrolled(int isEnrolled) {
		this.isEnrolled = isEnrolled;
	}

	public User(int userId, String userMobNo) {
	
		this.userId = userId;
		this.userMobNo = userMobNo;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPass=" + userPass + ", empId=" + empId
				+ ", userMobNo=" + userMobNo + ", userEmail=" + userEmail + ", isEnrolled=" + isEnrolled + "]";
	}
	
	
	

	
	
}
