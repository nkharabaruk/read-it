package com.readit.dao.impl;

import com.readit.dao.CategoryDAO;
import com.readit.entity.Book;
import com.readit.entity.Category;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
@Transactional
@NoArgsConstructor
public class CategoryDAOImpl implements CategoryDAO {

    @Autowired
    SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public List<Category> list() {
        return sessionFactory.getCurrentSession().createQuery("from Category ").list();
    }

    public Category get(Long id) {
        return sessionFactory.getCurrentSession().get(Category.class,id);
    }

    public void saveOrUpdate(Category category) {

    }

    public void delete(Long id) {

    }

    @SuppressWarnings("unchecked")
    public List<Category> getRootCategories() {
        return sessionFactory.getCurrentSession().createQuery("select c from Category c where c.parent = null").list();
    }

    @SuppressWarnings("unchecked")
    public List<Book> getBooksFromCategory(Long categoryId) {
        return sessionFactory.getCurrentSession().createQuery("select b from Book b join b.categories c where c.id = :id").setParameter("id", categoryId).list();
    }
}
