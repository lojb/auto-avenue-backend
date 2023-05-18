package com.codecool.autoavenue.service.DAO;

import com.codecool.autoavenue.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);
}
