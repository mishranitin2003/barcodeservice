package org.restful.barcode.service;

import java.util.ArrayList;
import java.util.List;

import org.restful.barcode.dao.CustomerDao;
import org.restful.barcode.data.Barcode;
import org.restful.barcode.data.BarcodePolicy;
import org.restful.barcode.data.Customer;
import org.restful.barcode.vo.CustomerBarcodesVo;
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

	public List<CustomerBarcodesVo> getCustomerBarcodes(String customerId) {
		Customer customer = customerDao.getCustomerById(customerId);
		List<CustomerBarcodesVo> customerBarcodes = new ArrayList<CustomerBarcodesVo>();
		
		if (customer == null) {
			return customerBarcodes; 
		}
		
		List<Barcode> barcodes = customer.getBarcodes();
		for (Barcode barcode : barcodes) {
			List<BarcodePolicy> barcodePolicies = barcode.getBarcodePolicies();
			
			if (barcodePolicies.isEmpty()) {
				CustomerBarcodesVo customerBarcode = new CustomerBarcodesVo();
				customerBarcode.setBarcodeId(barcode.getId());
				customerBarcode.setBarcodeApiKey(barcode.getBarcodeApiKey());
				customerBarcode.setBarcodeFormat(barcode.getBarcodeFormat());
				
				customerBarcodes.add(customerBarcode);
			} 
			
			for (BarcodePolicy barcodePolicy : barcodePolicies) {
				CustomerBarcodesVo customerBarcode = new CustomerBarcodesVo();
				customerBarcode.setBarcodePolicyId(barcodePolicy.getId());
				customerBarcode.setBarcodeApiKey(barcode.getBarcodeApiKey());
				customerBarcode.setBarcodeFormat(barcode.getBarcodeFormat());
				customerBarcode.setDomain(barcodePolicy.getDomain());
				
				customerBarcodes.add(customerBarcode);
			}
		}
		
		return customerBarcodes;
	}
}
