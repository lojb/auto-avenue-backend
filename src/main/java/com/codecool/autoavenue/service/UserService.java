package com.codecool.autoavenue.service;

import com.codecool.autoavenue.model.User;
import com.codecool.autoavenue.service.DAO.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userDAO.findById(id);
    }

    public Optional<User> getUserByUsername(String username) {
        return userDAO.findUserByUsername(username);
    }

    public void addUser(User user) throws Exception {
        boolean isFree = true;
        for (User u: userDAO.findAll()
             ) {
            if(u.getUsername().equals(user.getUsername())){
                isFree = false;
            }
        }
        if(isFree) {
            userDAO.save(user);
        } else {
            throw new Exception("asd");
        }
    }

    public void updateUser(Long id, User newUser) {
        User userToUpdate = userDAO.findById(id).get();

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
