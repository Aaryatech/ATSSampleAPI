package com.ats.sampleapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.ats.sampleapi.common.CommonUtility;
import com.ats.sampleapi.model.Info;
import com.ats.sampleapi.model.User;
import com.ats.sampleapi.repo.UserRepo;

//Author-Sachin Handge
//Created On-08-07-2020
//Modified By-Sachin Handge
//Modified On-08-07-2020
//Desc- All API related to login functionality expected to be here.

@RestController
public class LoginApiController {

	@Autowired
	UserRepo userRepo;

	// Author-Sachin Handge
	// Created On-08-07-2020
	// Modified By-Sachin Handge
	// Modified On-15-07-2020
	// Desc- Check Username and password for Login.
	@RequestMapping(value = { "/checkUserNamePassForLogin" }, method = RequestMethod.POST)
	public @ResponseBody Object checkUserNameForLogin(@RequestParam String userName, @RequestParam String pass) {
		Info info = new Info();
		User loginUser = new User();
		Integer userId = 0;
		System.err.println("Hiooooooo");
		try {

			// Check if user name exists
			// User name should be case in sensitive
			userId = userRepo.getUserId(userName.trim());
			if (userId!=null) {
				try {
					loginUser = userRepo.findByUserId(userId);
				} catch (Exception e) {
					info.setError(true);
				}
				
				if(loginUser!=null) {
					System.err.println("login User " + loginUser.toString());

					if(pass.trim().equals(loginUser.getUserPass().trim())) {
						//password matched.
						info.setError(false);
						info.setMsg("Login Successful ");
						info.setResponseObject1(CommonUtility.toJSONString(loginUser));
						return info;
					}else {
						//password not matched.
						info.setError(true);
						info.setMsg("Invalid password ");
						return info;
					}
				}
			} else {
				info.setError(true);
				info.setMsg("Invalid user name ");
				return info;
			}

		} catch (Exception e) {
			info.setError(true);
			info.setMsg("Exception Occurred  ");
		}
		
		return info;

	}
	
	// Author-Sachin Handge
		// Created On-15-07-2020
		// Modified By-Sachin Handge
		// Modified On-15-07-2020
		// Desc- Check Username for forgot/change password page ie accept user name and return it if available.
	
	@RequestMapping(value = { "/checkUserNameForgotPass" }, method = RequestMethod.POST)
	public @ResponseBody Object checkUserNameForgotPass(@RequestParam String userName) {
		Info info = new Info();
		User loginUser = new User();
		Integer userId = 0;
		try {

			// Check if user name exists
			userId = userRepo.getUserId(userName.trim());

			if (userId > 0) {
				try {
					loginUser = userRepo.getOne(userId);
				} catch (Exception e) {
					info.setError(true);
				}
				
				if(loginUser!=null) {

						info.setError(false);
						info.setMsg("User name found ");
						info.setResponseObject1(CommonUtility.toJSONString(loginUser));
						
						return info;
				}
			}
			 else {
				info.setError(true);
				info.setMsg("Invalid user name ");
				return info;
			}

		} catch (Exception e) {
			info.setError(true);
			info.setMsg("Exception Occurred ");
		}
		
		return info;
		
	}
	
	
	@RequestMapping(value = { "/updateUserPassword" }, method = RequestMethod.POST)
	public @ResponseBody Object updateUserPassword(@RequestParam int userId,
			@RequestParam String userPass,
			@RequestParam int makerUserId,@RequestParam String makerDttime) {

		Info info = new Info();
		int update=0;
		
		try {
			update=userRepo.updatePass(userId, userPass, makerUserId, makerDttime);
			
			if(update>0) {
				info.setError(false);
				info.setMsg("Record updated successfully");
			}else {
				info.setError(true);
				info.setMsg("Record not updated");
			}
			
		}catch (Exception e) {
			info.setError(true);
			info.setMsg("Exception Occurred- Password not changed");
		}
		
		return info;
		
	}
		
}
