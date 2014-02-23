package org.restful.barcode.service;

import org.restful.barcode.dao.UserDao;
import org.restful.barcode.data.User;
import org.restful.barcode.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public UserVo getUser(String username) {
		UserVo userVo = new UserVo();
		
		User user = userDao.getUser(username);
		if (user != null) {
			BeanUtils.copyProperties(user, userVo);
			return userVo;
		} else {
			return null;
		}
	}
}
