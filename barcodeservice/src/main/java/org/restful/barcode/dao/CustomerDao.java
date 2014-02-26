package org.restful.barcode.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.restful.barcode.data.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Customer> getAllCustomers() {
		return getSession().createQuery("FROM Customer c").list();
	}

	public Customer getCustomerById(String customerId) {
		return (Customer)getSession().get(Customer.class, customerId);
	}

	public String save(Customer customer) {
		return (String)getSession().save(customer);
	}
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
}
