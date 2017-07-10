package com.readit.service;

import com.readit.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(long id);

    User findByEmail(String email);

    User save(User user);

    User update(long id, User user);

    void deleteAll();

    void delete(long id);
}
