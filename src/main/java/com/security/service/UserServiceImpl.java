package com.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.dao.AccessRightsDao;
import com.security.dao.UserDao;
import com.security.entity.AccessRights;
import com.security.entity.Users;

/**
 * User Service 
 * @author pranav
 *
 */

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AccessRightsDao accessRightsDao;
	/**
	 * Save user
	 */
	
	public void saveUser(Users user) {

		userDao.saveUser(user);
		
		AccessRights accessRights= new AccessRights();
		accessRights.setAuthority("Admin");
		accessRights.setUser(user);
		
		System.out.println(user.toString());
		
		accessRightsDao.saveAccessRights(accessRights);
	
	}
	
	/**
	 * Get User By Email
	 */

	public Users getByEmail(String email) {
		
		return userDao.getByEmail(email);
	}

	
}
