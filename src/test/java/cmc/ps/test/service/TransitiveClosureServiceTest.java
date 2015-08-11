package cmc.ps.test.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cmc.ps.model.LegalEntity;
import cmc.ps.model.Owner;
import cmc.ps.model.PhysicalPerson;


public class TransitiveClosureServiceTest extends ServiceTestBase{
		
    @BeforeClass
    public void beforeTest() {

    }
    
    @DataProvider
    public Object[][] makeTest() {
    	
    	int[][] owners0 = new int[][] {};
    	
    	//int[][] businesses0 = new int[][] {};
    	
    	int[][] testPhysicalPersons0 = new int [][] {};
    	
    	int[][] testLegalEntites0 = new int[][] {};
    	
    	int[][] rightOwners0 = new int [][] {};
    	
    	int[][] owners1 = new int[][] {
    		{1, -1, 0},  //pp0->le1
    		{2, -1, 0},  //pp0->le2
    		{2, -1, 1},  //pp1->le2
    		{2, 0, -1},  //le0->le2
    		{3, 0, -1},  //le0->le3
    		{4, 2, -1},  //le2->le4 
    		{6, 5, -1},  //le5->le6
    		{5, -1, 2},  //pp2->le5  
    		{5, 7, -1},  //le7->le5
    		{8, -1, 3}   //pp3->le8
    	};
    	  	
    	//int[][] businesses1 = new int[][] {
    		//{5, 0},  //le5=>le0
    		//{4, 2}
    	//};
    	
        int[][] testPhysicalPersons1 = new int [][] { {0}, {1}, {}, {}, {}, {}, {} };
    	
    	int[][] testLegalEntites1 = new int[][] { {}, {}, {0}, {1}, {2}, {3}, {4} };
    	
    	int[][] rightOwners1 = new int [][] {
    		{0, 1, 2, 3, 4, 5}, //, 6, , 7, 8},
    		{0, 1, 2, 3, 4, 5}, //, 6, 7, 8},
    		{0, 1, 2, 3, 4, 5}, //, 6, 7, 8}, 
    		{0, 1, 2, 3, 4, 5}, //, 6, 7, 8},
    		{0, 1, 2, 3, 4, 5}, //, 6, 7, 8},
    		{0, 1, 2, 3, 4, 5}, //, 6, 7, 8},
    		{0, 1, 2, 3, 4, 5} //, 6, 7, 8},
    		//{0, 1, 2, 3, 4, 5}, //, 6, 7, 8},
    		//{0, 1, 2, 3, 4, 5}, //, 6, 7, 8},
    		//{0, 1, 2, 3, 4, 5}, //, 6, 7, 8},
    		//{0, 1, 2, 3, 4, 5}, //, 6, 7, 8}
    	};
    	
    	return new Object [][] { 
    	    {owners0, /*businesses0,*/ testPhysicalPersons0, testLegalEntites0, rightOwners0}, 
    	    {owners1, /*businesses1,*/ testPhysicalPersons1, testLegalEntites1, rightOwners1} 
        };
    }
    
    @Test
    public void test() throws ParseException {
    	PhysicalPerson physicalPerson = new PhysicalPerson("physicalPerson1", "c1", "b1", null);
    	physicalPersonService.save(physicalPerson);
    	
    	Set<PhysicalPerson> ppSet = new HashSet<PhysicalPerson>();
    	ppSet.add(physicalPerson);
    	
    	Set<Owner> transitiveClosure = (Set<Owner>) transitiveClosureService.getTransitiveClosure(ppSet, null);
    	Assert.assertTrue(transitiveClosure.isEmpty());
    	
    	LegalEntity legalEntity = new LegalEntity("legalEntity1", new SimpleDateFormat("yyyy-MM-dd").parse("1980-01-01"), true, "misc");
    	legalEntityService.save(legalEntity);
    	
    	Set<LegalEntity> leSet = new HashSet<LegalEntity>();
    	leSet.add(legalEntity);
    	
    	transitiveClosure = (Set<Owner>) transitiveClosureService.getTransitiveClosure(null, leSet);
    	Assert.assertTrue(transitiveClosure.isEmpty());
    	
    	Owner owner = new Owner(1, legalEntity, null, physicalPerson);
    	ownerService.save(owner);
    	
    	physicalPersonService.refresh(physicalPerson);
    	legalEntityService.refresh(legalEntity);
    	
    	transitiveClosure = (Set<Owner>) transitiveClosureService.getTransitiveClosure(ppSet, null);

    }
            
