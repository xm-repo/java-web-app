package cmc.ps.dao;

import cmc.ps.model.Owner;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;


/*
 * Interface for the Owner DAO. This is created very simply by extending
 * GenericDAO and specifying the type for the entity class (Owner) and the
 * type of its identifier (Integer).
 */


public interface OwnerDAO extends GenericDAO<Owner, Integer> {

}
