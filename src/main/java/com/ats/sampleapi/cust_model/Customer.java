package com.ats.sampleapi.cust_model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="m_customer")
public class Customer {

	@Id
	private Integer custId;
	
	private String custName;
	private String custMob;
	private Integer creditDays;
	
	private String exVar1;
	private Integer exInt1;
	
	private Integer makerUserId;
	private String makerDttime;
	
	private Integer delStatus;
	private Integer isActive;
	
	public Integer getCustId() {
		return custId;
	}
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustMob() {
		return custMob;
	}
	public void setCustMob(String custMob) {
		this.custMob = custMob;
	}
	public Integer getCreditDays() {
		return creditDays;
	}
	public void setCreditDays(Integer creditDays) {
		this.creditDays = creditDays;
	}
	public String getExVar1() {
		return exVar1;
	}
	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}
	public Integer getExInt1() {
		return exInt1;
	}
	public void setExInt1(Integer exInt1) {
		this.exInt1 = exInt1;
	}
	public Integer getMakerUserId() {
		return makerUserId;
	}
	public void setMakerUserId(Integer makerUserId) {
		this.makerUserId = makerUserId;
	}
	public String getMakerDttime() {
		return makerDttime;
	}
	public void setMakerDttime(String makerDttime) {
		this.makerDttime = makerDttime;
	}
	public Integer getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
	}
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	
	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custName=" + custName + ", custMob=" + custMob + ", creditDays="
				+ creditDays + ", exVar1=" + exVar1 + ", exInt1=" + exInt1 + ", makerUserId=" + makerUserId
				+ ", makerDttime=" + makerDttime + ", delStatus=" + delStatus + ", isActive=" + isActive + "]";
	}
	
	
}
