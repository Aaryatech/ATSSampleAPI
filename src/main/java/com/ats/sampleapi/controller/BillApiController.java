package com.ats.sampleapi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.sampleapi.bill_model.BillDetail;
import com.ats.sampleapi.bill_model.BillHeader;
import com.ats.sampleapi.common.CommonUtility;
import com.ats.sampleapi.cust_model.Customer;
import com.ats.sampleapi.model.Info;
import com.ats.sampleapi.repo.BillDetailRepo;
import com.ats.sampleapi.repo.BillHeaderRepo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class BillApiController {

	@Autowired BillDetailRepo billDetailRepo;
	@Autowired BillHeaderRepo billHeaderRepo;
	
	
	@RequestMapping(value = { "/saveBillHeadDetail" }, method = RequestMethod.POST)
	public @ResponseBody Object saveBillHeadDetail(@RequestBody BillHeader header) {
		//System.err.println("info==" + reqBody.toString());
		Info info = new Info();
		
		try {
			ObjectMapper objMapper = new ObjectMapper();

			//BillHeader billHead = objMapper.readValue(reqBody.getResponseObject1(), BillHeader.class);
			
			/*
			 * List<BillDetail> detailList = null; try { detailList =
			 * objMapper.readValue(reqBody.getResponseObject2(), new
			 * TypeReference<ArrayList<BillDetail>>() { }); } catch (JsonParseException e) {
			 * e.printStackTrace(); } catch (JsonMappingException e) { e.printStackTrace();
			 * } catch (IOException e) { e.printStackTrace(); }
			 */
			
			BillHeader billHeadRes=billHeaderRepo.save(header);
			if(billHeadRes!=null) {
				String message = "Bill Saved Successfully.";

				if (header.getCustId() > 0) {
					message = "Bill Updated Successfully.";
				}
				
				info.setError(false);
				info.setMsg(message);

				String resObj1 = CommonUtility.toJSONString(billHeadRes);
				info.setResponseObject1(resObj1);
			}
			System.err.println("billHeadRes " +billHeadRes.toString());
			System.err.println(" detailList  input " +header.getBillDetailList().toString());
			List<BillDetail> billDetailResList=new ArrayList<BillDetail>();
			//billDetailResList=header.getBillDetailList();
			
			for(int i=0;i<header.getBillDetailList().size();i++) {
				header.getBillDetailList().get(i).setBillId(billHeadRes.getBillId());
				// detail=billDetailRepo.save(header.getBillDetailList().get(i));
				//billDetailResList.add(detail);
			}

			billDetailResList=billDetailRepo.saveAll(header.getBillDetailList());
			System.err.println(" detailList output  " +billDetailResList.toString());
			if(billDetailResList!=null || billDetailResList.isEmpty()==false) {
				String resObj2 = CommonUtility.toJSONString(billDetailResList);
				info.setResponseObject2(resObj2);
			}else {
				info.setError(true);
				info.setMsg("Error Occurred");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			info.setError(true);
			info.setMsg("Exception Occurred");
		}
		return info;
	}
	
	
	
	@RequestMapping(value = { "/getBillList" }, method = RequestMethod.POST)
	public @ResponseBody Object getBillList(@RequestParam String fromDate,@RequestParam String toDate, @RequestParam int custId ) {

		Info info = new Info();
		
		List<BillHeader> billList=new ArrayList<BillHeader>();
		
		try {
			if(custId<1) {
			billList=billHeaderRepo.getBillsByFdTdAllCust(fromDate, toDate);
			}
			else {
				billList=billHeaderRepo.getBillsByFdTdCustId(fromDate, toDate, custId);
			}
				
		}catch (Exception e) {
			info.setError(true);
			info.setMsg("Exception Occurred- Data not loaded");
		}
		
		if(billList.size()<1 || billList.isEmpty() || billList==null) {
			info.setError(true);
			info.setMsg("No data found");
		}else {
			info.setResponseObject1(CommonUtility.toJSONString(billList));
			info.setError(false);
			info.setMsg("Data found");
		}
		
		return info;
	}
	
}
