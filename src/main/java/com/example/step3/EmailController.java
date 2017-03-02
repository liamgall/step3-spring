package com.example.step3;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class EmailController {
	private String eMail;
	private final byte[] keyValue = "abcdefghijklmnop".getBytes();
    @RequestMapping(value = "/eMailValidation", method = RequestMethod.POST)
    @ResponseBody
    public String eMailValidation(@RequestBody String body, HttpServletRequest request) throws Exception {
    	String response = request.getRequestURL().substring(0,request.getRequestURL().indexOf(request.getRequestURI()));

    	try{
    		Key key = new SecretKeySpec(keyValue, "AES");
    		Cipher c = Cipher.getInstance("AES");
    		c.init(Cipher.ENCRYPT_MODE, key);
    		byte[] encVal = c.doFinal(body.getBytes());
    		eMail = body;
    		String encryptedValue = new org.apache.commons.codec.binary.Base64().encodeAsString(encVal);
    		
        	return response+"/validation/"+encryptedValue;
    	} catch(Exception e){
    		throw e;
    	}

    }
	@RequestMapping(value="/validation/{param:.+}", method=RequestMethod.GET)
	public ModelAndView validAccept(@PathVariable String param, HttpServletRequest request) throws Exception{
		try{

			Key key = new SecretKeySpec(keyValue, "AES");
			Cipher c = Cipher.getInstance("AES");
			c.init(Cipher.DECRYPT_MODE, key);
			new org.apache.commons.codec.binary.Base64();
			byte[] decordedvalue = Base64.decodeBase64(param);
			byte[] decValue = c.doFinal(decordedvalue);
			
			String decryptedValue = new String(decValue);
			if(decryptedValue.equals(eMail)){
				ModelAndView mav = new ModelAndView();
				mav.setViewName("eMailValidationSuccess");
				mav.addObject("eMail", decryptedValue);
				return mav;
			}else{
				return null;
			}
			
		}catch(Exception e){
			throw e;
		}
	}
}