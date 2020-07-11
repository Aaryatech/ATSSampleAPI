package com.ats.sampleapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.sampleapi.common.CommonUtility;
import com.ats.sampleapi.cust_model.Customer;
import com.ats.sampleapi.model.Info;
import com.ats.sampleapi.model.User;
import com.ats.sampleapi.repo.CustomerRepo;

//Author-Sachin Handge
//Created On-09-07-2020
//Modified By-Sachin Handge
//Modified On-09-07-2020
//Desc- All API related to Customer Master Functionality 

@RestController
public class CustomerApiController {

	@Autowired
	CustomerRepo custRepo;

	@RequestMapping(value = { "/saveCustomerMaster" }, method = RequestMethod.POST)
	public @ResponseBody Object saveCustomerMaster(@RequestBody Customer custBody) {

		Info info = new Info();
		Integer userId = 0;
		
		try {

			// First check that if you have to identify any unique field, If yes do the
			// following.

			Integer recordCount = 0;
			// To check a unique mobile no of Customer Table

			if (custBody.getCustId().equals(0)) {
				// If Primary key custId is 0 means its new Record to be inserted.
				// Query to check uniqueness of record as its new record.
				recordCount = custRepo.getMobileNoCountForNewRecord(custBody.getCustMob());
			}else {
				// Query to check uniqueness of mobile for existing record .
				recordCount = custRepo.getMobileNoCountForExistingRecord(custBody.getCustMob(), custBody.getCustId());
			}
			
			// Check if recordCount is 0 you can save/update record

				if (recordCount.equals(0)) {
					// No record found with given mob no so you can insert/update it.
					
					Customer custSaveRes = null;
					try {
						custSaveRes = custRepo.save(custBody);

					} catch (Exception e) {
						custSaveRes = null;
					}
					if (custSaveRes != null) {

						String message = "Customer Saved Successfully.";

						if (custBody.getCustId() > 0) {
							message = "Customer Updated Successfully.";
						}
						
						info.setError(false);
						info.setMsg(message);

						String resObj1 = CommonUtility.toJSONString(custSaveRes);
						info.setResponseObject1(resObj1);

					} else {
						info.setError(true);
						info.setMsg("Error Occurred");

						String resObj1 = CommonUtility.toJSONString(custBody);
						info.setResponseObject1(resObj1);
					}
				} 

		} catch (Exception e) {
			info.setError(true);
			info.setMsg("Exception Occurred");
		}
		
		return info;

	}
	
	@RequestMapping(value = { "/getAllCustList" }, method = RequestMethod.GET)
	public @ResponseBody Object getAllCustList() {

		Info info = new Info();
		
		List<Customer> custList=new ArrayList<Customer>();
		
		try {
			custList=custRepo.findByDelStatusOrderByCustNameDesc(1);
		}catch (Exception e) {
			info.setError(true);
			info.setMsg("Exception Occurred- Data not loaded");
		}
		
		if(custList.size()<1 || custList.isEmpty() || custList==null) {
			info.setError(true);
			info.setMsg("No data found");
		}else {
			info.setResponseObject1(CommonUtility.toJSONString(custList));
			info.setError(false);
			info.setMsg("Data found");
		}
		
		return info;
	}
	
	
	//Author-Sachin Handge
	//Created On-10-07-2020
	//Modified By-Sachin Handge
	//Modified On-10-07-2020
	//Desc- delete a single customer by its primary key custId
	@RequestMapping(value = { "/deleteCustomerByCustId" }, method = RequestMethod.POST)
	public @ResponseBody Object deleteCustomer(@RequestParam Integer custId,
			@RequestParam Integer makerUserId,@RequestParam String makerDttime) {

		Info info = new Info();
		int delete=0;
		
		try {
			delete=custRepo.deleteCustomer(custId, makerUserId, makerDttime);
			
			if(delete>0) {
				info.setError(false);
				info.setMsg("Record deleted successfully");
			}else {
				info.setError(true);
				info.setMsg("Record not deleted");
			}
			
		}catch (Exception e) {
			info.setError(true);
			info.setMsg("Exception Occurred- Data not deleted");
		}
		
		return info;
		
	}
	
	@RequestMapping(value = { "/getCustomerByCustId" }, method = RequestMethod.POST)
	public @ResponseBody Object getCustomerByCustId(@RequestParam Integer custId) {

		Info info = new Info();
		Customer cust=new Customer();
		try {
			cust=custRepo.findByCustId(custId);
			System.err.println(cust);
			if(cust!=null) {
				info.setError(false);
				info.setMsg("Record found");
				/*
				 * List<Customer> customers=new ArrayList<Customer>(); customers.add(cust);
				 */
				info.setResponseObject1(CommonUtility.toJSONString(cust));
			}else {
				info.setError(true);
				info.setMsg("Record not found");
			}
			
		}catch (Exception e) {
			info.setError(true);
			info.setMsg("Exception Occurred- Data not found");
		}
		
		return info;
		
	}
	
	
}
