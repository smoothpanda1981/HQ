package com.wang.yan.mvc;

import com.wang.yan.mvc.model.Login;
import com.wang.yan.mvc.model.Message;
import com.wang.yan.mvc.model.Signup;
import com.wang.yan.mvc.service.LoginService;
import com.wang.yan.mvc.service.SignupService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("signup")
public class SingupController {

	private static final Logger logger = Logger.getLogger(SingupController.class);

	@Autowired
	private LoginService loginService;

	@Autowired
	private SignupService signupService;

	@RequestMapping(method = RequestMethod.GET)
	public String showForm(ModelMap model, HttpServletRequest request) {

		logger.info("************************************");
		model.addAttribute("signup", new Signup());

		return "signup";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String executeLogin(ModelMap model, HttpServletRequest request) {
		logger.debug(request.getParameter("company"));
		logger.debug(request.getParameter("first_name"));
		logger.debug(request.getParameter("last_name"));
		logger.debug(request.getParameter("email"));
		logger.debug(request.getParameter("street"));
		logger.debug(request.getParameter("city"));
		logger.debug(request.getParameter("state"));
		logger.debug(request.getParameter("zip"));
		logger.debug(request.getParameter("phone"));
		logger.debug(request.getParameter("username"));

		Signup signup = new Signup();
		signup.setCompany(request.getParameter("company"));
		signup.setFirst_name(request.getParameter("first_name"));
		signup.setLast_name(request.getParameter("last_name"));
		signup.setEmail(request.getParameter("email"));
		signup.setStreet(request.getParameter("street"));
		signup.setCity(request.getParameter("city"));
		signup.setState(request.getParameter("state"));
		signup.setZip(request.getParameter("zip"));
		signup.setPhone(request.getParameter("phone"));
		signup.setPassword(request.getParameter("password"));
		signup.setPassword_confirmation(request.getParameter("password_confirmation"));
		signup.setUsername(request.getParameter("username"));

		try {
			Login login = loginService.addNewLogin(request.getParameter("username"), request.getParameter("password"));
			logger.info("login id after saved in DB : " + login.getId());
			signup.setLogin(login);
			Signup signupResponse = signupService.addNewSignup(signup);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return "signupSuccess";
	}
}