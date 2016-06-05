package com.wang.yan.mvc;

import com.wang.yan.mvc.model.DeleteLogin;
import com.wang.yan.mvc.model.Login;
import com.wang.yan.mvc.model.UpdateLogin;
import com.wang.yan.mvc.service.LoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@Controller
@RequestMapping("/db")
public class DBLoginController {

	private static final Logger logger = Logger.getLogger(DBLoginController.class);

	@Autowired
	private LoginService loginService;

	@RequestMapping(value= "login", method = RequestMethod.GET)
	public String loginMainPage(ModelMap model) {
		model.addAttribute("message", "CRUD Login");
		model.addAttribute("createLogin", new Login());
		model.addAttribute("updateLogin", new UpdateLogin());
		model.addAttribute("deleteLogin", new DeleteLogin());
		return "db_login";
	}

	@RequestMapping(value= "login", method=RequestMethod.POST)
	public String createLogin(ModelMap model, HttpServletRequest request, @RequestParam String username, @RequestParam String password) {
		try {
			Login login = loginService.addLogin(username, password);
			model.addAttribute("create_login_message", login.getUsername());
			model.addAttribute("createLogin", new Login());
			model.addAttribute("updateLogin", new UpdateLogin());
			model.addAttribute("deleteLogin", new DeleteLogin());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "db_login";
	}

	@RequestMapping(value= "login", method=RequestMethod.PUT)
	public String updateLogin(ModelMap model, HttpServletRequest request, @RequestParam String current_username, @RequestParam String current_password, @RequestParam String new_username, @RequestParam String new_password) {
		try {
			Login login = loginService.updateExistingLogin(current_username, current_password, new_username, new_password);
			model.addAttribute("update_login_message", login.getUsername());
			model.addAttribute("createLogin", new Login());
			model.addAttribute("updateLogin", new UpdateLogin());
			model.addAttribute("deleteLogin", new DeleteLogin());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "db_login";
	}

	@RequestMapping(value= "login", method=RequestMethod.DELETE)
	public String deleteLogin(ModelMap model, HttpServletRequest request, @RequestParam String delete_username, @RequestParam String delete_password) {
		try {
			Login login = loginService.deleteExistingLogin(delete_username, delete_password);
			model.addAttribute("delete_login_message", login.getUsername());
			model.addAttribute("createLogin", new Login());
			model.addAttribute("updateLogin", new UpdateLogin());
			model.addAttribute("deleteLogin", new DeleteLogin());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "db_login";
	}

}