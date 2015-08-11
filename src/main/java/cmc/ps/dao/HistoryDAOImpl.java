package cmc.ps.dao;

import org.springframework.stereotype.Repository;

import cmc.ps.model.History;


/* 
 * This is the implementation of the History DAO. You can see that we don't
 * actually have to implement anything, it is all inherited from GenericDAOImpl
 * through BaseDAO. We just specify the entity type (History) and its identifier
 * type (Integer).
 * 
 * The @Repository allows Spring to recognize this as a managed component (so we
 * don't need to specify it in XML) and also tells spring to do DAO exception
 * translation to the Spring exception hierarchy.
 */


@Repository
public class HistoryDAOImpl extends BaseDAO<History, Integer> implements HistoryDAO {

}
