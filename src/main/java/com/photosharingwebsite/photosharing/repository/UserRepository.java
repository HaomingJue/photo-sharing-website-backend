package com.photosharingwebsite.photosharing.repository;

import com.photosharingwebsite.photosharing.model.Photo;
import com.photosharingwebsite.photosharing.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    @Query("{ 'username' :  ?0}")
    List<User> findByUsername(String username);
}
