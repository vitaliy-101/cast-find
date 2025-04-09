package com.example.castfindbackend.utils;

import com.example.castfindbackend.dto.photo.PhotoData;
import com.example.castfindbackend.entity.Photo;
import lombok.experimental.UtilityClass;
import org.apache.tika.Tika;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.castfindbackend.utils.FileUtils.convertToUrl;

@UtilityClass
public class PhotoUtils {
    public static final String PHOTO_URL = "/api/v1/photo/";
    public static List<String> extractPhotos(List<Photo> photos) {
        return photos.stream()
                .map(photo -> convertToUrl(PHOTO_URL, photo.getId().toString()))
                .collect(Collectors.toList());
    }

    public static String extractPhoto(Photo photo) {
        return convertToUrl(PHOTO_URL, photo.getId().toString());
    }

    public static PhotoData createPhotoData(byte[] data) {
        var resource = new InputStreamResource(new ByteArrayInputStream(data));
        return new PhotoData(resource, new Tika().detect(data));
    }

    public static ResponseEntity<Resource> createLoadResponseEntity(PhotoData photoData) {
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(photoData.getContentType()))
                .body(photoData.getResource());
    }
}
