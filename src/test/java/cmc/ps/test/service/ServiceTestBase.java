package cmc.ps.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

import cmc.ps.service.BusinessService;
import cmc.ps.service.HistoryService;
import cmc.ps.service.LegalEntityService;
import cmc.ps.service.OwnerService;
import cmc.ps.service.PhysicalPersonService;
import cmc.ps.service.TransitiveClosureService;


@ContextConfiguration(locations = {"classpath:testContext.xml"})
public abstract class ServiceTestBase extends AbstractTransactionalTestNGSpringContextTests{
	
	@Autowired
	protected HistoryService historyService;
	
	@Autowired
	protected LegalEntityService legalEntityService;
	
	@Autowired
	protected OwnerService ownerService;
	
	@Autowired
	protected BusinessService businessService;
	
	@Autowired
	protected PhysicalPersonService physicalPersonService;
	
	@Autowired
	protected TransitiveClosureService transitiveClosureService;
 
}

