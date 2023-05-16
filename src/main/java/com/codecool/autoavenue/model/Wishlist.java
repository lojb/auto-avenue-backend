package com.codecool.autoavenue.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Wishlist {
    @Id
    private Long id;
    @OneToOne
    private User user;
    @OneToMany
    private List<Advert> wishlistItems;
}
