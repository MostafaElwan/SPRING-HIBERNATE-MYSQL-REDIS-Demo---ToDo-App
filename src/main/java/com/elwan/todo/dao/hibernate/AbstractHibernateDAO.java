package com.elwan.todo.dao.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class AbstractHibernateDAO {
	
	@Autowired
	protected SessionFactory sessionFactory;

}
