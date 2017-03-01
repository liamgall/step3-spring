package com.example.step3;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

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
			Map<String, Object> model, @RequestParam("captcha") String captcha,
			@RequestParam("uploadFile") MultipartFile file) throws ParseException, IllegalStateException, IOException {
		
		File saveFile = new File("C:/images/"+file.getOriginalFilename());
		file.transferTo(saveFile);
		
		RestTemplate template = new RestTemplate();
		final String url = "https://www.google.com/recaptcha/api/siteverify";
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.add("secret", "6Ld2LBcUAAAAAA9MavSDWm0J_WsyLrSfArwg3zcz");
		parameters.add("response", captcha);
		ResponseEntity<String> response = template.postForEntity(url, parameters, String.class);

		JSONParser parser = new JSONParser();
		JSONObject jsonObj = (JSONObject) parser.parse(response.getBody());

		if (jsonObj.get("success").toString().equals("false")) {
			return "JoinForm";
		} else {
			if (result.hasErrors()) {
				return "JoinForm";
			}
			return "JoinSuccess";
		}

	}
}