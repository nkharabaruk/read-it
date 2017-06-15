package com.readit.service;

import com.readit.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(long id);

    List<User> saveAll(List<User> list);

    User save(User user);

    void deleteAll();

    void delete(User user);
}
