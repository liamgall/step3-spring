package com.example.step3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class EmailController {
	
    @RequestMapping(value = "/eMailValidation", method = RequestMethod.POST)
    @ResponseBody
    public String eMailValidation(@RequestBody String body) {
    	return body;
    }

	@RequestMapping(value="/validation/{param}", method=RequestMethod.GET)
	public ModelAndView validAccept(@PathVariable String param){
		System.out.println(param);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("eMailValidationSuccess");
		mav.addObject("eMail", param);
		return mav;
	}
}