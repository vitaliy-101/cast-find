package com.example.castfindbackend.dto.photo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PhotoDeleteRequest {
    private List<Long> ids;
}
