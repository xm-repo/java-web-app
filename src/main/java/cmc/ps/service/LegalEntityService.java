package cmc.ps.service;

import java.util.List;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;

import cmc.ps.model.LegalEntity;

/*
 * This is the interface for our LegalEntity Service. As a matter of best practice
 * we reference this interface in other components rather than the
 * implementation itself.
 */

public interface LegalEntityService {

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#save(Object)}
	 */
	public boolean save(LegalEntity legalEntity);

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#removeById(java.io.Serializable)}
	 */
	public boolean removeById(Integer id);
	
	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#remove(Object)}
	 */
	public boolean remove(LegalEntity legalEntity);
	
	public void removeAll();

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#findAll()}
	 */
	public List<LegalEntity> findAll();

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#search(ISearch)}
	 */
	public List<LegalEntity> search(ISearch search);

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#searchAndCount(ISearch)}
	 */
	public SearchResult<LegalEntity> searchAndCount(ISearch search);

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#find(java.io.Serializable)}
	 */
	public LegalEntity findById(Integer id);

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#find(java.io.Serializable)}
	 */
	public LegalEntity findByName(String businessName);

	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#flush()}
	 */
	public void flush();
	
	/**
	 * {@link com.googlecode.genericdao.dao.hibernate.GenericDAO#refresh(Object...)}
	 */
	public void refresh(LegalEntity legalEntity);
}