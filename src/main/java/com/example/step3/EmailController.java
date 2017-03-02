package com.example.step3;

import javax.servlet.http.HttpServletRequest;

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
    public String eMailValidation(@RequestBody String body, HttpServletRequest request) {
    	String response = request.getRequestURL().substring(0,request.getRequestURL().indexOf(request.getRequestURI()));
    	return response;
    }
	@RequestMapping(value="/validation/{param}", method=RequestMethod.GET)
	public ModelAndView validAccept(@PathVariable String param, HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("eMailValidationSuccess");
		mav.addObject("eMail", param);
		return mav;
	}
}