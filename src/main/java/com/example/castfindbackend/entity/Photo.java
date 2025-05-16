package com.example.castfindbackend.entity;

import com.example.castfindbackend.mapper.PhotoMapper;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "photo")
@Getter
@Setter
@NoArgsConstructor
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "main")
    private boolean main = false;

    @Column(name = "description")
    private String description;

    @Column(name = "data")
    private byte[] data;

    @Column(name = "other_id")
    private Long otherId;

    public Photo(String type,
                 boolean isMain,
                 String description,
                 byte[] data,
                 Long otherId) {
        this.type = type;
        this.main = isMain;
        this.description = description;
        this.data = data;
        this.otherId = otherId;
    }
}
