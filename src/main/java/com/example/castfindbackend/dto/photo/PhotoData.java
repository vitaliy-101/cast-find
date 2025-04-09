package com.example.castfindbackend.dto.photo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.InputStreamResource;
@Getter
@Setter
@AllArgsConstructor
public class PhotoData {
    private InputStreamResource resource;
    private String contentType;
}
