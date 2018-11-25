package com.readit.service.impl;

import com.readit.entity.User;
import com.readit.repository.UserRepository;
import com.readit.service.UserService;
import com.readit.service.exception.UserAlreadyExistsException;
import com.readit.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) throw new UserNotFoundException("email", email);
        return user;
    }

    @Override
    public User save(User user) {
        User existing = userRepository.findByEmail(user.getEmail());
        if (existing != null) throw new UserAlreadyExistsException(existing);
        return userRepository.save(user);
    }

    @Override
    public User update(long id, User user) {
        try {
            User existing = userRepository.getOne(id);
            user.setId(existing.getId());
            return userRepository.save(user);
        } catch (JpaObjectRetrievalFailureException e) {
            throw new UserNotFoundException(id);
        }
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public void delete(long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException(id);
        }
    }
}
