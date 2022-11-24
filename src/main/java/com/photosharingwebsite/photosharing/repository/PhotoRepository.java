package com.photosharingwebsite.photosharing.repository;

import com.photosharingwebsite.photosharing.model.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;

@Repository
public interface PhotoRepository extends MongoRepository<Photo, String> {
    @Query("{ 'title' :  ?0}")
    List<Photo> findByTitle(String title);

    @Query("{$and: [{title: ?0}, {uploadUser: ?1}]}")
    List<Photo> findByTitleAndUser(String title, String user);
}
