package com.wang.yan.mvc;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/maps")
public class GoogleMapsController {
	private static final Logger logger = Logger.getLogger(GoogleMapsController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String mapsPage(ModelMap model) {
		model.addAttribute("message", "Google Maps");
		return "maps";
	}
}