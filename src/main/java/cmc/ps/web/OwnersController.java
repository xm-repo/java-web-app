package cmc.ps.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cmc.ps.model.Owner;
import cmc.ps.service.LegalEntityService;
import cmc.ps.service.OwnerService;
import cmc.ps.service.PhysicalPersonService;
//import cmc.ps.web.customeditor.CustomOwnerEditor;


@Controller
@RequestMapping(value = "/owners")
public class OwnersController {
	
	private OwnerService ownerService;
	private LegalEntityService legalEntityService;
	private PhysicalPersonService physicalPersonService;
	
	private Validator validator;
	
	@Autowired
	public void setValidator(Validator validator) {
	    this.validator = validator;
	}
	
	@Autowired
	public void setPhysicalPersonService(PhysicalPersonService physicalPersonService) {
		this.physicalPersonService = physicalPersonService;
	}
	
	@Autowired
	public void setOwnerService(OwnerService ownerService) {
		this.ownerService = ownerService;
	}
	
	@Autowired
	public void setLegalEntityService(LegalEntityService legalEntityService) {
		this.legalEntityService = legalEntityService;
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
		
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		//binder.registerCustomEditor(Owner.class, new CustomOwnerEditor());
        binder.setValidator((org.springframework.validation.Validator) this.validator);
    }
	
	@RequestMapping(value = "/owners", method = RequestMethod.GET)
    public ModelAndView setupForm(@RequestParam(value="id", required = false) Integer id, ModelAndView mv, RedirectAttributes redirectAttributes) {
		
		if(id != null) {
			mv.addObject("owner", ownerService.findById(id));
		} else {
			mv.addObject("owner", new Owner());
		}
		
		mv.addObject("leowners", ownerService.findLEOwners());
		mv.addObject("ppowners", ownerService.findPPOwners());	
		mv.addObject("legalentities", legalEntityService.findAll());
		mv.addObject("physicalpersons", physicalPersonService.findAll());

		return mv;
    }
	
	public boolean validate(Owner owner, BindingResult bindingResult) {
		
		if(owner.getLegalEntity2() != null && owner.getLegalEntity1().getId().compareTo(owner.getLegalEntity2().getId()) == 0) {
			bindingResult.rejectValue("legalEntity2.id", "error", "LE1 != LE2");	
			return false;
		}

		Owner owner2 = ownerService.findEqual(owner);
		if(owner2 != null) {
			ownerService.remove(owner2);
		}
			
		/*legalEntityService.refresh(owner.getLegalEntity1());
		
		List<Owner> owners = owner.getLegalEntity1().getOwners1(); 
		
		int property = 0;
		for(Owner next : owners) {
			property += next.getProperty();
		}
		
		if(owner2 != null) {
		    property -= owner2.getProperty();
		} 
		
		if(owner.getProperty() + property > 100) {
			if(owner2 != null) {
			    owner.setProperty(owner2.getProperty());
			}
			bindingResult.rejectValue("property", "error", "sum(property) must be <= 100");	
		} */
		
		return true;
		
	}
	
	@RequestMapping(value = "/owners", method = RequestMethod.POST)
    public ModelAndView processSubmit(@ModelAttribute("owner") @Valid Owner owner, BindingResult bindingResult, ModelAndView mv, RedirectAttributes redirectAttributes) {
		
		
		if(bindingResult.hasErrors()) {

		} else {
			
			if(this.validate(owner, bindingResult)) {
			    ownerService.save(owner);
			    ownerService.refresh(owner);
			}
			
			
			//OwnerValidator v = new OwnerValidator(legalEntityService);
			//BeanPropertyBindingResult result = new BeanPropertyBindingResult(owner, "owner");
			//ValidationUtils.invokeValidator(v, owner, result);
			
			//ownerService.removeEqual(owner);
		}
        
		mv.addObject("leowners", ownerService.findLEOwners());	
		mv.addObject("ppowners", ownerService.findPPOwners());
		mv.addObject("legalentities", legalEntityService.findAll());
		mv.addObject("physicalpersons", physicalPersonService.findAll());
		
		return mv;

    }
	
	@RequestMapping(value = "/remove")
    public String remove(@RequestParam(value = "id") Integer id, HttpServletRequest request) {		
		
		ownerService.removeById(id);		
        return "redirect:/owners/owners.do";
    }
	
}
