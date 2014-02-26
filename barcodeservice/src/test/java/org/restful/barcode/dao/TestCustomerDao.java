package org.restful.barcode.dao;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.restful.barcode.data.Barcode;
import org.restful.barcode.data.BarcodePolicy;
import org.restful.barcode.data.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.google.zxing.BarcodeFormat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/test-context.xml")
@ActiveProfiles("test")
@Transactional
public class TestCustomerDao {

	@Autowired
	private CustomerDao customerDao;
	
	@Test
	public void saveCustomer() {
		Customer customer = new Customer();
		customer.setCustomerId("blahId");
		customer.setName("tescos");
		customer.setEmail("abc@tescos.com");
		
		String customerId = customerDao.save(customer);
		
		List<Customer> allCustomers = customerDao.getAllCustomers();
		
		// 3 Customers that are default in schema_h2.sql
		assertEquals(4, allCustomers.size());
		assertEquals("blahId", customerId);
	}
	
	@Test
	public void saveBarcodesForCustomer() {
		
		Customer customer = new Customer();
		customer.setCustomerId("blahId");
		customer.setName("tescos");
		customer.setEmail("abc@tescos.com");
		
		Barcode barcode = new Barcode();
		barcode.setBarcodeApiKey("barcodeApiKey");
		barcode.setCustomer(customer);
		barcode.setBarcodeFormat(BarcodeFormat.CODE_128.name());
		
		BarcodePolicy barcodePolicy = new BarcodePolicy();
		barcodePolicy.setActive(true);
		barcodePolicy.setBarcode(barcode);
		barcodePolicy.setDomain("S");
		barcodePolicy.setWidth(200);
		barcodePolicy.setHeight(200);
		barcode.setBarcodePolicies(Arrays.asList(barcodePolicy));
		
		customer.setBarcodes(Arrays.asList(barcode));
		
		customerDao.save(customer);
		
		Customer customerFromDB = customerDao.getAllCustomers().get(3);
		assertEquals(1, customerFromDB.getBarcodes().size());
		Barcode barcodeFromDB = customerFromDB.getBarcodes().get(0);
		assertEquals(BarcodeFormat.CODE_128.name(), barcodeFromDB.getBarcodeFormat());
		assertNotNull(barcodeFromDB.getId());
		assertEquals("S", barcodeFromDB.getBarcodePolicies().get(0).getDomain());
	}
}
