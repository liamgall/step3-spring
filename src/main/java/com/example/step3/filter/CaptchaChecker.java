package com.example.step3.filter;

import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

public class CaptchaChecker {

	final String url = "https://www.google.com/recaptcha/api/siteverify";
	private ResponseEntity<String> response;
	private JSONObject jsonObj;
	
	public CaptchaChecker(String captcha) throws ParseException {

		RestTemplate template = new RestTemplate();
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();

//		parameters.add("secret", "6Ld2LBcUAAAAAA9MavSDWm0J_WsyLrSfArwg3zcz");
		parameters.add("secret", "6LcUZxcUAAAAAMvScdUgnVLPJjub6J91IHCBjcRv");
		parameters.add("response", captcha);
		response = template.postForEntity(url, parameters, String.class);

		JSONParser parser = new JSONParser();
		jsonObj = (JSONObject) parser.parse(response.getBody());
	}
	
	public JSONObject getResult(){
		return jsonObj;
	}

}
