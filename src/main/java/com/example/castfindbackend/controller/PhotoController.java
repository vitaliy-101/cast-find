package com.example.castfindbackend.controller;

import com.example.castfindbackend.dto.photo.PhotoCreateRequest;
import com.example.castfindbackend.dto.photo.PhotoDeleteRequest;
import com.example.castfindbackend.dto.photo.PhotosFindRequest;
import com.example.castfindbackend.dto.photo.PhotosResponse;
import com.example.castfindbackend.service.PhotoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.example.castfindbackend.utils.PhotoUtils.createLoadResponseEntity;

@RestController
@RequestMapping("/api/v1/photo")
@RequiredArgsConstructor
public class PhotoController {
    private final PhotoService photoService;

    @Operation(description = "Сохранить несколько фото")
    @PostMapping
    public void create(@RequestPart("request") PhotoCreateRequest request,
                       @RequestPart("photos") List<MultipartFile> photos) {
        photoService.savePhotos(photos, request);
    }

    @Operation(description = "Сохранение одного фото")
    @PostMapping("/one")
    public void createOne(@RequestPart("request") PhotoCreateRequest request,
                          @RequestPart("photo") MultipartFile photo) {
        photoService.savePhoto(photo, request);
    }

    @Operation(description = "Получить фото по id")
    @GetMapping("/{id}")
    public ResponseEntity<Resource> loadImage(@PathVariable("id") Long id) {
        var imageData = photoService.findPhotoById(id);
        return createLoadResponseEntity(imageData);
    }

    @Operation(description = "Получить все фото по other id и типу")
    @PostMapping("/all")
    public PhotosResponse getAll(@RequestBody PhotosFindRequest request) {
        var photos = photoService.findPhotosByTypeAndOtherId(
                request.getOtherId(),
                request.getType(),
                request.getMainType());
        return new PhotosResponse(photos);
    }

    @Operation(description = "Уделение несколько фоток по id")
    @DeleteMapping("/delete")
    public void delete(@RequestBody PhotoDeleteRequest request) {
        photoService.delete(request.getIds());
    }

}
