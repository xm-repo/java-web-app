package cmc.ps.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cmc.ps.model.LegalEntity;
import cmc.ps.service.HistoryService;
import cmc.ps.service.LegalEntityService;

@Controller
@RequestMapping(value = "/history")
public class HistoryController {
	
    private LegalEntityService legalEntityService;
    private HistoryService historyService;
	
	@Autowired
	public void setLegalEntityService(LegalEntityService legalEntityService) {
		this.legalEntityService = legalEntityService;
	}
	
	@Autowired
	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}
		
	
	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public ModelAndView setupForm(@RequestParam(value = "id", required = false) Integer id) {
		
	    ModelAndView mv = new ModelAndView();	
	    
	    LegalEntity legalEntity = null;
	    if(id != null && (legalEntity = legalEntityService.findById(id)) != null) {
	    	
			mv.addObject("legalEntity", legalEntity);
	    	mv.addObject("histories", legalEntity.getHistories());
		} else {
			mv.addObject("histories", historyService.findAll());
		}
	    
	    return mv;
	}
	
	@RequestMapping(value = "/remove")
    public String remove(@RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "rmid") Integer rmid, HttpServletRequest request) {		
		
		historyService.removeById(rmid);
		
		if(id != null) {
			return "redirect:/history/history.do?id=" + id;
		}
		
        return "redirect:/history/history.do";
    }
	
}
