package com.photosharingwebsite.photosharing.service;

import com.photosharingwebsite.photosharing.model.Photo;
import com.photosharingwebsite.photosharing.model.User;
import com.photosharingwebsite.photosharing.repository.PhotoRepository;
import com.photosharingwebsite.photosharing.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PhotoRepository photoRepository;

    public UserService(UserRepository userRepository, PhotoRepository photoRepository) {
        this.userRepository = userRepository;
        this.photoRepository = photoRepository;
    }

    public User createNewUser(String username, String password) {
        User user = new User(username, password, new HashSet<>(), new HashSet<>());
        userRepository.save(user);
        return user;

    }

    public User findUserByUsername(String username) {
        List<User> userList = userRepository.findByUsername(username);
        return userList.size() == 0 ? null : userList.get(0);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addLikedPhoto(String username, String photoTitle) {
        try {
            // add photo to user's liked list
            User user = userRepository.findByUsername(username).get(0);
            HashSet<String> curLikedPhotos = user.getLikedPhotos();
            curLikedPhotos.add(photoTitle);
            user.setLikedPhotos(curLikedPhotos);
            userRepository.save(user);

            // add user to photo's liked list
            Photo photo = photoRepository.findByTitle(photoTitle).get(0);
            HashSet<String> curLikedUsers = photo.getLikedUsers();
            curLikedUsers.add(username);
            photo.setLikedUsers(curLikedUsers);
            photoRepository.save(photo);

            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public User removeLikedPhoto(String username, String photoTitle) {
        try {
            // remove photo from user's liked list
            User user = userRepository.findByUsername(username).get(0);
            HashSet<String> curLikedPhotos = user.getLikedPhotos();
            curLikedPhotos.remove(photoTitle);
            user.setLikedPhotos(curLikedPhotos);
            userRepository.save(user);

            // remove user from photo's liked list
            Photo photo = photoRepository.findByTitle(photoTitle).get(0);
            HashSet<String> curLikedUsers = photo.getLikedUsers();
            curLikedUsers.remove(username);
            photo.setLikedUsers(curLikedUsers);
            photoRepository.save(photo);

            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User authenticateUser(String username, String password) {
        List<User> userList = userRepository.findByUsername((username));
        System.out.println(userList.toString());
        if (userList.size() == 0) {
            return null;
        }
        User user = userList.get(0);
        if (!user.getPassword().equals(password)) {
            return null;
        }
        return user;
    }
}
