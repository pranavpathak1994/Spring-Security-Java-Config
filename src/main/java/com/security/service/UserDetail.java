package com.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.dao.UserDao;
import com.security.entity.AccessRights;
import com.security.entity.Users;

/**
 * UserDetail Service for spring security
 * @author Pranav
 *
 */

@Service
public class UserDetail implements UserDetailsService{

	
	@Autowired
	private UserDao userDao;
	
	/**
	 * Load user to validate authentication in spring security
	 */
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDetails loadedUser;
		 
		Users user= userDao.getByEmail(username);
		
		loadedUser= new User(user.getEmail(),user.getPassword(),user.getRoleList());
		
		for (AccessRights	access	: user.getRoleList()) {
			System.out.println(access.getAuthority());
		}
		
		return loadedUser;
	}

	
}
