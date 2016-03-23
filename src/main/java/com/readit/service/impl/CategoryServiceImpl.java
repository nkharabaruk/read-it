package com.readit.service.impl;

import com.readit.dao.BookDAO;
import com.readit.dao.CategoryDAO;
import com.readit.entity.Category;
import com.readit.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDAO categoryDAO;

    @Autowired
    BookDAO bookDAO;

    public List<Category> getAll() {
        return categoryDAO.list();
    }

    public Category getById(Long id) {
        return categoryDAO.get(id);
    }

    public Set<Category> getBookCategories(Long bookId) {
        return bookDAO.get(bookId).getCategories();
    }

    public List<Category> getRootCategories() {
        return categoryDAO.getRootCategories();
    }

    public List<Category> getAscendants(Long id) {
        List<Category> parents = new ArrayList<Category>();
        Category child = getById(id);
        while (child.getParent() != null) {
            Category parent = child.getParent();
            parents.add(parent);
            child = parent;
        }
        return parents;
    }

    public List<Category> getDescendants(Long id) {
        List<Category> children = new ArrayList<Category>();
        children.addAll(getById(id).getChildren());
        boolean repeat = true;
        while (repeat) {
            repeat = false;
            Set<Category> temp = new HashSet<Category>();
            for (Category c : children) {
                if (!children.containsAll(c.getChildren()) && c.getChildren().size() != 0) {
                    repeat = true;
                    temp.addAll(c.getChildren());
                }
            }
            children.addAll(temp);
        }
        return children;
    }
}
