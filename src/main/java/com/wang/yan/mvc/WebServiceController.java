package com.wang.yan.mvc;

import com.wang.yan.mvc.dao.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ws")
public class WebServiceController {

	@RequestMapping(value = "student")
	public @ResponseBody Student getStudent() {
		Student student = new Student();
		student.setId(1);
		student.setFirstName("Yan");
		student.setLastName("Wang");
		student.setEmail("yan.wang.ch@gmail.com");
		student.getPhone("+41762230758");
		return student;
	}

	@RequestMapping(value = "students")
	public @ResponseBody List<Student> getListOfStudents() {
		List<Student> students = new ArrayList<Student>();

		Student student = new Student();
		student.setId(1);
		student.setFirstName("Yan");
		student.setLastName("Wang");
		student.setEmail("yan.wang.ch@gmail.com");
		student.getPhone("+41762230758");

		students.add(student);

		student.setId(2);
		student.setFirstName("Yan2");
		student.setLastName("Wang2");
		student.setEmail("yan2.wang2.ch@gmail.com");
		student.getPhone("+41762230758");

		students.add(student);

		return students;
	}
}