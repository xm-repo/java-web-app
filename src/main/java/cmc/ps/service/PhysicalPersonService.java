package cmc.ps.service;

import java.util.List;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;

import cmc.ps.model.PhysicalPerson;

/*
 * This is the interface for our PhysicalPerson Service. As a matter of best practice
 * we reference this interface in other components rather than the
 * implementation itself.
 */

public interface PhysicalPersonService {
	
	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#save(Object)}
	 */
	public boolean save(PhysicalPerson physicalPerson);

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#removeById(java.io.Serializable)}
	 */
	public boolean removeById(Integer id);
	
	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#remove(Object)}
	 */
	public boolean remove(PhysicalPerson physicalPerson);
	
	public void removeAll();

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#findAll()}
	 */
	public List<PhysicalPerson> findAll();

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#search(ISearch)}
	 */
	public List<PhysicalPerson> search(ISearch search);

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#searchAndCount(ISearch)}
	 */
	public SearchResult<PhysicalPerson> searchAndCount(ISearch search);

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#find(java.io.Serializable)}
	 */
	public PhysicalPerson findById(Integer id);

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#find(java.io.Serializable)}
	 */
	public PhysicalPerson findByName(String fullName);

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#flush()}
	 */
	public void flush();
	
	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#refresh(Object...)}
	 */
	public void refresh(PhysicalPerson physicalPerson);
}