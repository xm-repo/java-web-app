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

import cmc.ps.model.PhysicalPerson;
import cmc.ps.service.PhysicalPersonService;


@Controller
@RequestMapping(value = "/physicalpersons")
public class PhysicalPersonsController {
	
	private PhysicalPersonService physicalPersonService;
	private Validator validator;
	
	@Autowired
	public void setPhysicalPersonService(PhysicalPersonService physicalPersonService) {
		this.physicalPersonService = physicalPersonService;
	}
	
	@Autowired
	public void setValidator(Validator validator) {
	    this.validator = validator;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.setValidator((org.springframework.validation.Validator) this.validator);
	}
	
	@RequestMapping(value = "/physicalpersons", method = RequestMethod.GET)
	public ModelAndView setupForm(@RequestParam(value="id", required=false) Integer id, ModelAndView mv) {
		
		if(id != null) {
			mv.addObject("physicalPerson", physicalPersonService.findById(id));
		} else {
			mv.addObject("physicalPerson", new PhysicalPerson());
		} 
	   
		mv.addObject("physicalPersons", physicalPersonService.findAll());
	    
	    return mv;
	}
	
	@RequestMapping(value = "/physicalpersons", method = RequestMethod.POST)
    public ModelAndView processSubmit(@ModelAttribute("physicalPerson") @Valid PhysicalPerson physicalPerson, BindingResult bindingResult, ModelAndView mv, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			
        } else {
        	physicalPersonService.save(physicalPerson);
        	physicalPersonService.flush();
        	physicalPersonService.refresh(physicalPerson);
        }
		
		mv.addObject("physicalPersons", physicalPersonService.findAll());
		
		return mv;
    }
	
	@RequestMapping(value = "/remove")
    public String remove(@RequestParam("id") Integer id, HttpServletRequest request) {	
		
		physicalPersonService.removeById(id);
        return "redirect:/physicalpersons/physicalpersons.do";
    }
	
}
