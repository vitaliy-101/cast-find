package com.example.castfindbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "photo")
@Getter
@Setter
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "main")
    private boolean main = false;

    @Column(name = "data")
    private byte[] data;

    @Column(name = "other_id")
    private Long otherId;
}
