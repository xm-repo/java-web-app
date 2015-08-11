package cmc.ps.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cmc.ps.model.FindSmth;
import cmc.ps.model.LegalEntity;
import cmc.ps.model.PhysicalPerson;
import cmc.ps.service.LegalEntityService;
import cmc.ps.service.PhysicalPersonService;

@Controller
@RequestMapping(value = "/main")
public class MainController {
	
	private LegalEntityService legalEntityService;
	private PhysicalPersonService physicalPersonService;
	
	@Autowired
	public void setPhysicalPersonService(PhysicalPersonService physicalPersonService) {
		this.physicalPersonService = physicalPersonService;
	}
	
	@Autowired
	public void setLegalEntityService(LegalEntityService legalEntityService) {
		this.legalEntityService = legalEntityService;
	}
	
	@RequestMapping(value = "/main")
	public ModelAndView main() {
		
	    ModelAndView mv = new ModelAndView();
	    
	    mv.addObject("findSmth", new FindSmth());
	    
	    return mv;
	}	
	
	@RequestMapping(value = "/main", method = RequestMethod.POST)
    public String processSubmit(@ModelAttribute("findSmth") FindSmth findSmth, BindingResult bindingResult, ModelAndView mv, RedirectAttributes redirectAttributes) {
		
        if(findSmth.physicalPerson != null) {
        	
        	PhysicalPerson physicalPerson = physicalPersonService.findByName(findSmth.physicalPerson);
        	if(physicalPerson != null) {
        		return "redirect:/physicalpersons/physicalpersons.do?id=" + physicalPerson.getId();		
        	}
        }
        
        if(findSmth.legalEntity != null) {
        	
        	LegalEntity legalEntity = legalEntityService.findByName(findSmth.legalEntity);
        	if(legalEntity != null) {
        		return "redirect:/legalentities/legalentities.do?id=" + legalEntity.getId();		
        	}
        }
		
		return "redirect:/main/main.do";
    }
	
	@RequestMapping(value = "/remove")
    public String remove(HttpServletRequest request) {				
        
		physicalPersonService.removeAll();
		legalEntityService.removeAll();
		
		return "redirect:/main/main.do";
    }
	

}
