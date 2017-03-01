package com.example.step3;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public ModelAndView viewLogin(Map<String, Object> model, @RequestParam("eMail") String eMail)
			throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("JoinForm");
		mav.addObject("eMail", java.net.URLDecoder.decode(eMail, "UTF-8"));
		User user = new User();
		model.put("userForm", user);
		return mav;
	}

	@RequestMapping(value = "/InfoValidation", method = RequestMethod.POST)
	public String doLogin(@Valid @ModelAttribute("userForm") User userForm, BindingResult result,
			Map<String, Object> model, @RequestParam("captcha") String captcha) {
		System.out.println(captcha);
		if (result.hasErrors()) {
			return "JoinForm";
		}

		return "JoinSuccess";
	}
}