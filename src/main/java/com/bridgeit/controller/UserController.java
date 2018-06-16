package com.bridgeit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.exception.EmailAlreadyExist;
import com.bridgeit.model.User;
import com.bridgeit.service.IUserService;

@RestController
public class UserController {
	
	@Autowired
	IUserService userservice;

	@RequestMapping(value="/registerUser", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<?> registerUser(@RequestBody User user,HttpServletRequest request)
	{
		try
		{
			if(userservice.isEmailIdPresent(user.getEmail()))
			{
				 	throw new EmailAlreadyExist("email alreading exist ");	 	 
			}
			else {
				if(userservice.addUser(user, request)!=null)
				{
					return new ResponseEntity<String>("Registration failed",HttpStatus.CONFLICT);
				}
			}	
		}
		catch(EmailAlreadyExist eae)
		{
			System.out.println("Email Already Exist exception");
		}
		return new  ResponseEntity<String>("User Registered Successfully",HttpStatus.OK);
	}
	
	
}
