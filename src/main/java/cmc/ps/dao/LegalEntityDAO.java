package cmc.ps.dao;

import cmc.ps.model.LegalEntity;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;


/*
 * Interface for the LegalEntity DAO. This is created very simply by extending
 * GenericDAO and specifying the type for the entity class (LegalEntity) and the
 * type of its identifier (Integer).
 */


public interface LegalEntityDAO extends GenericDAO<LegalEntity, Integer> {

}
