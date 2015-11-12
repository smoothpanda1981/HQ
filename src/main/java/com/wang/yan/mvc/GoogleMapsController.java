package com.wang.yan.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/maps")
public class GoogleMapsController {

	@RequestMapping(method = RequestMethod.GET)
	public String mapsPage(ModelMap model) {
		model.addAttribute("message", "Google Maps");
		return "maps";
	}
}