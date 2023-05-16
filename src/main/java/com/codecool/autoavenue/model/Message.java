package com.codecool.autoavenue.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Message {
    @Id
    private Long id;
    @ManyToOne
    private User writer;
    @ManyToOne
    private Advert advert;
    private String message;
}
