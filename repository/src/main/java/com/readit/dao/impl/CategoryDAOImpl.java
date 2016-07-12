package com.readit.dao.impl;

import com.readit.dao.CategoryDAO;
import com.readit.entity.Category;
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
public class CategoryDAOImpl implements CategoryDAO {

    @Autowired
    SessionFactory sessionFactory;

    public List<Category> list() {
        return sessionFactory.getCurrentSession().createQuery("from Category ").list();
    }

    public Category get(Long id) {
        return sessionFactory.getCurrentSession().get(Category.class, id);
    }

    public List<Category> getRootCategories() {
        return sessionFactory.getCurrentSession().createQuery("select c from Category c where c.parent = null").list();
    }

    public List<Category> getChildren(Long categoryId) {
        return sessionFactory.getCurrentSession().createQuery("select c from Category c where c.parent = :id").setParameter("id", categoryId).list();
    }

    public void saveOrUpdate(Category category) {

    }

    public void delete(Long id) {

    }
}
