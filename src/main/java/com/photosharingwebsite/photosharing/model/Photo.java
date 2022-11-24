package com.photosharingwebsite.photosharing.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
}
