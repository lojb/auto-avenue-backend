package com.codecool.autoavenue.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
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
    @ManyToOne
    private User seller;
    @OneToMany(mappedBy = "advert")
    private List<Message> messages;
}
