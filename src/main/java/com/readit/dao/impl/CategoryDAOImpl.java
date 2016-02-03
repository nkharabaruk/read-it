package com.readit.dao.impl;

import com.readit.dao.CategoryDAO;
import com.readit.entity.Book;
import com.readit.entity.Category;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    @Autowired
    SessionFactory sessionFactory;

    public List<Category> list() {
        return sessionFactory.getCurrentSession().createQuery("from Category ").list();
    }

    public Category get(Long id) {
        return sessionFactory.getCurrentSession().get(Category.class,id);
    }

    public List<Book> getBooks(Long id) {
        return null;
    }

    public void saveOrUpdate(Category category) {

    }

    public void delete(Long id) {

    }
}
