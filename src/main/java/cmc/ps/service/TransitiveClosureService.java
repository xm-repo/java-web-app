package cmc.ps.service;

import java.util.Set;

import cmc.ps.model.LegalEntity;
import cmc.ps.model.Owner;
import cmc.ps.model.PhysicalPerson;


/*
 * This is the interface for our TransitiveClosure Service. As a matter of best practice
 * we reference this interface in other components rather than the
 * implementation itself.
 */


public interface TransitiveClosureService {
	
	/**
	 * Get transitive closure of dependencies for given physical persons and legal entities
	 * 
	 * @param physicalPersons
	 * @param legalEntities
	 * @return 
	 */
	public Set<Owner> getTransitiveClosure(Set<PhysicalPerson> physicalPersons, 
			Set<LegalEntity> legalEntities);
	
	public Set<Owner> getTransitiveClosure(PhysicalPerson physicalPerson);
	
	public Set<Owner> getTransitiveClosure(LegalEntity legalEntity); 
}