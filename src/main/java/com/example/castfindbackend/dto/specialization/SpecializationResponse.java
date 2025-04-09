package com.example.castfindbackend.dto.specialization;

import com.example.castfindbackend.dto.photo.PhotoResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SpecializationResponse {
    private Long id;
    private String name;
    private PhotoResponse photo;
}
