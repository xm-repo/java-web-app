package cmc.ps.service;

import java.util.List;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;

import cmc.ps.model.History;


/*
 * This is the interface for our History Service. As a matter of best practice
 * we reference this interface in other components rather than the
 * implementation itself. 
 */


public interface HistoryService {

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#save(Object)}
	 */
	public boolean save(History history);

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#removeById(java.io.Serializable)}
	 */
	public boolean removeById(Integer id);
	
	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#remove(Object)}
	 */
	public boolean remove(History history);

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#findAll()}
	 */
	public List<History> findAll();

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#search(ISearch)}
	 */
	public List<History> search(ISearch search);

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#searchAndCount(ISearch)}
	 */
	public SearchResult<History> searchAndCount(ISearch search);

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#find(java.io.Serializable)}
	 */
	public History findById(Integer id);

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#flush()}
	 */
	public void flush();
	
	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#refresh(Object...)}
	 */
	public void refresh(History history);
}