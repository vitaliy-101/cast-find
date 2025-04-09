package com.example.castfindbackend.dto.photo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
public class PhotoResponse {
    private String url;
    private boolean isMain;
}
