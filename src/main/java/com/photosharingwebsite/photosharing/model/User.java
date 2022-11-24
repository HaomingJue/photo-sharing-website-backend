package com.photosharingwebsite.photosharing.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.List;

@Document
@Getter
@Setter
@ToString
public class User {
    @Id
    private String id;

    private String username;

    private String password;

    private HashSet<String> photos;

    private HashSet<String> likedPhotos;


    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, HashSet<String> photos) {
        this.username = username;
        this.password = password;
        this.photos = photos;
    }

    public User(String username, String password, HashSet<String> photos, HashSet<String> likedPhotos) {
        this.username = username;
        this.password = password;
        this.photos = photos;
        this.likedPhotos = likedPhotos;
    }

}
