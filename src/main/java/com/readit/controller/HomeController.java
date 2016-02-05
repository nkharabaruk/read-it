package com.readit.controller;

import com.readit.dao.BookDAO;
import com.readit.dao.AuthorDAO;
import com.readit.dao.CategoryDAO;
import com.readit.dto.CategoryDTO;
import com.readit.entity.Author;
import com.readit.entity.Book;
import com.readit.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@PropertySource(value = "classpath:db.properties")
public class HomeController {
    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private AuthorDAO authorDAO;

    @Autowired
    private Environment env;

    @RequestMapping("/list")
    public ModelAndView listBooks() {
        Map<Book, List<Author>> listBooks = new HashMap<Book, List<Author>>();

        List<Book> books = bookDAO.list();
        for (Book book : books) {
            listBooks.put(book, bookDAO.getAuthors(book.getId()));
        }
        ModelAndView model = new ModelAndView("BookList");
        model.addObject("bookList", listBooks);
        return model;
    }

    @RequestMapping("/categories")
    public ModelAndView listCategories() {
        List<Category> rootCategories = categoryDAO.getRootCategories();
        ModelAndView model = new ModelAndView("CategoryList");
        model.addObject("rootCategories", rootCategories);
        return model;
    }

    @RequestMapping("/category/{id}")
    public ModelAndView getCategory(@PathVariable long id) {
        List<Book> books = categoryDAO.getBooks(id);
        ModelAndView model = new ModelAndView("CategoryInf");
        model.addObject("books",books);
        return model;
    }

    @RequestMapping("/authors")
    public ModelAndView handleRequest() {
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
        return model;
    }

    @RequestMapping("/images/**")
    public void getImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName = request.getServletPath().substring("/images/".length(), request.getServletPath().length());
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