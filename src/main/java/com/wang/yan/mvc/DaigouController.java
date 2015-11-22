package com.wang.yan.mvc;

import com.wang.yan.mvc.dao.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/daigou")
public class DaigouController {

	@RequestMapping(method = RequestMethod.GET)
	public String daigouPage(ModelMap model) {
		model.addAttribute("message", "Daigou");
		model.addAttribute("customer", new Customer());
		return "daigou";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String commandPage(@ModelAttribute("customer")Customer customer, ModelMap model) {
		model.addAttribute("message", "You will receive our reply in your mailbox : " + customer.getEmail() + " !");
		System.out.println(customer.getContent());

		System.out.println(customer.getEmail());

		return "daigouSuccess";
	}
}