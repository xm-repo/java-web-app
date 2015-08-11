package cmc.ps.dao;

import cmc.ps.model.PhysicalPerson;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;


/*
 * Interface for the PhysicalPerson DAO. This is created very simply by extending
 * GenericDAO and specifying the type for the entity class (PhysicalPerson) and the
 * type of its identifier (Integer).
 */


public interface PhysicalPersonDAO extends GenericDAO<PhysicalPerson, Integer> {

}