    @Test(dataProvider = "makeTest")
    public void transitiveClosureTest(int [][] makeOwners, /*int[][] makeBusiness,*/ int[][] testPhysicalPersons, 
    		int[][] testLegalEntites, int[][] rightOwners) throws ParseException {
    	
    	int maxpp = -1;
    	int maxle = -1;
    	for(int[] owners : makeOwners) {
    		maxpp = (owners[2] > maxpp) ? owners[2] : maxpp;
    		maxle = (owners[1] > maxle) ? owners[1] : maxle;
    		maxle = (owners[0] > maxle) ? owners[0] : maxle;
    	}
    	
    	List<PhysicalPerson> physicalPersons = new ArrayList<PhysicalPerson>();    	
    	for(int i = 0; i < maxpp + 1; i++) {
    		PhysicalPerson physicalPerson = new PhysicalPerson("full name: " + Integer.toString(i), Integer.toString(i), Integer.toString(i));
    		physicalPersonService.save(physicalPerson);
    		physicalPersons.add(physicalPerson);
    	}
    	
    	List<LegalEntity> legalEntities = new ArrayList<LegalEntity>();   	
    	for(int i = 0; i < maxle + 1; i++) {
    		LegalEntity legalEntity = new LegalEntity("business name: " + Integer.toString(i), new SimpleDateFormat("yyyy-MM-dd").parse("1980-01-01"), true, "misc");
    		legalEntityService.save(legalEntity);
    		legalEntities.add(legalEntity);
    	}   
    	
    	List<Owner> owners = new ArrayList<Owner>();
    	
    	for(int [] nextOwner : makeOwners) { 		
    		Owner owner = new Owner(1, legalEntities.get(nextOwner[0]), 
    				(nextOwner[1] != -1) ? legalEntities.get(nextOwner[1]) : null, 
    				(nextOwner[2] != -1) ? physicalPersons.get(nextOwner[2]) : null);  		
    		ownerService.save(owner);  	
    		owners.add(owner);  		
    	}
    	ownerService.flush();
    	
    	//for(int [] nextBusiness : makeBusiness) {
    		//Business business = new Business("misc", null, legalEntities.get(nextBusiness[0]), 
    			//	legalEntities.get(nextBusiness[1]));
    		//businessService.save(business);
    	//}
    	//businessService.flush();
    	
    	physicalPersonService.flush();
    	for(PhysicalPerson physicalPerson : physicalPersons) {
    		physicalPersonService.refresh(physicalPerson);
    	}
    	legalEntityService.flush();
    	for(LegalEntity legalEntity : legalEntities) {
    		legalEntityService.refresh(legalEntity);
    	}
    	
    	Set<LegalEntity> leSet = new HashSet<LegalEntity>();
    	Set<PhysicalPerson> ppSet = new HashSet<PhysicalPerson>();
    	
    	for(int i = 0; i < rightOwners.length; i++) {
    		
    		ppSet.clear();
    		for(int testPhysicalPerson : testPhysicalPersons[i]) {
    			ppSet.add(physicalPersons.get(testPhysicalPerson));
    		}
    		
    		leSet.clear();
    		for(int testLegalEntity : testLegalEntites[i]) {
    			leSet.add(legalEntities.get(testLegalEntity));
    		}
    		
    		Set<Owner> transitiveClosure = (Set<Owner>) transitiveClosureService.getTransitiveClosure(ppSet, leSet);
    		
    		Assert.assertEquals(transitiveClosure.size(), rightOwners[i].length);
    		for(int rightOwner : rightOwners[i]) {
    			Assert.assertTrue(transitiveClosure.contains(owners.get(rightOwner)));
    		}
    		
    	}
    	
    }
 
}

