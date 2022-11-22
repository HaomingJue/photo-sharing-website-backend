package com.photosharingwebsite.photosharing;

import com.photosharingwebsite.photosharing.model.Photo;
import com.photosharingwebsite.photosharing.model.User;
import com.photosharingwebsite.photosharing.repository.PhotoRepository;
import com.photosharingwebsite.photosharing.repository.UserRepository;

import com.photosharingwebsite.photosharing.service.PhotoService;
import com.photosharingwebsite.photosharing.service.UserService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@SpringBootApplication
public class PhotosharingApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PhotoRepository photoRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private PhotoService photoService;

	public static void main(String[] args) {
		SpringApplication.run(PhotosharingApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Path path = Paths.get("C:\\Users\\capture.png");
		byte[] fileContent = Files.readAllBytes(path);
		String baseString = Base64.getEncoder().encodeToString(fileContent);
//		userService.createNewUser("antifa", "Jeremy's password");
		photoService.uploadPhoto("antifa", "3 photo", "Some description", baseString);
		photoService.likePhoto("antifa", "2 photo");
		HashSet<String> likedPhotos = userRepository.findByUsername("antifa").get(0).getLikedPhotos();
		System.out.println(likedPhotos);
		photoService.likePhoto("antifa", "3 photo");
		likedPhotos = userRepository.findByUsername("antifa").get(0).getLikedPhotos();
		System.out.println(likedPhotos);
		photoService.unLikePhoto("antifa", "2 photo");
		 likedPhotos = userRepository.findByUsername("antifa").get(0).getLikedPhotos();
		System.out.println(likedPhotos);
//		List<Photo> photoList = photoRepository.findByTitleAndUser("a photo", "antifa");
//		for (Photo photo : photoList) {
//			System.out.println(photo.getId());
//			System.out.println(photo.getUploadUser());
//		}
//		if (userRepository.findAll().isEmpty()) {
//			userRepository.save(new User("Alice", "Smith"));
//			userRepository.save(new User("Bob", "Smith"));
//		}
//		HashSet<String> photos = new HashSet<>();
//		photos.add("photo1");
//		photos.add("photo2");
////		userRepository.save(new User("hashset", "what", photos));
////		if (photoRepository.findAll().isEmpty()) {
////			photoRepository.save(new Photo( "photo title", "desc", "HJ"));
////		}
//
//		Path path = Paths.get("C:\\Users\\capture.png");
//		byte[] fileContent = Files.readAllBytes(path);
//		String baseString = Base64.getEncoder().encodeToString(fileContent);
//		System.out.println(baseString);
//		photoRepository.save(new Photo("new Photo", "descion a aaaa", "HJ2", baseString));
//
//
//		for (User user : userRepository.findAll()) {
//			System.out.println(user);
//		}
//		for (Photo photo : photoRepository.findAll()) {
//			System.out.println(photo);
//		}
//
//		List<Photo> photoList = photoRepository.findByTitleAndUser("new Photo", "HJ2");
//		for (Photo photo : photoList) {
//			System.out.println(photo.getId());
//			System.out.println(photo.getUploadUser());
//		}
	}
}
