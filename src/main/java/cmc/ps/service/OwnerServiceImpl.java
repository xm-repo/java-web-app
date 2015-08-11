package cmc.ps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cmc.ps.dao.LegalEntityDAO;
import cmc.ps.dao.OwnerDAO;
import cmc.ps.dao.PhysicalPersonDAO;
import cmc.ps.model.Owner;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

/*
 * This is the implementation for our Owner Service. The @Service annotation
 * allows Spring to automatically detect this as a component rather than having
 * to configure it in XML. The @Autowired annotation tells Spring to inject our
 * Owner DAO using the setDao() method.
 */

@Service
@Transactional
public class OwnerServiceImpl implements OwnerService {

	OwnerDAO dao;
	LegalEntityDAO leDao;
	PhysicalPersonDAO ppDao;

	@Autowired
	public void setDao(OwnerDAO dao) {
		this.dao = dao;
	}
	
	@Autowired
	public void setleDao(LegalEntityDAO leDao) {
		this.leDao = leDao;
	}
	
	@Autowired
	public void setppDao(PhysicalPersonDAO ppDao) {
		this.ppDao = ppDao;
	}

	public boolean save(Owner owner) {
		return dao.save(owner);
	}

	public List<Owner> findAll() {
		return dao.findAll();
	}

	public void flush() {
		dao.flush();
	}

	public List<Owner> search(ISearch search) {
		return dao.search(search);
	}

	public Owner findById(Integer id) {
		return dao.find(id);
	}

	public boolean removeById(Integer id) {
		return dao.removeById(id);
	}
	
	public boolean remove(Owner owner) {
		return dao.remove(owner);
	}

	public SearchResult<Owner> searchAndCount(ISearch search) {
		return dao.searchAndCount(search);
	}

	@Override
	public void refresh(Owner owner) {
		dao.refresh(owner);
	}

	@Override
	public List<Owner> findPPOwners() {
		
		Search search = new Search();
		search.setSearchClass(Owner.class);
		search.addFilterNull("legalEntity2");
		return dao.search(search);
	}

	@Override
	public List<Owner> findLEOwners() {
		
		Search search = new Search();
		search.setSearchClass(Owner.class);
		search.addFilterNull("physicalPerson");
		return dao.search(search);
	}

	@Override
	public Owner findEqual(Owner owner) {
		
		if(owner.getLegalEntity1() != null) {
			leDao.refresh(owner.getLegalEntity1());
		}		
		if(owner.getLegalEntity2() != null) {
			leDao.refresh(owner.getLegalEntity2());
		}
		if(owner.getPhysicalPerson() != null) {
			ppDao.refresh(owner.getPhysicalPerson());
		}
		
		Search search = new Search();
		search.setSearchClass(Owner.class);
		search.addFilterEqual("physicalPerson", owner.getPhysicalPerson());
		search.addFilterEqual("legalEntity1", owner.getLegalEntity1());
		search.addFilterEqual("legalEntity2", owner.getLegalEntity2());
		
		List<Owner> l = dao.search(search);
		//int property = 0;
		if(!l.isEmpty()) {
			
			Owner owner2 = l.get(0);
			return owner2;
			//property = owner2.getProperty();
			
			//owner.setId(owner2.getId());
			//owner2.setLegalEntity1(owner.getLegalEntity1());
			//owner2.setLegalEntity2(owner.getLegalEntity2());
			//owner2.setPhysicalPerson(owner.getPhysicalPerson());
			//owner2.setProperty(owner.getProperty());
			//dao.save(owner2);
		}
		//return property;
		return null;
	}
	
}
