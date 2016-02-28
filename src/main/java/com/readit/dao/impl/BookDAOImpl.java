package com.readit.dao.impl;

import com.readit.dao.BookDAO;
import com.readit.entity.Author;
import com.readit.entity.Book;
import lombok.NoArgsConstructor;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("unchecked")
@Repository
@Transactional
@NoArgsConstructor
public class BookDAOImpl implements BookDAO {

    @Autowired
    SessionFactory sessionFactory;

    public List<Book> list() {
        return sessionFactory.getCurrentSession().createQuery("from Book").list();
    }

    public Book get(Long id) {
        return sessionFactory.getCurrentSession().get(Book.class,id);
    }

    public List<Book> getByAuthor(Long authorId) {
        return sessionFactory.getCurrentSession().createQuery("select b from Book b join b.authors a where a.id = :id").setParameter("id", authorId).list();
    }

    public List<Book> getFromCategory(Long categoryId) {
        return sessionFactory.getCurrentSession().createQuery("select b from Book b join b.categories c where c.id = :id").setParameter("id", categoryId).list();
    }

    public void saveOrUpdate(Book book) {

    }

    public void delete(Long id) {

    }
}
