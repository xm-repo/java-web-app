package cmc.ps.dao;

import cmc.ps.model.Business;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;


/* 
 * Interface for the Business DAO. This is created very simply by extending
 * GenericDAO and specifying the type for the entity class (Business) and the
 * type of its identifier (Integer).
 */


public interface BusinessDAO extends GenericDAO<Business, Integer> {

}
