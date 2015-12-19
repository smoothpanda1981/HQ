package com.wang.yan.mvc;

import com.wang.yan.mvc.model.Student;
import com.wang.yan.mvc.model.User;
import com.wang.yan.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ws")
public class WebServiceController {

	@Autowired
	public UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String printWS(ModelMap model, HttpServletRequest request) {

		List<String> urlList = new ArrayList<String>();
		urlList.add(request.getRequestURL().toString() + "/student");
		urlList.add(request.getRequestURL().toString() + "/students");
		urlList.add(request.getRequestURL().toString() + "/user/");
		urlList.add(request.getRequestURL().toString() + "/user/1");

		model.addAttribute("message", "Web Service");
		model.addAttribute("urlList", urlList);
		return "ws";
	}

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

	//-------------------Retrieve All Users--------------------------------------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.findAllUsers();
		if(users.isEmpty()){
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}


	//-------------------Retrieve Single User--------------------------------------------------------

	@RequestMapping(value = "user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("id") long id) {
		System.out.println("Fetching User with id " + id);
		User user = userService.findById(id);
		if (user == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}



	//-------------------Create a User--------------------------------------------------------

	@RequestMapping(value = "user/", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody User user,    UriComponentsBuilder ucBuilder) {
		System.out.println("Creating User " + user.getName());

		if (userService.isUserExist(user)) {
			System.out.println("A User with name " + user.getName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}


	//------------------- Update a User --------------------------------------------------------

	@RequestMapping(value = "user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		System.out.println("Updating User " + id);

		User currentUser = userService.findById(id);

		if (currentUser==null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		currentUser.setName(user.getName());
		currentUser.setAge(user.getAge());
		currentUser.setSalary(user.getSalary());

		userService.updateUser(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	//------------------- Delete a User --------------------------------------------------------

	@RequestMapping(value = "user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting User with id " + id);

		User user = userService.findById(id);
		if (user == null) {
			System.out.println("Unable to delete. User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		userService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}


	//------------------- Delete All Users --------------------------------------------------------

	@RequestMapping(value = "user/", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteAllUsers() {
		System.out.println("Deleting All Users");

		userService.deleteAllUsers();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
}