package com.photosharingwebsite.photosharing.service;

import com.photosharingwebsite.photosharing.model.Photo;
import com.photosharingwebsite.photosharing.model.User;
import com.photosharingwebsite.photosharing.repository.PhotoRepository;
import com.photosharingwebsite.photosharing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class PhotoService {
    private final UserRepository userRepository;

    private final PhotoRepository photoRepository;

    private MongoTemplate mongoTemplate;

    public PhotoService(UserRepository userRepository, PhotoRepository photoRepository) {
        this.userRepository = userRepository;
        this.photoRepository = photoRepository;
    }

    public void uploadPhoto(String username, String photoTitle, String description, String imgBase64) {
        try {

            photoRepository.save(new Photo(photoTitle, description, username, imgBase64));
//            Query query = new Query().addCriteria(Criteria.where("username").is(username));
            User user = userRepository.findByUsername(username).get(0);
            HashSet<String> currentPhotos = user.getPhotos();
            currentPhotos.add(photoTitle);
            user.setPhotos(currentPhotos);
            userRepository.save(user);
//            user.setPhotos(currentPhotos);
//            User targetUser = mongoTemplate.save(user);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void likePhoto(String username, String photoTitle) {
        try {
//            Query query = new Query().addCriteria(Criteria.where("username").is(username));
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
//            Query query = new Query().addCriteria(Criteria.where("username").is(username));
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
        List<Photo> result = photoRepository.findByTitle(title);
        return result.get(0);
    }
}
