package org.restful.barcode.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.restful.barcode.data.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Customer> getAllCustomers() {
		return sessionFactory.getCurrentSession().createQuery("FROM Customer c").list();
	}
}
