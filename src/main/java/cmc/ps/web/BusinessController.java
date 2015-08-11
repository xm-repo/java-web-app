package cmc.ps.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cmc.ps.model.LegalEntity;
import cmc.ps.service.BusinessService;
import cmc.ps.service.LegalEntityService;

@Controller
@RequestMapping(value = "/business")
public class BusinessController {
	
	private BusinessService businessService;
	private LegalEntityService legalEntityService;
	
	private Validator validator;
	
	@Autowired
	public void setValidator(Validator validator) {
	    this.validator = validator;
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
	
	@RequestMapping(value = "/business", method = RequestMethod.GET)
    public ModelAndView setupForm(@RequestParam(value = "id", required = false) Integer id) {
		
        ModelAndView mv = new ModelAndView();	
	    
        LegalEntity legalEntity = null;
	    if(id != null && (legalEntity = legalEntityService.findById(id)) != null) {
	    	
			mv.addObject("legalEntity", legalEntity);
	    	mv.addObject("businesses", legalEntity.getBusinesses2());
		} else {
			mv.addObject("businesses", businessService.findAll());
		}
		
		return mv;
    }
	
	@RequestMapping(value = "/remove")
    public String remove(@RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "rmid") Integer rmid, HttpServletRequest request) {		
		
		businessService.removeById(rmid);
		
		if(id != null) {
			return "redirect:/business/business.do?id=" + id;
		}
		
        return "redirect:/business/business.do";
    }
	
}
