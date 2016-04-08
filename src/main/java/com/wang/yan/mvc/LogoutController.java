package com.wang.yan.mvc;

import com.wang.yan.mvc.model.Login;
import com.wang.yan.mvc.model.Message;
import com.wang.yan.mvc.service.LoginService;
import com.wang.yan.mvc.utils.Utils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.method.HandlerMethod;
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
@RequestMapping("/logout")
public class LogoutController {

	private static final Logger logger = Logger.getLogger(LogoutController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String logout(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("path : " + request.getContextPath());

		HttpSession session = request.getSession();
		if (session != null) {
			session.invalidate();
		}
		if (session == null) {
			logger.info("This will never happen!");
		}

		model.addAttribute("message", "Please Sign In !");
		try {
			response.sendRedirect(Utils.computePath(request));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}