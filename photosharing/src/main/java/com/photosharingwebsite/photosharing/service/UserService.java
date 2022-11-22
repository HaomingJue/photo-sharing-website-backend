package com.photosharingwebsite.photosharing.service;

import com.photosharingwebsite.photosharing.model.Photo;
import com.photosharingwebsite.photosharing.model.User;
import com.photosharingwebsite.photosharing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createNewUser(String username, String password) {
        try {
            User user = new User(username, password, new HashSet<>(), new HashSet<>());
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User findUserByUsername(String username) {
        List<User> userList = userRepository.findByUsername(username);
        return userList.size() == 0 ? null : userList.get(0);
    }

    public List<User> getAllUsername() {
        return userRepository.findAll();
    }
}
