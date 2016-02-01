package com.readit.controller;

import com.readit.dao.BookDAO;
import com.readit.dao.AuthorDAO;
import com.readit.entity.Author;
import com.readit.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@PropertySource(value = "classpath:db.properties")
public class HomeController {
    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private AuthorDAO authorDAO;

    @Autowired
    private Environment env;

    @RequestMapping("/list")
    public ModelAndView listBooks() throws Exception {
        Map<Book, List<Author>> listBooks = new HashMap<Book, List<Author>>();

        List<Book> books = bookDAO.list();
        for (Book book : books) {
            listBooks.put(book, bookDAO.getAuthors(book.getId()));
        }
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
        List<Author> authors = bookDAO.getAuthors(book.getId());
        model.addObject("authors", authors);
        return model;
    }

    @RequestMapping("/author/{id}")
    public ModelAndView viewAuthorInfo(@PathVariable long id) {
        Author author = authorDAO.get(id);
        ModelAndView model = new ModelAndView("AuthorInf");
        model.addObject(author);
        Thread t = new Thread();
        return model;
    }

    @RequestMapping("/images/**")
    public void getImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName = request.getRequestURI().substring(7, request.getRequestURI().length());
        String imagesPath;
        if (System.getenv("OPENSHIFT_DATA_DIR") != null) {
            imagesPath = System.getenv("OPENSHIFT_DATA_DIR") + "/images/";
        }
        else {
            imagesPath = env.getProperty("images.path");
        }
        InputStream inputStream = new FileInputStream(imagesPath + fileName);
        byte[] imageBytes = new byte[inputStream.available()];
        inputStream.read(imageBytes);
        response.setContentType("image/jpeg");
        response.setContentLength(imageBytes.length);
        response.getOutputStream().write(imageBytes);
    }
}