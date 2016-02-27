package com.security.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.security.entity.Users;

/**
 * User Repository
 * @author pranav
 *
 */

@Repository
@Transactional
public class UserDaoImple implements UserDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * Get User By Email
	 */
	
	public Users getByEmail(String email) {
	
		Users user= null;
		try {
			Criteria criteria= sessionFactory.getCurrentSession().createCriteria(Users.class);
			criteria.add(Restrictions.eq("email",email));
			List<Users> users=null;	users= (List<Users>) criteria.list();
			if(users.size()!=0){
				user= users.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
		
		
	}

	/**
	 * Save User
	 */
	
	public void saveUser(Users user) {
		
		sessionFactory.getCurrentSession().save(user);
		
	}



}
