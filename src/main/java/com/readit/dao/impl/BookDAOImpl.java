package com.readit.dao.impl;

import com.readit.dao.BookDAO;
import com.readit.entity.Author;
import com.readit.entity.Book;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class BookDAOImpl implements BookDAO {

    @Autowired
    SessionFactory sessionFactory;

    public BookDAOImpl() {
    }

    @SuppressWarnings("unchecked")
    public List<Book> list() {
        return sessionFactory.getCurrentSession().createQuery("from Book").list();
    }

    public Book get(Long id) {
        return sessionFactory.getCurrentSession().get(Book.class,id);
    }

    @SuppressWarnings("unchecked")
    public List<Author> getAuthors(Long id) {
        return sessionFactory.getCurrentSession().createQuery("select a from Author a join a.books b where b.id = :id").setParameter("id", id).list();
    }

    public void saveOrUpdate(Book book) {

    }

    public void delete(Long id) {

    }
}
