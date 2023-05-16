package com.codecool.autoavenue.service.DAO;

import com.codecool.autoavenue.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Long> {

}
