package cmc.ps.web;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cmc.ps.model.LegalEntity;
import cmc.ps.model.Owner;
import cmc.ps.model.PhysicalPerson;
import cmc.ps.service.LegalEntityService;
import cmc.ps.service.OwnerService;
import cmc.ps.service.PhysicalPersonService;
import cmc.ps.service.TransitiveClosureService;

@Controller
@RequestMapping(value = "/transitiveowners")
public class TransitiveownersController {

	private OwnerService ownerService;
	//private BusinessService businessService;
	private LegalEntityService legalEntityService;
	private PhysicalPersonService physicalPersonService;
	private TransitiveClosureService transitiveClosureService;
	
	@Autowired
	public void setPhysicalPersonService(PhysicalPersonService physicalPersonService) {
		this.physicalPersonService = physicalPersonService;
	}
	
	@Autowired
	public void setTransitiveClosureServices(TransitiveClosureService transitiveClosureService) {
		this.transitiveClosureService = transitiveClosureService;
	}
	
	@Autowired
	public void setOwnerService(OwnerService ownerService) {
		this.ownerService = ownerService;
	}
	
	/*@Autowired
	public void setBusinessService(BusinessService businessService) {
		this.businessService = businessService;
	}*/
	
	@Autowired
	public void setLegalEntityService(LegalEntityService legalEntityService) {
		this.legalEntityService = legalEntityService;
	}
	
	@RequestMapping(value = "/transitiveowners", method = RequestMethod.GET)
    public ModelAndView setup(@RequestParam(value="leid", required = false) Integer leid, @RequestParam(value="ppid", required = false) Integer ppid, 
    		ModelAndView mv, RedirectAttributes redirectAttributes) {
		
		if(leid != null) {
			
			LegalEntity legalEntity = legalEntityService.findById(leid);
			if(legalEntity == null) {
				return mv;
			}
			
			mv.addObject("legalEntity", legalEntity);
			
			Set<Owner> owners = transitiveClosureService.getTransitiveClosure(legalEntity);
			mv.addObject("owners", owners);
			
			return mv;
		}
		
		if(ppid != null) {
			
			PhysicalPerson physicalPerson = physicalPersonService.findById(ppid);
			if(physicalPerson == null) {
				return mv;
			}
			
			mv.addObject("physicalPerson", physicalPerson);
			
			Set<Owner> owners = transitiveClosureService.getTransitiveClosure(physicalPerson);
			mv.addObject("owners", owners);
			
			return mv;
		}
		
		mv.addObject("owners", ownerService.findAll());	
		//mv.addObject("businesses", businessService.findAll());

		return mv;
    }
	
}
