package com.wang.yan.mvc;

import com.wang.yan.mvc.model.Login;
import com.wang.yan.mvc.model.Message;
import com.wang.yan.mvc.service.LoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
@SessionAttributes("login")
@RequestMapping("/")
public class HelloController {

	private static final Logger logger = Logger.getLogger(HelloController.class);

	@Autowired
	private RequestMappingHandlerMapping handlerMapping;

	@Autowired
	private Message message;

	@Autowired
	private LoginService loginService;

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model, HttpServletRequest request) {

		if (request.getSession().getAttribute("login") == null) {
			model.addAttribute("message", "Please Sign In !");
			return "login";
		} else {
			genetateHelloData(model, request);
			return "hello";
		}
	}

	@RequestMapping(method=RequestMethod.POST)
	public String executeLogin(ModelMap model, HttpServletRequest request, @RequestParam String username, @RequestParam String password) {
		String view = "login";

		Login login = null;
		try {
			login = loginService.isValidUser(username, password);
			request.getSession().setAttribute("login", login);
			if (login == null) {
				logger.info("login is null");
				model.addAttribute("message", "Please Sign In !");
				model.addAttribute("error_message", "Invalid username or password !");

			} else {
				model.addAttribute("message", "Hello " + message.getContent() + " !");
				genetateHelloData(model, request);

				view = "hello";
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return view;
	}

	private void genetateHelloData(ModelMap model, HttpServletRequest request) {
		model.addAttribute("message", "Hello " + message.getContent() + " !");
		logger.info("INFO LOG");
		logger.debug("DEBUG LOG");
		Map<RequestMappingInfo, HandlerMethod> map = new HashMap<RequestMappingInfo, HandlerMethod>();
		map = handlerMapping.getHandlerMethods();
		Set<RequestMappingInfo> keys = map.keySet();
		model.addAttribute("endPoints", keys);
		String url = request.getRequestURL().toString();
		String urlReplace = url.replace("8080", "8161");
		String urlReplaceHQ = urlReplace.replace("/HQ", "");
		String finalUrl = urlReplaceHQ + "admin/index.jsp";
		model.addAttribute("activemq", finalUrl);
	}
}