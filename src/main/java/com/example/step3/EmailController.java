package com.example.step3;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import database.ValidationDAO;

@Controller
public class EmailController {
	private ValidationDAO vd = new ValidationDAO();

	@Inject
	private DataSource ds;
	
	
	/* 이메일 검증 단계 */
	@RequestMapping(value = "/eMailValidation", method = RequestMethod.POST)
	@ResponseBody
	public String eMailValidation(@RequestBody String body, HttpServletRequest request) throws Exception {
		String response = request.getRequestURL().substring(0,
				request.getRequestURL().indexOf(request.getRequestURI()));

		String eMail = body;
		System.out.println(eMail.hashCode());
		if (vd.insertEmail(ds, eMail, Integer.toString(eMail.hashCode())).equals("success")) {
			return response + "/validation/" + Integer.toString(eMail.hashCode());
		} else {
			return "error";
		}

	}

	/* URL과 이메일 일치 여부 검사 */
	@RequestMapping(value = "/validation/{param:.+}", method = RequestMethod.GET)
	public ModelAndView validAccept(@PathVariable String param, HttpServletRequest request) throws Exception {
		
		String eMail = vd.checkEmail(ds, param);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("eMailValidationSuccess");
		mav.addObject("eMail", eMail);
		return mav;
	}
}