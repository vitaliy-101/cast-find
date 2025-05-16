package com.example.castfindbackend.dto.photo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PhotosFindRequest {
    private Long otherId;
    private String type;
    @Schema(description = "Задает тип фото, которые мы хотим получить: DEFAULT, AVATAR, ALL")
    private MainPhotoType mainType;
}
