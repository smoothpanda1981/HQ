package com.wang.yan.mvc;

import com.wang.yan.mvc.model.Login;
import com.wang.yan.mvc.model.Message;
import com.wang.yan.mvc.service.LoginService;
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

		logger.info("************************************");
		if (request.getSession().getAttribute("login") == null) {
			logger.info("no session login");
			model.addAttribute("message", "Please Sign In !");
			model.addAttribute("login", new Login());
			return "login";
		} else {
			logger.info("with session login");
			genetateHelloData(model, request);
			return "hello";
		}
	}

	@RequestMapping(method=RequestMethod.POST)
	public String executeLogin(ModelMap model, HttpServletRequest request, @RequestParam String username, @RequestParam String password) {
		String view = "";

		Login login = null;
		try {
			login = loginService.isValidUser(username, password);
			request.getSession().setAttribute("login", login);
			if (login == null) {
				logger.info("login is null");
				model.addAttribute("message", "Please Sign In !");
				model.addAttribute("login", new Login());
				model.addAttribute("error_message", "Invalid username or password !");
				view = "login";
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
		String requestUrl = request.getRequestURL().toString();
		if (String.valueOf(requestUrl.charAt(requestUrl.length()-1)).equals("/")) {
			requestUrl = requestUrl.substring(0, requestUrl.length()-1);
		}
		model.addAttribute("requestUrl", requestUrl);
	}
}