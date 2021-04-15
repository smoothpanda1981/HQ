package com.wang.yan.mvc;

import com.wang.yan.mvc.model.Login;
import com.wang.yan.mvc.model.Message;
import com.wang.yan.mvc.service.FinDataService;
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
@RequestMapping("/findata")
public class BuySellBTCStragegyController {

	private static final Logger logger = Logger.getLogger(BuySellBTCStragegyController.class);

	@Autowired
	private FinDataService finDataService;

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model, HttpServletRequest request) {

		logger.info("************************************");
//		if (request.getSession().getAttribute("login") == null) {
//			logger.info("no session login");
//			model.addAttribute("message", "Please Sign In !");
//			model.addAttribute("login", new Login());
//			return "login";
//		} else {
//			logger.info("with session login");
			//genetateHelloData(model, request);
			return "findata";
//		}
	}
}