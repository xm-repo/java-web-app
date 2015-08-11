package cmc.ps.service;

import java.util.List;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;

import cmc.ps.model.Owner;

/*
 * This is the interface for our Owner Service. As a matter of best practice
 * we reference this interface in other components rather than the
 * implementation itself. 
 */

public interface OwnerService {

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#save(Object)}
	 */
	public boolean save(Owner owner);

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#removeById(java.io.Serializable)}
	 */
	public boolean removeById(Integer id);
	
	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#remove(Object)}
	 */
	public boolean remove(Owner owner);

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#findAll()}
	 */
	public List<Owner> findAll();

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#search(ISearch)}
	 */
	public List<Owner> search(ISearch search);

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#searchAndCount(ISearch)}
	 */
	public SearchResult<Owner> searchAndCount(ISearch search);

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#find(java.io.Serializable)}
	 */
	public Owner findById(Integer id);

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#flush()}
	 */
	public void flush();
	
	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#refresh(Object...)}
	 */
	public void refresh(Owner owner);
	
	public List<Owner> findLEOwners();
	
	public List<Owner> findPPOwners();
	
	public Owner findEqual(Owner owner);
}