package com.readit.service.exception;

import com.readit.entity.Category;

public class CategoryNotFoundException extends NotFoundException {

    public CategoryNotFoundException(long id) {
        super(Category.class, id);
    }
}
