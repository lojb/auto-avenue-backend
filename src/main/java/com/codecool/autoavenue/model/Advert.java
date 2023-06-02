package com.codecool.autoavenue.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "adverts")
@Getter
@Setter
@NoArgsConstructor
public class Advert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String manufacturer;
    private String model;
    private int year;
    private String title;
    private String description;
    private double price;
    private Long sellerId;
    private String imageUrl;
    @OneToMany(mappedBy = "advert")
    private List<Message> messages;
}
