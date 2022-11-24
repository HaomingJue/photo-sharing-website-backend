package com.photosharingwebsite.photosharing.controller;

import com.photosharingwebsite.photosharing.model.Photo;
import com.photosharingwebsite.photosharing.service.PhotoService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PhotoController {

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @QueryMapping
    public List<Photo> allPhotos() {
        return photoService.findAllPhotos();
    }

    @QueryMapping
    public Photo photoByTitle(@Argument String title) {
        return photoService.findPhotoByTitle(title);
    }

    @MutationMapping
    public Photo addPhoto(@Argument String title, @Argument String description, @Argument String uploadedUser, @Argument String imgBase64) {
        return photoService.uploadPhoto(uploadedUser, title, description, imgBase64);
    }
}
