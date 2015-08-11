package cmc.ps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cmc.ps.dao.HistoryDAO;
import cmc.ps.model.History;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;

/*
 * This is the implementation for our History Service. The @Service annotation
 * allows Spring to automatically detect this as a component rather than having
 * to configure it in XML. The @Autowired annotation tells Spring to inject our
 * History DAO using the setDao() method.
 */

@Service
@Transactional
public class HistoryServiceImpl implements HistoryService {

	HistoryDAO dao;

	@Autowired
	public void setDao(HistoryDAO dao) {
		this.dao = dao;
	}

	public boolean save(History history) {
		return dao.save(history);
	}

	public List<History> findAll() {
		return dao.findAll();
	}

	public void flush() {
		dao.flush();
	}

	public List<History> search(ISearch search) {
		return dao.search(search);
	}

	public History findById(Integer id) {
		return dao.find(id);
	}

	public boolean removeById(Integer id) {
		return dao.removeById(id);
	}
	
	public boolean remove(History history) {
		return dao.remove(history);
	}

	public SearchResult<History> searchAndCount(ISearch search) {
		return dao.searchAndCount(search);
	}

	@Override
	public void refresh(History history) {
		dao.refresh(history);
	}
}
