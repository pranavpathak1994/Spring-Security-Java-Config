package com.security.dao;

import com.security.entity.Users;

/**
 * Interface for User Repository
 * @author Pranav
 *
 */

public interface UserDao {
	
	public Users getByEmail(String email);
	
	public void saveUser(Users user);

}
