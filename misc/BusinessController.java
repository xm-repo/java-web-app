package cmc.ps.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cmc.ps.model.Business;
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
    public ModelAndView setupForm(@RequestParam(value="id", required = false) Integer id, ModelAndView mv, RedirectAttributes redirectAttributes) {
		
        //ModelAndView mv = new ModelAndView();
		
		if(id != null) {
			mv.addObject("business", businessService.findById(id));
		} else {
			mv.addObject("business", new Business());
		}
		
		mv.addObject("businesses", businessService.findAll());
		mv.addObject("legalentities", legalEntityService.findAll());

		return mv;
    }
	
	@RequestMapping(value = "/business", method = RequestMethod.POST)
    public ModelAndView processSubmit(@ModelAttribute("business") @Valid Business business, BindingResult bindingResult, ModelAndView mv, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {

        } else {
        	businessService.save(business);
        	businessService.flush();
        	businessService.refresh(business);
        }
		
		mv.addObject("businesses", businessService.findAll());
		mv.addObject("legalentities", legalEntityService.findAll());
		
		return mv;
    }
	
	@RequestMapping(value = "/remove")
    public String remove(@RequestParam("id") Integer id, HttpServletRequest request) {	
		
		businessService.removeById(id);
        return "redirect:/business/business.do";
    }
	

}
