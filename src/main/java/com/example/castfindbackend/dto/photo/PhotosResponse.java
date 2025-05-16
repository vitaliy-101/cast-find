package com.example.castfindbackend.dto.photo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PhotosResponse {
    private List<PhotoResponse> photoResponses;
}
