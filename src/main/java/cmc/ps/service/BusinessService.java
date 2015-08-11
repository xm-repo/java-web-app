package cmc.ps.service;

import java.util.List;

import cmc.ps.model.Business;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;


/*
 * This is the interface for our Business Service. As a matter of best practice
 * we reference this interface in other components rather than the
 * implementation itself.
 */


public interface BusinessService {

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#save(Object)}
	 */
	public boolean save(Business business);

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#removeById(java.io.Serializable)}
	 */
	public boolean removeById(Integer id);
	
	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#remove(Object)}
	 */
	public boolean remove(Business business);

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#findAll()}
	 */
	public List<Business> findAll();

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#search(ISearch)}
	 */
	public List<Business> search(ISearch search);

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#searchAndCount(ISearch)}
	 */
	public SearchResult<Business> searchAndCount(ISearch search);

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#find(java.io.Serializable)}
	 */
	public Business findById(Integer id);

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#flush()}
	 */
	public void flush();
	
	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#refresh(Object...)}
	 */
	public void refresh(Business business);
}