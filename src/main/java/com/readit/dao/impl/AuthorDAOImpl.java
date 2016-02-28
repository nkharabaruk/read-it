package com.readit.dao.impl;

import com.readit.dao.AuthorDAO;
import com.readit.entity.Author;
import com.readit.entity.Book;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("unchecked")
@Repository
@Transactional
@NoArgsConstructor
public class AuthorDAOImpl implements AuthorDAO {

    @Autowired
    SessionFactory sessionFactory;

    public List<Author> list() {
        return sessionFactory.getCurrentSession().createQuery("from Author ").list();
    }

    public Author get(Long id) {
        return sessionFactory.getCurrentSession().get(Author.class,id);
    }

    public List<Author> getBookAuthors(Long bookId) {
        return sessionFactory.getCurrentSession().createQuery("select a from Author a join Book b where b.id = :id").setParameter("id", bookId).list();
    }

    public void saveOrUpdate(Author author) {

    }

    public void delete(Long id) {

    }
}
