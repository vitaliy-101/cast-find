package com.example.castfindbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "organisation")
public class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "organisation")
    private Set<User> users;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "organisation_specialisation",
            joinColumns = @JoinColumn(name = "organisation_id"),
            inverseJoinColumns = @JoinColumn(name = "specialisation_id")
    )
    private Set<Specialisation> specialisations = new HashSet<>();

    @OneToMany(mappedBy = "organisation")
    private Set<PhotoOrganisation> photoOrganisations = new HashSet<>();

}
