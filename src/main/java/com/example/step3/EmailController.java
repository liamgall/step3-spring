package com.example.step3;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.step3.DAO.EmailDAO;
import com.example.step3.model.Email;

@Controller
public class EmailController {
	@Autowired
	private SqlSession sqlSession;
	
	
	
	/* 이메일 검증 단계 */
	@RequestMapping(value = "/eMailValidation", method = RequestMethod.POST)
	@ResponseBody
	public String eMailValidation(@RequestBody String body, HttpServletRequest request) throws Exception {
		
		/* url을 얻기 위함 */
		StringBuffer url = request.getRequestURL();
		String uri = request.getRequestURI();
		String response = url.substring(0, url.indexOf(uri));
		
		/* dao interface에 mapper 연동 */
		EmailDAO dao = sqlSession.getMapper(EmailDAO.class);
		try{
			Email email = new Email(body, body.hashCode());
			dao.insertDAO(email);
			return response + "/validation/" + Integer.toString(body.hashCode());
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}

	}

	/* URL과 이메일 일치 여부 검사 */
	@RequestMapping(value = "/validation/{param:.+}", method = RequestMethod.GET)
	public ModelAndView validAccept(@PathVariable int param, HttpServletRequest request) throws Exception {

		ModelAndView mav = new ModelAndView();
		
		/* dao interface에 mapper 연동 */
		EmailDAO dao = sqlSession.getMapper(EmailDAO.class);
		String email = dao.findDAO(param);
		
		/* 데이터베이스에 hashcode가 존재하지 않는 경우 */
		if(email != null){
			
			mav.setViewName("eMailValidationSuccess");
			mav.addObject("eMail",  email);
			return mav;
		}else{
			mav.setViewName("eMailValidationFail");
			return mav;
		}
	}
}