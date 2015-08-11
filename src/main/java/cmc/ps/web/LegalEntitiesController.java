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

import cmc.ps.model.LegalEntity;
import cmc.ps.service.LegalEntityService;

@Controller
@RequestMapping(value = "/legalentities")
public class LegalEntitiesController {
	
	private LegalEntityService legalEntityService;
	private Validator validator;
	
	@Autowired
	public void setPhysicalPersonService(LegalEntityService legalEntityService) {
		this.legalEntityService = legalEntityService;
	}
	
	@Autowired
	public void setValidator(Validator validator) {
	    this.validator = validator;
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
		
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.setValidator((org.springframework.validation.Validator) this.validator);
    }
	
	@RequestMapping(value = "/legalentities", method = RequestMethod.GET)
	public ModelAndView setupForm(@RequestParam(value="id", required = false) Integer id) {
		
		ModelAndView mv = new ModelAndView();		    
	    mv.addObject("legalEntities", legalEntityService.findAll());
	    
	    if(id != null) {
			mv.addObject("legalEntity", legalEntityService.findById(id));
		} else {
			mv.addObject("legalEntity", new LegalEntity());
		}
	    
	    return mv;
	}
	
	@RequestMapping(value = "/legalentities", method = RequestMethod.POST)
    public ModelAndView processSubmit(@ModelAttribute("legalEntity") @Valid LegalEntity legalEntity, BindingResult bindingResult, ModelAndView mv, RedirectAttributes redirectAttributes) {
		
		//ModelAndView mv = new ModelAndView();
		
		if(bindingResult.hasErrors()) {

		} else {
		    legalEntityService.save(legalEntity);
		    legalEntityService.flush();
		    legalEntityService.refresh(legalEntity);
		}
		mv.addObject("legalEntities", legalEntityService.findAll());
				
		return mv;
		//return "redirect:/legalentities/legalentities.do";
    }
	
	@RequestMapping(value = "/remove")
    public String remove(@RequestParam("id") Integer id, HttpServletRequest request) {	
		
		legalEntityService.removeById(id);
        return "redirect:/legalentities/legalentities.do";
    }

}
