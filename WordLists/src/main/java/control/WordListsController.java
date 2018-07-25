package control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import model.WordListsModel;

@Controller
public class WordListsController {

	  @Autowired
	   WordListsModel wlm;
	  @RequestMapping(value = "/index")
	public ModelAndView getUrlParameter(@RequestParam("words") String words) {
		ModelAndView mav= new ModelAndView();
		mav.addObject("list", wlm.getPrintLists(words));
		mav.setViewName("index");
		return mav;
	}	
	
	
	

}
