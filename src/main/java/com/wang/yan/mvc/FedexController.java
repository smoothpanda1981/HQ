package com.wang.yan.mvc;

import com.wang.yan.mvc.model.Fedex;
import com.wang.yan.mvc.service.FedexService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/invoices")
public class FedexController {

	private static final Logger logger = Logger.getLogger(FedexController.class);

	@Autowired
	private FedexService fedexService;

	@RequestMapping(value= "fedex", method = RequestMethod.GET)
	public String fedexMainPage(ModelMap model) {
		model.addAttribute("message", "CRUD Fedex");
		model.addAttribute("createFedex", new Fedex());
		List< String> accounts = new ArrayList<>();
		model.addAttribute("accounts", getAccountsForForm());
		try {
			List<Fedex> fedexList = fedexService.getListFedex();
			model.addAttribute("fedexList", fedexList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "fedex";
	}

	@RequestMapping(value= "fedex", method=RequestMethod.POST)
	public String createFedex(ModelMap model, @ModelAttribute("createFedex")Fedex fedex) {
		try {
			logger.info("in addFedex");
			fedexService.addFedex(fedex);
			model.addAttribute("saveMessage", "Saved");
			List<Fedex> fedexList = fedexService.getListFedex();
			model.addAttribute("fedexList", fedexList);
			model.addAttribute("accounts", getAccountsForForm());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "fedex";
	}

	private List <String> getAccountsForForm() {
		List< String> accounts = new ArrayList<>();
		accounts.add("--- Select ---");
		accounts.add("8222-1264-6");
		accounts.add("7032-7650-4");
		return accounts;
	}
}