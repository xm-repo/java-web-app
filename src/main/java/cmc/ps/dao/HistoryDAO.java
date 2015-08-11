package cmc.ps.dao;

import cmc.ps.model.History;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;


/*
 * Interface for the History DAO. This is created very simply by extending
 * GenericDAO and specifying the type for the entity class (History) and the
 * type of its identifier (Integer).
 */


public interface HistoryDAO extends GenericDAO<History, Integer> {
    
}
