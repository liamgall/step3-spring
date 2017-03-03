package com.example.step3;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.step3.filter.CaptchaChecker;
import com.example.step3.filter.FileTransfer;
import com.example.step3.model.User;

import database.RegisterDAO;
import net.minidev.json.parser.ParseException;

@Controller
public class UserController {

	private RegisterDAO rd = new RegisterDAO();

	@Inject
	private DataSource ds;
	
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
	public ModelAndView doLogin(@Valid @ModelAttribute("userForm") User userForm, BindingResult result,
			Map<String, Object> model, @RequestParam("captcha") String captcha,
			@RequestParam("uploadFile") MultipartFile file, HttpServletRequest request) throws ParseException, IllegalStateException, IOException, SQLException {
		
		ModelAndView mav = new ModelAndView();
		CaptchaChecker cc = new CaptchaChecker(captcha);
		String path = request.getSession().getServletContext().getRealPath("resources/attatchments");
		FileTransfer ft = new FileTransfer(file, path);
		
		if (cc.getResult().get("success").toString().equals("false")) {
			mav.setViewName("JoinForm");
		} else {
			/* 회원가입 정보들이 양식에 맞지 않은 경우 */
			if (result.hasErrors()) {
				mav.setViewName("JoinForm");
			}
			else{
				/* 양식에 맞게 작성한 경우 파일 업로드 */
				ft.uploadFile();
				mav.addObject("fileName", file.getOriginalFilename());
				mav.setViewName("JoinSuccess");
				rd.insertDB(ds, userForm, file.getOriginalFilename());
			}
		}
		return mav;

	}
}