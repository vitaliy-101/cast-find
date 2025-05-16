package com.example.castfindbackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "portfolio_folder")
public class PortfolioFolder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public PortfolioFolder(String title,
                           String description,
                           User user) {
        this.description = description;
        this.title = title;
        this.user = user;
    }

    public PortfolioFolder() {

    }
}
