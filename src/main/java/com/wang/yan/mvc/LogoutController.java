package com.wang.yan.mvc;

import com.wang.yan.mvc.model.Login;
import com.wang.yan.mvc.utils.Utils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LogoutController {

	private static final Logger logger = Logger.getLogger(LogoutController.class);

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutFirstLayer(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("path : " + request.getContextPath());

		HttpSession session = request.getSession();
		if (session != null) {
			session.invalidate();
		}
		if (session == null) {
			logger.info("This will never happen!");
		}

		model.addAttribute("message", "Please Sign In !");
		model.addAttribute("login", new Login());
		try {
			response.sendRedirect(Utils.computePath(request));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value="/*/logout", method = RequestMethod.GET)
	public String logoutSecondLyer(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		return logoutFirstLayer(model, request, response);
	}

	@RequestMapping(value="/*/*/logout", method = RequestMethod.GET)
	public String logoutThridLyer(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		return logoutFirstLayer(model, request, response);
	}

	@RequestMapping(value="/*/*/*/logout", method = RequestMethod.GET)
	public String logoutFouthLyer(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		return logoutFirstLayer(model, request, response);
	}
}