package com.readit.controller;

import com.readit.dao.BookDAO;
import com.readit.dao.AuthorDAO;
import com.readit.entity.Author;
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
    @Autowired
    private AuthorDAO authorDAO;

    @RequestMapping("/list")
    public ModelAndView listBooks() throws Exception {
        List<Book> listBooks = bookDAO.list();
        ModelAndView model = new ModelAndView("BookList");
        model.addObject("bookList", listBooks);
        return model;
    }
    @RequestMapping("/authors")
    public ModelAndView handleRequest() throws Exception {
        List<Author> listAuthors = authorDAO.list();
        ModelAndView model = new ModelAndView("AuthorList");
        model.addObject("authorList", listAuthors);
        return model;
    }
    @RequestMapping("/book/{id}")
    public ModelAndView viewBookInfo(@PathVariable long id) {
        Book book = bookDAO.get(id);
        ModelAndView model = new ModelAndView("BookInf");
        model.addObject(book);
        return model;
    }
    @RequestMapping("/author/{id}")
    public ModelAndView viewAuthorInfo(@PathVariable long id) {
        Author author = authorDAO.get(id);
        ModelAndView model = new ModelAndView("AuthorInf");
        model.addObject(author);
        return model;
    }
}
