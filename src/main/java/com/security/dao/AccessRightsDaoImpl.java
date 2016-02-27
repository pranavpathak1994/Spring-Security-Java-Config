package com.security.dao;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.security.entity.AccessRights;

/**
 * AccessRights Repository
 * @author pranav
 *
 */

@Repository
@Transactional
public class AccessRightsDaoImpl implements AccessRightsDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * Save AccessRights
	 */
	
	public void saveAccessRights(AccessRights accessRights) {
		
		sessionFactory.getCurrentSession().save(accessRights);
	}

}
