package com.wang.yan.mvc;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/bitstamp")
public class BitstampController {
	private static final Logger logger = Logger.getLogger(BitstampController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String bitStampPage(ModelMap model) {
		model.addAttribute("message", "Bitstamp");

		return "bitstamp";
	}
}