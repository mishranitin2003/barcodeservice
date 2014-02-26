/*
 * The GNU General Public License (GPL-3.0)
 * Copyright 2013-2014 the original author or authors.
 *
 */
package org.restful.barcode.controller;

import java.util.List;

import org.restful.barcode.service.CustomerService;
import org.restful.barcode.service.UserService;
import org.restful.barcode.vo.CustomerBarcodesVo;
import org.restful.barcode.vo.CustomerVo;
import org.restful.barcode.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author nmishra
 */
@Controller
public class MainScreenController {

	private static final Logger logger = LoggerFactory.getLogger(MainScreenController.class);

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/customers", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<CustomerVo> getCustomers() {
		return customerService.findAllCustomers();
	}
	
	@RequestMapping(value="/customerBarcodes/{customerId}", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<CustomerBarcodesVo> getCustomerBarcodes(@PathVariable String customerId) {
		return customerService.getCustomerBarcodes(customerId);
	}
	
	@RequestMapping(value="/mainscreen", method = RequestMethod.GET)
	public String toMainScreen() {
		return "mainscreen";
	}
	
	@RequestMapping(value="/loggedinuser/{username}", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public UserVo getLoggedInUser(@PathVariable String username) {
		UserVo user = userService.getUser(username);
		return user;
	}
	
}
