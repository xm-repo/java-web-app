package cmc.ps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cmc.ps.dao.BusinessDAO;
import cmc.ps.dao.LegalEntityDAO;
import cmc.ps.model.Business;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;

/*
 * This is the implementation for our Business Service. The @Service annotation
 * allows Spring to automatically detect this as a component rather than having
 * to configure it in XML. The @Autowired annotation tells Spring to inject our
 * Business DAO using the setDao() method.
 */

@Service
@Transactional
public class BusinessServiceImpl implements BusinessService {

	BusinessDAO dao;
	LegalEntityDAO leDao;

	@Autowired
	public void setDao(BusinessDAO dao) {
		this.dao = dao;
	}
	
	@Autowired
	public void setleDao(LegalEntityDAO leDao) {
		this.leDao = leDao;
	}

	public boolean save(Business business) {
		
		/*if(business.getLegalEntity1() != null) {
			leDao.refresh(business.getLegalEntity1());
		}
		if(business.getLegalEntity2() != null) {
			leDao.refresh(business.getLegalEntity2());
		}
		
		Search search = new Search();
		search.setSearchClass(Business.class);
		search.addFilterEqual("legalEntity1", business.getLegalEntity1());
		search.addFilterEqual("legalEntity2", business.getLegalEntity2());
		
		List<Business> b = dao.search(search);
		Business business2 = owner;
		if(!b.isEmpty()) {
			business2 = b.get(0);
			business2.setMiscellaneous();
			business2.setPurchaseDate();
			business.setId(business2.getId());
		}*/
		
		return dao.save(business);
	}

	public List<Business> findAll() {
		return dao.findAll();
	}

	public void flush() {
		dao.flush();
	}

	public List<Business> search(ISearch search) {
		return dao.search(search);
	}

	public Business findById(Integer id) {
		return dao.find(id);
	}

	public boolean removeById(Integer id) {
		return dao.removeById(id);
	}
	
	public boolean remove(Business business) {
		return dao.remove(business);
	}

	public SearchResult<Business> searchAndCount(ISearch search) {
		return dao.searchAndCount(search);
	}

	@Override
	public void refresh(Business business) {
		dao.refresh(business);
	}
}
