package com.bridgeit.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.dao.IUserDao;
import com.bridgeit.interceptor.Validation;
import com.bridgeit.model.User;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserDao userDao;
	
	@Transactional
	public String addUser(User user, HttpServletRequest req) {
		String getDetails=Validation.userValidation(user.getUsername(),user.getEmail(), user.getPassword());
		return null;
	}

	@Transactional
	public String validateUser(User user) {
		
		return null;
	}

	@Transactional
	public boolean isEmailIdPresent(String emailId) {
		
		List<User> userlist=userDao.checkEmailId(emailId);
		if(userlist.size()!=0)
		{
			return true;
		}
		return  false;
	}

	
}
