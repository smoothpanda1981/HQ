package com.wang.yan.mvc;

import com.wang.yan.mvc.model.Customer;
import org.apache.log4j.Logger;
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

	private static final Logger logger = Logger.getLogger(DaigouController.class);

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

		logger.info(customer.getFname());
		logger.info(customer.getLname());
		logger.info(customer.getPhone());
		logger.info(customer.getEmail());
		logger.info(customer.getContent());


		SimpleMailMessage emailToMe = new SimpleMailMessage();
		emailToMe.setTo("daigouswitzerland@gmail.com");
		emailToMe.setSubject("Order of : " + customer.getFname() + " " + customer.getLname() + " - " + customer.getEmail() + " (" + customer.getPhone() + ")");
		emailToMe.setText(customer.getContent());

		// sends the e-mail
		mailSender.send(emailToMe);

		SimpleMailMessage emailToCustomer = new SimpleMailMessage();
		emailToCustomer.setTo(customer.getEmail());
		emailToCustomer.setSubject("Confirmation - please do not reply");
		emailToCustomer.setText("We are going to reply you ASAP. Thanks for your order(s) !");

		// sends the e-mail
		mailSender.send(emailToCustomer);

		return "daigouSuccess";
	}
}