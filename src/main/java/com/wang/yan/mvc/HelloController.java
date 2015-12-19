package com.wang.yan.mvc;

import com.wang.yan.mvc.dao.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HelloController {

	private static final Logger logger = Logger.getLogger(HelloController.class);

	@Autowired
	private Message message;

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {

		model.addAttribute("message", "Hello " + message.getContent() + " !");
		logger.info("INFO LOG");
		logger.debug("DEBUG LOG");
		return "hello";
	}
}