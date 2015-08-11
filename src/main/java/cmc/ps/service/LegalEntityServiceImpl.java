package cmc.ps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cmc.ps.dao.LegalEntityDAO;
import cmc.ps.model.LegalEntity;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

/*
 * This is the implementation for our LegalEntity Service. The @Service annotation
 * allows Spring to automatically detect this as a component rather than having
 * to configure it in XML. The @Autowired annotation tells Spring to inject our
 * LegalEntity DAO using the setDao() method.
 */

@Service
@Transactional
public class LegalEntityServiceImpl implements LegalEntityService {

	LegalEntityDAO dao;

	@Autowired
	public void setDao(LegalEntityDAO dao) {
		this.dao = dao;
	}

	public boolean save(LegalEntity legalEntity) {
		return dao.save(legalEntity);
	}

	public List<LegalEntity> findAll() {
		return dao.findAll();
	}

	public LegalEntity findByName(String businessName) {
		if(businessName == null) {
			return null;
		}
		return dao.searchUnique(new Search().addFilterEqual("businessName", businessName));
	}

	public void flush() {
		dao.flush();
	}

	public List<LegalEntity> search(ISearch search) {
		return dao.search(search);
	}

	public LegalEntity findById(Integer id) {
		return dao.find(id);
	}

	public boolean removeById(Integer id) {
		return dao.removeById(id);
	}
	
	public boolean remove(LegalEntity legalEntity) {
		return dao.remove(legalEntity);
	}
	
    public void removeAll() {		
		
		for(LegalEntity legalEntity: dao.findAll()) {
			dao.remove(legalEntity);
		}
		
	}

	public SearchResult<LegalEntity> searchAndCount(ISearch search) {
		return dao.searchAndCount(search);
	}

	public void refresh(LegalEntity legalEntity) {
        dao.refresh(legalEntity);		
	}
}
