package com.ats.sampleapi.bill_model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

//Author-Sachin Handge
//Created On-13-07-2020
//Modified By-Sachin Handge
//Modified On-13-07-2020

@Entity
@Table(name="t_bill_header")
public class BillHeader {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int billId;
	private String invoiceNo;
	private Integer custId;
	
	private Date billDate;
	private String billDttime;
	private String billUpdateDttime;
	
	private Integer billAmt;

	private Integer makerUserId;
    private String makerDttime;
	
	private Integer delStatus;
	
	private String exVar1;
	private int exInt1;
	
	@Transient 
	List<BillDetail> billDetailList;
	
	
	
	
	public List<BillDetail> getBillDetailList() {
		return billDetailList;
	}
	public void setBillDetailList(List<BillDetail> billDetailList) {
		this.billDetailList = billDetailList;
	}
	public int getBillId() {
		return billId;
	}
	public void setBillId(int billId) {
		this.billId = billId;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public Integer getCustId() {
		return custId;
	}
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getBillDate() {
		return billDate;
	}
	
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	
	public String getBillDttime() {
		return billDttime;
	}
	public void setBillDttime(String billDttime) {
		this.billDttime = billDttime;
	}
	public String getBillUpdateDttime() {
		return billUpdateDttime;
	}
	public void setBillUpdateDttime(String billUpdateDttime) {
		this.billUpdateDttime = billUpdateDttime;
	}
	public Integer getBillAmt() {
		return billAmt;
	}
	public void setBillAmt(Integer billAmt) {
		this.billAmt = billAmt;
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
	public String getExVar1() {
		return exVar1;
	}
	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}
	public int getExInt1() {
		return exInt1;
	}
	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}
	@Override
	public String toString() {
		return "BillHeader [billId=" + billId + ", invoiceNo=" + invoiceNo + ", custId=" + custId + ", billDate="
				+ billDate + ", billDttime=" + billDttime + ", billUpdateDttime=" + billUpdateDttime + ", billAmt="
				+ billAmt + ", makerUserId=" + makerUserId + ", makerDttime=" + makerDttime + ", delStatus=" + delStatus
				+ ", exVar1=" + exVar1 + ", exInt1=" + exInt1 + ", billDetailList=" + billDetailList + "]";
	}
	
	
	
}
