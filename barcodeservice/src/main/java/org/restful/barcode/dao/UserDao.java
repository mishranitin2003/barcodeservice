package org.restful.barcode.dao;

import org.hibernate.SessionFactory;
import org.restful.barcode.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public User getUser(String username) {
		return (User)sessionFactory.getCurrentSession().createQuery("FROM User u WHERE u.username = ?")
				.setString(0, username)
				.uniqueResult();
	}
}
