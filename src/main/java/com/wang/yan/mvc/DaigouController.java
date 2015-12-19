package com.wang.yan.mvc;

import com.wang.yan.mvc.model.Customer;
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

		SimpleMailMessage emailToMe = new SimpleMailMessage();
		emailToMe.setTo("daigouswitzerland@gmail.com");
		emailToMe.setSubject("Commande de " + customer.getEmail());
		emailToMe.setText(customer.getContent());

		// sends the e-mail
		mailSender.send(emailToMe);

		SimpleMailMessage emailToCustomer = new SimpleMailMessage();
		emailToCustomer.setTo(customer.getEmail());
		emailToCustomer.setSubject("Confirmation - no reply");
		emailToCustomer.setText("Nous allons vous répondre très rapidement. Merci de votre confiance !");

		// sends the e-mail
		mailSender.send(emailToCustomer);

		return "daigouSuccess";
	}
}