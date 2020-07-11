package com.ats.sampleapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.ats.sampleapi.model.User;
import com.ats.sampleapi.repo.UserRepo;


//Author-Sachin Handge
//Created On-08-07-2020
//Modified By-Sachin Handge
//Modified On-08-07-2020
//Desc- All API related to login functionality expected to be here.

@RestController
public class LoginApiController {

	@Autowired UserRepo userRepo;
	
	
	//Author-Sachin Handge
	//Created On-08-07-2020
	//Modified By-Sachin Handge
	//Modified On-08-07-2020
	//Desc- Check Username and password for Login.
	@RequestMapping(value = { "/checkUserNameForLogin" }, method = RequestMethod.POST)
	public @ResponseBody Object checkUserNameForLogin(@RequestParam String userName,@RequestParam String pass) {
		
		User user=new User(1,"66666");
		Integer userId=0;
		try {
		
		    //Check if user name exists 
			//User name should be case in sensitive
			userId=	userRepo.getUserId(userName.trim());
		 
		if(userId>0){
			
			User loginUser=userRepo.getOne(userId);
			System.err.println("login User " +loginUser.toString());
			
		}
		
		}catch (Exception e) {
			userId=0;
		}
		return userId;
		
	}

		
}
