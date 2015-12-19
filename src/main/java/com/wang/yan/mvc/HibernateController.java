package com.wang.yan.mvc;

import com.wang.yan.mvc.model.Book;
import com.wang.yan.mvc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/hibernate")
public class HibernateController {

	@Autowired
	private BookService bookService;

	@RequestMapping(method = RequestMethod.GET)
	public String hibernatePage(ModelMap model) {
		model.addAttribute("message", "Hibernate");

		Book book = new Book();
		book.setName("La puissance de la joie");
		book.setAuthors("Frédéric Lenoir");
		book.setCode("9782213661353");
		book.setIsbn("2213661359");
		book.setPrice("18,00");
		book.setPublisher("Fayard");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		String dateInString = "14-10-2015 00:00:00";
		Date date = null;
		try {
			date = sdf.parse(dateInString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		book.setPublishedOn(new java.sql.Date(date.getTime()));

		bookService.saveBook(book);

		return "hello";
	}

}