package com.example.castfindbackend.mapper;

import com.example.castfindbackend.dto.photo.PhotoCreateRequest;
import com.example.castfindbackend.entity.Photo;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomPhotoMapper {

    public static Photo toEntity(PhotoCreateRequest request, byte[] bytes) {
        return new Photo(
                request.getType(),
                request.isMain(),
                request.getDescription(),
                bytes,
                request.getOtherId()
        );
    }
}
