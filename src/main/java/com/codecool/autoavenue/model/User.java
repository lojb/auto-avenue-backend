package com.codecool.autoavenue.model;

import com.codecool.autoavenue.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private UserRole role;
    @OneToMany(mappedBy = "seller")
    private List<Advert> adverts;
}
