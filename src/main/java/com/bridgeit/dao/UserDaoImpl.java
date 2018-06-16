package com.bridgeit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgeit.model.User;

@Repository
public class UserDaoImpl implements IUserDao {

	
	@Autowired
	private SessionFactory Factory;
	
	public int addUser(User user) {
		Session session=Factory.getCurrentSession();
		int id=(Integer)session.save(user);
		return id;
	}

	public User validateUser(User user) {
		
		Session session=Factory.openSession();
		Criteria crite=session.createCriteria(User.class);
		Criterion email=Restrictions.eq("Email", user.getEmail());
		Criterion password=Restrictions.eq("Password", user.getPassword());
		Criterion bothArePresent=Restrictions.and(email,password);
		crite.add(bothArePresent);
		
		User reg=(User) crite.uniqueResult();
		System.out.println(reg.getUsername()+ " "+ reg.getEmail()+ " "+ reg.getPassword());
		
		return reg;
	}

	public List<User> checkEmailId(String email) {
		
		Session session = Factory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));
		List<User> userList = criteria.list();
		return userList;
	}

	
}
