package cmc.ps.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;


/*
 * We have this base class for our GenericDAOs so that we don't have to override
 * and autowire the sessionFactory property for each one. That is the only
 * reason for having this class. The @Autowired annotation tells Spring to inject the 
 * sessionFactory bean into this setter method.
 */


public class BaseDAO<T, ID extends Serializable> extends GenericDAOImpl<T, ID> {

	@Autowired
	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public Session getSession() {
		return super.getSession();
	}
}
