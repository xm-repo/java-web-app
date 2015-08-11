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

import cmc.ps.model.Business;
import cmc.ps.model.Owner;
import cmc.ps.service.BusinessService;
import cmc.ps.service.LegalEntityService;
import cmc.ps.service.OwnerService;
import cmc.ps.service.PhysicalPersonService;


@Controller
@RequestMapping(value = "/superowners")
public class SuperownersController {
	
	private OwnerService ownerService;
	private BusinessService businessService;
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
	public void setBusinessService(BusinessService businessService) {
		this.businessService = businessService;
	}
	
	@Autowired
	public void setLegalEntityService(LegalEntityService legalEntityService) {
		this.legalEntityService = legalEntityService;
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
		
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.setValidator((org.springframework.validation.Validator) this.validator);
    }
	
	@RequestMapping(value = "*", method = RequestMethod.GET)
    public ModelAndView setupForm(@RequestParam(value="id", required = false) Integer id, @RequestParam(value="bid", required = false) Integer bid, ModelAndView mv, RedirectAttributes redirectAttributes) {
		
        //ModelAndView mv = new ModelAndView();
		
		mv.addObject("ppowners", ownerService.findPPOwners());
		mv.addObject("leowners", ownerService.findLEOwners());		
		mv.addObject("businesses", businessService.findAll());
		mv.addObject("legalentities", legalEntityService.findAll());
		mv.addObject("physicalpersons", physicalPersonService.findAll());
		
		if(id != null) {
			mv.addObject("owner", ownerService.findById(id));
		} else {
			mv.addObject("owner", new Owner());
		}
		
		if(bid != null) {
			mv.addObject("business", businessService.findById(bid));
		} else {
			mv.addObject("business", new Business());
		}

		return mv;
    }
	
	@RequestMapping(value = "/owners", method = RequestMethod.POST)
    public String processSubmit(@ModelAttribute("owner") @Valid Owner owner, BindingResult bindingResult, ModelAndView mv, RedirectAttributes redirectAttributes) {
		
		
		if(bindingResult.hasErrors()) {

		} else {
			ownerService.save(owner);
			ownerService.flush();
			ownerService.refresh(owner);
		}
        
		
		//return mv;
		return "redirect:/owners/owners.do";
    }
	
	@RequestMapping(value = "/business", method = RequestMethod.POST)
    public String processBSubmit(@ModelAttribute("business") @Valid Business business, BindingResult bindingResult, ModelAndView mv, RedirectAttributes redirectAttributes) {
		
		//RedirectView rv =  new RedirectView("owners.do");
				
		if(bindingResult.hasErrors()) {
            
		} else {
			businessService.save(business);
			businessService.flush();	
			businessService.refresh(business);
		}
		//return mv;
		return "redirect:/owners/owners.do";
    }
	
	@RequestMapping(value = "/remove")
    public String remove(@RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "bid", required = false) Integer bid, HttpServletRequest request) {		
		
		if(id != null) {
		    ownerService.removeById(id);
		}
		
		if(bid != null) {
			businessService.removeById(bid);
		}
		
        return "redirect:/owners/owners.do";
    }
	
}
