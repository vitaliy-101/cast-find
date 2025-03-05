package com.example.castfindbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "photo_organisation")
@Getter
@Setter
public class PhotoOrganisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "data")
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;

}
