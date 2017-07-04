package com.readit.rest;

import com.readit.entity.User;
import com.readit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable long userId) {
        return userService.findById(userId);
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping("/all")
    public void deleteAllUsers() {
        userService.deleteAll();
    }

    @DeleteMapping
    public void deleteUser(@RequestBody User user) {
        userService.delete(user);
    }
}
