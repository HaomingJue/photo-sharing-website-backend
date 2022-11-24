package com.photosharingwebsite.photosharing.service;

import com.photosharingwebsite.photosharing.model.Photo;
import com.photosharingwebsite.photosharing.model.User;
import com.photosharingwebsite.photosharing.repository.PhotoRepository;
import com.photosharingwebsite.photosharing.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class PhotoService {
    private final UserRepository userRepository;

    private final PhotoRepository photoRepository;


    public PhotoService(UserRepository userRepository, PhotoRepository photoRepository) {
        this.userRepository = userRepository;
        this.photoRepository = photoRepository;
    }

    public Photo uploadPhoto(String username, String photoTitle, String description, String imgBase64) {
        try {
            Photo newPhoto = new Photo(photoTitle, description, username, imgBase64, new HashSet<>());
            photoRepository.save(newPhoto);
            User user = userRepository.findByUsername(username).get(0);
            HashSet<String> currentPhotos = user.getPhotos();
            currentPhotos.add(photoTitle);
            user.setPhotos(currentPhotos);
            userRepository.save(user);
            return newPhoto;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void likePhoto(String username, String photoTitle) {
        try {
            User user = userRepository.findByUsername(username).get(0);
            HashSet<String> currentLikedPhotos = user.getLikedPhotos();
            currentLikedPhotos.add(photoTitle);
            user.setPhotos(currentLikedPhotos);
            userRepository.save(user);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void unLikePhoto(String username, String photoTitle) {
        try {
            User user = userRepository.findByUsername(username).get(0);
            HashSet<String> currentLikedPhotos = user.getLikedPhotos();
            currentLikedPhotos.remove(photoTitle);
            user.setPhotos(currentLikedPhotos);
            userRepository.save(user);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Photo findPhotoByTitle(String title) {
        List<Photo> resultList = photoRepository.findByTitle(title);
        return resultList.size() == 0 ? null : resultList.get(0);
    }

    public List<Photo> findAllPhotos() {
        return photoRepository.findAll();
    }

    public Photo deletePhoto(String photoTitle, String username) {
        Photo deletedPhoto = photoRepository.deleteByTitle(photoTitle);

        // remove photo from user's uploaded photo list
        User user = userRepository.findByUsername(username).get(0);
        HashSet<String> currentPhotos = user.getPhotos();
        currentPhotos.remove(photoTitle);
        user.setPhotos(currentPhotos);
        userRepository.save(user);

        // remove photo from all liked user's like list
        HashSet<String> likedUsers = deletedPhoto.getLikedUsers();
        for (String aUsername : likedUsers) {
            System.out.println(aUsername);
            // remove photo from user's liked list
            User aLikedUser = userRepository.findByUsername(aUsername).get(0);
            HashSet<String> curLikedPhotos = aLikedUser.getLikedPhotos();
            curLikedPhotos.remove(photoTitle);
            aLikedUser.setLikedPhotos(curLikedPhotos);
            userRepository.save(aLikedUser);
        }


        return deletedPhoto;
    }
}
