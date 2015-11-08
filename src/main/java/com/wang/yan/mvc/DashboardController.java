package com.wang.yan.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	@RequestMapping(method = RequestMethod.GET)
	public String dashBoardPage(ModelMap model) {
		model.addAttribute("message", "Dashboard");
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/world", "root", "ouafahwafa79");

			DatabaseMetaData md = connection.getMetaData();
			ResultSet rs = md.getTables(null, null, "%", null);
			List<String> tableList = new ArrayList<String>();
			while (rs.next()) {
				System.out.println(rs.getString(3));
				tableList.add(rs.getString(3));
			}
			model.addAttribute("tableList", tableList);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				model.addAttribute("connection_status", "OK");
			} else {
				model.addAttribute("connection_status", "KO");
			}
		}
		return "dashboard";
	}
}