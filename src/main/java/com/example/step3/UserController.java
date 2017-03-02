package com.example.step3;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.step3.Filter.CaptchaChecker;
import com.example.step3.Filter.FileTransfer;
import com.example.step3.model.User;

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
		
		/* 캡차 확인 */
		CaptchaChecker cc = new CaptchaChecker(captcha);
		FileTransfer ft = new FileTransfer(file);
		
		if (cc.getResult().get("success").toString().equals("false")) {
			return "JoinForm";
		} else {
			/* 회원가입 정보들이 양식에 맞지 않은 경우 */
			if (result.hasErrors()) {
				return "JoinForm";
			}
			/* 파일 업로드 */
			ft.uploadFile();
			return "JoinSuccess";
		}

	}
}