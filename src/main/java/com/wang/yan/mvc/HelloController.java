package com.wang.yan.mvc;

import com.wang.yan.mvc.model.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
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

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model, HttpServletRequest request) {

		model.addAttribute("message", "Hello " + message.getContent() + " !");
		logger.info("INFO LOG");
		logger.debug("DEBUG LOG");
		Map<RequestMappingInfo, HandlerMethod> map = new HashMap<RequestMappingInfo, HandlerMethod>();
		map = handlerMapping.getHandlerMethods();
		Set<RequestMappingInfo> keys = map.keySet();
		model.addAttribute("endPoints", keys);
		String url = request.getRequestURL().toString();
		String urlReplace = url.replace("8080", "8161");
		String urlRepleaceHQ = urlReplace.replace("HQ", "");
		String finalUrl = urlRepleaceHQ + "admin/index.jsp";
		model.addAttribute("activemq", finalUrl);
		model.addAttribute("wiki", "https://ywangubuntu.wiki.zoho.com/How-to-set-up-a-ubuntu-server.html");
		return "hello";
	}
}