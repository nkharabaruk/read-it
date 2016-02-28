package com.readit.controller;

import com.readit.entity.Author;
import com.readit.entity.Book;
import com.readit.entity.Category;
import com.readit.service.AuthorService;
import com.readit.service.BookService;
import com.readit.service.CategoryService;
import com.readit.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@EnableWebMvc
@RestController
public class HomeController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private FilesService filesService;

    @RequestMapping("/list")
    public ModelAndView listBooks() {
        List<Book> books = bookService.getAll();
        ModelAndView model = new ModelAndView("BookList");
        model.addObject("bookList", books);
        return model;
    }

    @RequestMapping(value = {"/admin**", "/dba**"}, method = RequestMethod.GET)
    public ModelAndView adminPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Hello World");
        model.addObject("message", "This is protected page - Admin Page!");
        model.setViewName("admin");
        return model;
    }


    @RequestMapping("/categories")
    public ModelAndView listCategories() {
        List<Category> rootCategories = categoryService.getRootCategories();
        ModelAndView model = new ModelAndView("CategoryList");
        model.addObject("rootCategories", rootCategories);
        return model;
    }

    @RequestMapping("/category/{id}")
    public ModelAndView getCategory(@PathVariable long id) {
        Category category = categoryService.getById(id);
        ModelAndView model = new ModelAndView("CategoryInf");
//        Set<Book> books = bookService.getFromCategoryAndParents(id);
        model.addObject(category);
//        model.addObject("books",books);
        return model;
    }

    @RequestMapping("/authors")
    public ModelAndView handleRequest() {
        List<Author> listAuthors = authorService.getAll();
        ModelAndView model = new ModelAndView("AuthorList");
        model.addObject("authorList", listAuthors);
        return model;
    }

    @RequestMapping("/book/{id}")
    public ModelAndView viewBookInfo(@PathVariable long id) {
        Book book = bookService.getById(id);
        ModelAndView model = new ModelAndView("BookInf");
        List<Category> categories = new ArrayList<Category>();
        for (Category c : book.getCategories()) {
            categories.add(c);
            categories.addAll(categoryService.getAscendants(c.getId()));
        }
        Collections.reverse(categories);
        model.addObject("book", book);
        model.addObject("categories",categories);
        return model;
    }

    @RequestMapping("/author/{id}")
    public ModelAndView viewAuthorInfo(@PathVariable long id) {
        Author author = authorService.getById(id);
        ModelAndView model = new ModelAndView("AuthorInf");
        model.addObject(author);
        return model;
    }

    @RequestMapping("/images/**")
    public void getImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName = request.getServletPath().substring("/images/".length(), request.getServletPath().length());
        byte [] imageBytes = filesService.getImageByteArray(fileName);
        response.setContentType("image/jpeg");
        response.setContentLength(imageBytes.length);
        response.getOutputStream().write(imageBytes);
    }
}