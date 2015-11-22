package com.wang.yan.mvc;

import com.wang.yan.mvc.dao.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/daigou")
public class DaigouController {

	@Autowired
	@Qualifier("mailSender")
	private JavaMailSender mailSender;

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

		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo("daigouswitzerland@gmail.com");
		email.setSubject("Requête de " + customer.getEmail());
		email.setText(customer.getContent());

		// sends the e-mail
		mailSender.send(email);

		return "daigouSuccess";
	}
}