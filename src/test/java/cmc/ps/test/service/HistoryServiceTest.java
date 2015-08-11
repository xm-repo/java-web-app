package cmc.ps.test.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cmc.ps.model.History;
import cmc.ps.model.LegalEntity;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;


public class HistoryServiceTest extends ServiceTestBase{
	
	@BeforeClass
    public void setUp() {
    }
 
    @Test
    public void simpleTest() {	
    	
    	//empty table
    	List<History> histories = historyService.findAll();
    	Assert.assertTrue(histories.isEmpty());
    	
    	LegalEntity legalEntity = new LegalEntity("LE", null, true, "misc");
    	legalEntityService.save(legalEntity);
    	
    	//save
    	History history1 = new History(null, "misc", "2LE", legalEntity);
    	History history2 = new History(null, "misc", "3LE", legalEntity);
    	Assert.assertTrue(historyService.save(history1));
    	Integer h1id;
    	Assert.assertTrue((h1id = history1.getId()) != 0);
    	Assert.assertTrue(historyService.save(history2));
    	Assert.assertTrue(history2.getId() != 0);
    	historyService.flush();
    	
    	//update
    	history1.setOldBusinessName("22LE");
    	Assert.assertFalse(historyService.save(history1));
    	Assert.assertEquals(history1.getId(), h1id);
        
        //search
        Search search = new Search();
        search.addFilterEqual("id", history1.getId());
        search.setResultMode(Search.RESULT_SINGLE);
        search.addField("oldBusinessName");
        Assert.assertEquals(history1.getOldBusinessName(), historyService.search(search).get(0));
        search.setSearchClass(History.class);
        Assert.assertEquals(history1.getOldBusinessName(), historyService.search(search).get(0));
        search.addFilterEqual("id", 1000);
        Assert.assertTrue(historyService.search(search).isEmpty());
    	
    	//findAll
    	histories = historyService.findAll();
    	Assert.assertTrue(histories.contains(history1));
    	Assert.assertTrue(histories.contains(history2));
    	Assert.assertEquals(histories.size(), 2);
    	
    	//findById
    	Assert.assertEquals(historyService.findById(history1.getId()), history1);
    	Assert.assertEquals(historyService.findById(history2.getId()), history2);
    	Assert.assertNull(historyService.findById(1000));
    	
    	//searchAndCount
        SearchResult<History> result = historyService.searchAndCount(new Search());
        this.assertListEqual(new History[] { history1, history2 }, result.getResult());
        result = historyService.searchAndCount(new Search(History.class));
        this.assertListEqual(new History[] { history1, history2 }, result.getResult());
    	
    	//check searching with null
        assertListEqual(new History[] { history1, history2 }, historyService.search(null));
        assertListEqual(new History[] { history1, history2 }, historyService.searchAndCount(null).getResult());
        Assert.assertEquals(historyService.searchAndCount(null).getTotalCount(), 2);
    	
    	//removeById
    	Assert.assertFalse(historyService.removeById(1000));
        Assert.assertTrue(historyService.removeById(history1.getId()));
    	Assert.assertEquals(null, historyService.findById(history1.getId()));
    	
    	//remove
    	History history3 = new History(null, "misc", "4LE", legalEntity);
    	Assert.assertFalse(historyService.remove(history3)); 
    	Assert.assertTrue(historyService.remove(history2));
    	Assert.assertEquals(null, historyService.findById(history2.getId()));
    	
    	//empty table
    	Assert.assertTrue(historyService.findAll().isEmpty());
    	
    	historyService.flush();
    	
    }
    
    private void assertListEqual(History[] expected, List<History> actual) {
        Assert.assertEquals(expected.length, actual.size(), "The list did not have the expected length");

        Set<Integer> unmatched = new HashSet<Integer>();
        for(History history : expected) {
                unmatched.add(history.getId());
        }
        for(History history : actual) {
                unmatched.remove(history.getId());
        }
        if(unmatched.size() != 0) {
            Assert.fail("The list did not match the expected results.");
        }
    }    

 
}

