package com.readit.dao.impl;

import com.readit.dao.AuthorDAO;
import com.readit.entity.Author;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class AuthorDAOImpl implements AuthorDAO {

    @Autowired
    SessionFactory sessionFactory;

    public AuthorDAOImpl() {
    }

    public List<Author> list() {
        return sessionFactory.getCurrentSession().createQuery("from Author ").list();
    }

    public Author get(long id) {
        return sessionFactory.getCurrentSession().get(Author.class,id);
    }

    public void saveOrUpdate(Author author) {

    }

    public void delete(long id) {

    }
}
