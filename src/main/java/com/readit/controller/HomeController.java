package com.readit.controller;

import com.readit.entity.Author;
import com.readit.entity.Book;
import com.readit.entity.Category;
import com.readit.service.AuthorService;
import com.readit.service.BookService;
import com.readit.service.CategoryService;
import com.readit.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Controller
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
        Set<Book> books = category.getBooks();
        ModelAndView model = new ModelAndView("CategoryInf");
        model.addObject(category);
        model.addObject("books",books);
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
        model.addObject(book);
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