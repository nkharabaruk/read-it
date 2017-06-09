package com.readit.service.impl;

import com.readit.entity.Category;
import com.readit.repository.BookRepository;
import com.readit.repository.CategoryRepository;
import com.readit.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, BookRepository bookRepository) {
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(long id) {
        return categoryRepository.findOne(id);
    }

    /**
     * Returns list of categories.
     * Each category is a simple tree which starts from root
     * and ends with the last leaf which book belongs to.
     **/
    public List<Category> findBookCategories(long bookId) {
        List<Category> bookCategories = bookRepository.findOne(bookId).getCategories();
        List<Category> bookCategoriesInverse = new ArrayList<>();
        for (Category category : bookCategories) {
            List<Category> parents = new ArrayList<>();
            parents.add(category);
            while (category.getParent() != null) {
                category = category.getParent();
                parents.add(category);
            }
            Category categoryTree = parents.get(0);
            categoryTree.setChildren(null);
            for (int i = 1; i < parents.size(); i++) {
                List<Category> children = new ArrayList<>();
                children.add(categoryTree);
                categoryTree.setParent(parents.get(i));
                categoryTree = categoryTree.getParent();
                categoryTree.setChildren(children);
            }
//            categoryTree = new Category(categoryTree);
            bookCategoriesInverse.add(categoryTree);
        }
        Collections.sort(bookCategoriesInverse, (c1, c2) -> (c1.getId() > c2.getId()) ? 1 : -1);
        return bookCategoriesInverse;
    }

    public List<Category> findRootCategories() {
//        return categoryRepository.findRootCategories();
        return null;
    }

    public List<Category> findAscendants(long id) {
        List<Category> parents = new ArrayList<>();
        Category child = findById(id);
        while (child.getParent() != null) {
            Category parent = child.getParent();
            parents.add(parent);
            child = parent;
        }
        return parents;
    }

    public List<Category> findDescendants(long id) {
        List<Category> children = new ArrayList<>();
        children.addAll(findById(id).getChildren());
        boolean repeat = true;
        while (repeat) {
            repeat = false;
            Set<Category> temp = new HashSet<>();
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

    @Override
    public List<Category> saveAll(List<Category> list) {
        return categoryRepository.save(list);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteAll() {
        categoryRepository.deleteAll();
    }

    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }
}
