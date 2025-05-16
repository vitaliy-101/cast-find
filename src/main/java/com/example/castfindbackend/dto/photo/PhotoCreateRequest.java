package com.example.castfindbackend.dto.photo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoCreateRequest {
    private Long otherId;
    private String type;
    private boolean isMain;
    private String description;
}
