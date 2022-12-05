package com.photosharingwebsite.photosharing.controller;

import com.photosharingwebsite.photosharing.model.User;
import com.photosharingwebsite.photosharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
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

    @MutationMapping
    public User addUser(@Argument String username, @Argument String password) {
        return userService.createNewUser(username, password);
    }

    @MutationMapping
    public User likePhoto(@Argument String username, @Argument String photoTitle) {
        return userService.addLikedPhoto(username, photoTitle);
    }

    @MutationMapping
    public User unLikePhoto(@Argument String username, @Argument String photoTitle) {
        return userService.removeLikedPhoto(username, photoTitle);
    }

    @QueryMapping
    public User login(@Argument String username, @Argument String password) {
        return userService.authenticateUser(username, password);
    }

}
