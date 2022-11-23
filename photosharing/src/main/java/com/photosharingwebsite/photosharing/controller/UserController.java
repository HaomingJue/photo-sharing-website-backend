package com.photosharingwebsite.photosharing.controller;

import com.photosharingwebsite.photosharing.model.User;
import com.photosharingwebsite.photosharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @QueryMapping
    public List<User> allUsers() {
        return userService.getAllUsers();
    }

    @QueryMapping
    public User userByUsername(@Argument String username) {
        return userService.findUserByUsername(username);
    }
}
