package com.wang.yan.mvc;

import com.wang.yan.mvc.utils.Utils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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