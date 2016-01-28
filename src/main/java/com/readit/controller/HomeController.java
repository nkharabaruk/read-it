package com.readit.controller;

import com.readit.dao.BookDAO;
import com.readit.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private BookDAO bookDAO;

    @RequestMapping("/list")
    public ModelAndView listBooks() throws Exception {
        List<Book> listBooks = bookDAO.list();
        ModelAndView model = new ModelAndView("BookList");
        model.addObject("bookList", listBooks);
        return model;
    }
    @RequestMapping("/book/{id}")
    public ModelAndView viewBookInfo(@PathVariable long id) {
        Book book = bookDAO.get(id);
        ModelAndView model = new ModelAndView("BookInf");
        model.addObject(book);
        return model;
    }

}
