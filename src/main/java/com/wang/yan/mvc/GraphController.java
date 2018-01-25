package com.wang.yan.mvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wang.yan.mvc.model.bitstamp.*;
import com.wang.yan.mvc.service.BitstampService;
import com.wang.yan.mvc.service.FedexService;
import com.wang.yan.mvc.utils.BitstampUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/graph")
public class GraphController {
	private static final Logger logger = Logger.getLogger(GraphController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String moneyPage(ModelMap model) throws InterruptedException {
		model.addAttribute("message", "Graph");

		model.addAttribute("data", "[\n" +
				"                ['Year', 'Sales', 'Expenses'],\n" +
				"                ['2004',  1000,      400],\n" +
				"                ['2005',  1170,      460],\n" +
				"                ['2006',  660,       1120],\n" +
				"                ['2007',  1030,      540],\n" +
				" 				 ['2008',  930,      440]\n" +
				"            ]");
		return "graph";
	}

}