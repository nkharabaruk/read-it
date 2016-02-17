package com.readit.service.impl;

import com.readit.dao.CategoryDAO;
import com.readit.entity.Category;
import com.readit.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDAO categoryDAO;

    public List<Category> getAll() {
        return categoryDAO.list();
    }

    public Category getById(Long id) {
        return categoryDAO.get(id);
    }

    public List<Category> getRootCategories() {
        return categoryDAO.getRootCategories();
    }

    public List<Category> getParents(Long id) {
        List<Category> parents = new ArrayList<Category>();
        Category child = getById(id);
        while (child.getParent() != null) {
            Category parent = child.getParent();
            parents.add(parent);
            child = parent;
        }
        return parents;
    }

    public List<Category> getChildren(Long id) {
        List<Category> children = new ArrayList<Category>();
        Category parent = getById(id);
        while (parent.getChildren() != null) {
            Category child = (Category) parent.getChildren();
            children.add(child);
            parent = child;
        }
        return children;
    }
}
