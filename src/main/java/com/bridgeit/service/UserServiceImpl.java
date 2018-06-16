package com.bridgeit.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.dao.IUserDao;
import com.bridgeit.exception.Tokens;
import com.bridgeit.interceptor.Validation;
import com.bridgeit.model.User;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserDao userDao;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Transactional
	public String addUser(User user, HttpServletRequest req) {
		String getDetails=Validation.userValidation(user.getUsername(),user.getEmail(),user.getPassword());
		
		if(getDetails!=null)
		{
			return getDetails;
		}
		String URL=req.getRequestURI();
		String generataHash=encoder.encode(user.getPassword());
		user.setPassword(generataHash);
		
		int id = userDao.addUser(user);
		System.out.println("My  primary id is:" +id);
		
		String token = Tokens.generateToken(id);
		System.out.println("my Token.... "+token);

		int id1 = Tokens.getId(token);
		System.out.println("My id via JWT token..."+ id);
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
