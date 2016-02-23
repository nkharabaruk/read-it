package com.readit.service.impl;

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

    public Set<Category> getChildren(Long id) {
        Set<Category> children = new HashSet<Category>();
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
