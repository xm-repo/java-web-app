package cmc.ps.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cmc.ps.dao.LegalEntityDAO;
import cmc.ps.dao.OwnerDAO;
import cmc.ps.dao.PhysicalPersonDAO;
import cmc.ps.model.LegalEntity;
import cmc.ps.model.Owner;
import cmc.ps.model.PhysicalPerson;


/*
 * This is the implementation for our TransitiveClosureService Service. The @Service annotation
 * allows Spring to automatically detect this as a component rather than having
 * to configure it in XML. The @Autowired annotation tells Spring to inject our
 * PhysicalPerson, LegalEntity, Owner DAO using the setDao() method. 
 */


@Service
@Transactional
public class TransitiveClosureServiceImpl implements TransitiveClosureService {

	OwnerDAO ownerDAO;
	PhysicalPersonDAO physicalPersonDAO;
	LegalEntityDAO legalEntityDAO;    

	@Autowired
	public void setDao(OwnerDAO ownerDAO, PhysicalPersonDAO physicalPersonDAO, LegalEntityDAO legalEntityDAO) {
		this.ownerDAO = ownerDAO;
		this.physicalPersonDAO = physicalPersonDAO;
		this.legalEntityDAO = legalEntityDAO;
	}
	
	public Set<Owner> getTransitiveClosure(Set<PhysicalPerson> physicalPersons, 
			Set<LegalEntity> legalEntities) {
		
		Set<PhysicalPerson> addedPOwners = new HashSet<PhysicalPerson>();
		Set<PhysicalPerson> addPOwners = new HashSet<PhysicalPerson>(); 
		if(physicalPersons != null) {
		    addPOwners.addAll(physicalPersons);
		}
				
		Set<LegalEntity> addedLEOwners = new HashSet<LegalEntity>();
		Set<LegalEntity> addLEOwners = new HashSet<LegalEntity>();
		if(legalEntities != null) {
            addLEOwners.addAll(legalEntities);
		}
               
        Set<Owner> transitiveClosure = new HashSet<Owner>();
        Set<LegalEntity> newLegalEntities = new HashSet<LegalEntity>();
        
        //Set<Business> transitiveBusiness = new HashSet<Business>();

        while(!addLEOwners.isEmpty() || !addPOwners.isEmpty()) {
        	
        	Set<Owner> newOwners = new HashSet<Owner>(); 
        	
        	for(PhysicalPerson physicalPerson : addPOwners) {      		
        		List<Owner> ppOwners = physicalPerson.getOwners();
        		//if(ppOwners != null) {
        		    newOwners.addAll(ppOwners);
        		//}
        	}
        	
        	
        	newLegalEntities.clear();
        	for(LegalEntity legalEntity : addLEOwners) {
        		
        		//!List<Business> businesses1 = legalEntity.getBusinesses1();
        		//!transitiveBusiness.addAll(businesses1);
        		//if(businesses1 != null) {
        			//!for(Business business : businesses1) {
        				//!LegalEntity le2 = business.getLegalEntity2();
        				//!newLegalEntities.add(le2);
        			//!}
        		//}
        		//!List<Business> businesses2 = legalEntity.getBusinesses2();
        		//!transitiveBusiness.addAll(businesses2);
        		//if(businesses2 != null) {
        			//!for(Business business : businesses2) {
        				//!LegalEntity le1 = business.getLegalEntity1();
        				//!newLegalEntities.add(le1);
        			//!}
        		//}
        		
        		List<Owner> leOwners1 = legalEntity.getOwners1();
        		//if(leOwners1 != null) {
        		    newOwners.addAll(leOwners1);
        		//}
        		List<Owner> leOwners2 = legalEntity.getOwners2();
        		//if(leOwners2 != null) {
        		    newOwners.addAll(leOwners2);
        		//}     		
        	}
        	
        	addedPOwners.addAll(addPOwners);
        	addPOwners.clear();
        	addedLEOwners.addAll(addLEOwners);
        	addLEOwners.clear();
        	transitiveClosure.addAll(newOwners);
        	
        	for(LegalEntity legalEntity : newLegalEntities) {
        		if(!addedLEOwners.contains(legalEntity)) {
        			addLEOwners.add(legalEntity);
        		}
        	}
        	
        	for(Owner owner : newOwners) {
        		
        		LegalEntity le1 = owner.getLegalEntity1();
        		if(!addedLEOwners.contains(le1)) {
        			addLEOwners.add(le1);    	
        		}
        		
        		LegalEntity le2 = owner.getLegalEntity2();       		
        		if(le2 != null && !addedLEOwners.contains(le2)) {
        			addLEOwners.add(le2);    	
        		}
        		
        		PhysicalPerson pp = owner.getPhysicalPerson();
        		if(pp != null && !addedPOwners.contains(pp)) {
        			addPOwners.add(pp);
        		}     		
        	}
        }
        
		if(physicalPersons != null) {
            physicalPersons.clear();
		    physicalPersons.addAll(addedPOwners);
		}
		if(legalEntities != null) {
		    legalEntities.clear();
		    legalEntities.addAll(addedLEOwners);
		}
		
		return transitiveClosure;
		//return new Object[] {transitiveClosure, transitiveBusiness};
	}

	@Override
	public Set<Owner> getTransitiveClosure(PhysicalPerson physicalPerson) {
    
		Set<PhysicalPerson> pp = new HashSet<PhysicalPerson>(); 
		pp.add(physicalPerson);
		
		return this.getTransitiveClosure(pp, null);
	}

	@Override
	public Set<Owner> getTransitiveClosure(LegalEntity legalEntity) {
		
		Set<LegalEntity> le = new HashSet<LegalEntity>();
		le.add(legalEntity);
		
		return this.getTransitiveClosure(null, le);
	}

}
