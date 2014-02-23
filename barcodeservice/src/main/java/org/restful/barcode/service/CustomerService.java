package org.restful.barcode.service;

import java.util.ArrayList;
import java.util.List;

import org.restful.barcode.dao.CustomerDao;
import org.restful.barcode.data.Customer;
import org.restful.barcode.vo.CustomerVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	public List<CustomerVo> findAllCustomers() {
		List<CustomerVo> customers = new ArrayList<CustomerVo>();
		
		List<Customer> allCustomers = customerDao.getAllCustomers();
		for (Customer customer : allCustomers) {
			CustomerVo customerVo = new CustomerVo();
			BeanUtils.copyProperties(customer, customerVo);
			customers.add(customerVo);
		}
		
		return customers;
	}
}
