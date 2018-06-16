package com.bridgeit.dao;

import java.util.List;

import com.bridgeit.model.User;

public interface IUserDao {

	public int addUser(User user);
	public User validateUser(User user);
	/*public Register getUserByEmaiId(String email);
	public Register getUserById(int id);
	public Register getUserByRandomId(String randomId);
	public Register updateRecord(Register user);*/
	List<User> checkEmailId(String emailId);
}
