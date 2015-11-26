package com.wang.yan.mvc;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	public SessionFactory sessionFactory;

	@RequestMapping(value = "jdbc", method = RequestMethod.GET)
	public String dashBoardPage(ModelMap model) {
		model.addAttribute("message", "Dashboard");
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/classicmodels", "root", "ouafahwafa79");

			ResultSet rs1 = connection.getMetaData().getCatalogs();

			while (rs1.next()) {
				System.out.println("TABLE_CAT = " + rs1.getString("TABLE_CAT") );
				rs1.getMetaData();
			}

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

	@RequestMapping(value="hibernate", method = RequestMethod.GET)
	public String dashBoardUsingHibernatePage(ModelMap model) {
		model.addAttribute("message", "Dashboard Hibernate");
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select customerName from customers where customerNumber = :customerNumber")
				.setParameter("customerNumber", "103");
		List result = query.list();
		if (result.size() == 1) {
			System.out.println(result.get(0).toString());
		}
		return "dashboard_hibernate";
	}
}