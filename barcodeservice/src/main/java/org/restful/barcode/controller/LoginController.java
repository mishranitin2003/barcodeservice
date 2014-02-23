/*
 * The GNU General Public License (GPL-3.0)
 * Copyright 2013-2014 the original author or authors.
 *
 */
package org.restful.barcode.controller;

import javax.servlet.http.HttpServletRequest;

import org.restful.barcode.vo.LoginStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author nmishra
 */
@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value="/adminlogin", method = RequestMethod.GET)
	public ModelAndView login(ModelMap model, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("adminlogin");
		return mv;
	}
	
	@RequestMapping(value="/adminlogin/success", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public LoginStatus loginSuccess(ModelMap model, HttpServletRequest request) {
		LoginStatus loginStatus = new LoginStatus();
		loginStatus.setStatus("success");
		return loginStatus;
	}

	
	@RequestMapping(value="/adminlogin/failed", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public LoginStatus loginFailed(ModelMap model, HttpServletRequest request) {
		LoginStatus loginStatus = new LoginStatus();
		loginStatus.setStatus("failed");
		return loginStatus;
	}
	
	
}
