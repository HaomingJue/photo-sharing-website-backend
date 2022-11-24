package com.photosharingwebsite.photosharing.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;

@Document
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Photo {
    @Id
    private String id;

    private String title;
    private String description;
    private String uploadUser;

    private String imgBase64;

    private HashSet<String> likedUsers;

    public Photo(){}
    public Photo(String title, String description, String uploadUser) {
        this.title = title;
        this.description = description;
        this.uploadUser = uploadUser;
    }

    public Photo(String title, String description, String uploadUser, String imgBase64) {
        this.title = title;
        this.description = description;
        this.uploadUser = uploadUser;
        this.imgBase64 = imgBase64;
    }

    public Photo(String title, String description, String uploadUser, String imgBase64, HashSet<String> likedUsers) {
        this.title = title;
        this.description = description;
        this.uploadUser = uploadUser;
        this.imgBase64 = imgBase64;
        this.likedUsers = likedUsers;
    }
}
