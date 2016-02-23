package com.readit.service.impl;

import com.readit.dao.AuthorDAO;
import com.readit.entity.Author;
import com.readit.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorDAO authorDAO;

    public List<Author> getAll() {
        return authorDAO.list();
    }

    public Author getById(Long id) {
        return authorDAO.get(id);
    }
}
