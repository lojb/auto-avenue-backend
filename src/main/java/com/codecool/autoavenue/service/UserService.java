package com.codecool.autoavenue.service;

import com.codecool.autoavenue.model.User;
import com.codecool.autoavenue.service.DAO.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    public User getUserById(Long id) {
        return userDAO.getById(id);
    }

    public void addUser(User user) {
        userDAO.save(user);
    }

    public void updateUser(Long id, User newUser) {
        User userToUpdate = userDAO.getById(id);

        userToUpdate.setUsername(newUser.getUsername());
        userToUpdate.setPassword(newUser.getPassword());
        userToUpdate.setRole(newUser.getRole());
        userToUpdate.setAdverts(newUser.getAdverts());

        userDAO.save(userToUpdate);
    }

    public void deleteUser(Long id) {
        userDAO.deleteById(id);
    }
}
