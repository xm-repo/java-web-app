package cmc.ps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cmc.ps.dao.PhysicalPersonDAO;
import cmc.ps.model.PhysicalPerson;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

/*
 * This is the implementation for our PhysicalPerson Service. The @Service annotation
 * allows Spring to automatically detect this as a component rather than having
 * to configure it in XML. The @Autowired annotation tells Spring to inject our
 * PhysicalPerson DAO using the setDao() method.
 */

@Service
@Transactional
public class PhysicalPersonServiceImpl implements PhysicalPersonService {

	PhysicalPersonDAO dao;

	@Autowired
	public void setDao(PhysicalPersonDAO dao) {
		this.dao = dao;
	}

	public boolean save(PhysicalPerson physicalPerson) {
		return dao.save(physicalPerson);
	}

	public List<PhysicalPerson> findAll() {
		return dao.findAll();
	}

	public PhysicalPerson findByName(String fullName) {
		if(fullName == null) {
			return null;
		}
		return dao.searchUnique(new Search().addFilterEqual("fullName", fullName));
	}

	public void flush() {
		dao.flush();
	}
	
	public void refresh(PhysicalPerson physicalPerson) {
		dao.refresh(physicalPerson);
	}

	public List<PhysicalPerson> search(ISearch search) {
		return dao.search(search);
	}

	public PhysicalPerson findById(Integer id) {
		return dao.find(id);
	}

	public boolean removeById(Integer id) {		
		return dao.removeById(id);
	}
	
	public boolean remove(PhysicalPerson physicalPerson) {		
		return dao.remove(physicalPerson);
	}
	
	public void removeAll() {		
		
		for(PhysicalPerson physicalPerson: dao.findAll()) {
			dao.remove(physicalPerson);
		}
		
	}

	public SearchResult<PhysicalPerson> searchAndCount(ISearch search) {
		return dao.searchAndCount(search);
	}
}
