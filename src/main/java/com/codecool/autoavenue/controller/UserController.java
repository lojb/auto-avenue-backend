package com.codecool.autoavenue.controller;

import com.codecool.autoavenue.model.User;
import com.codecool.autoavenue.service.UserService;
import com.codecool.autoavenue.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    WishlistService wishlistService;

    @Secured("ADMIN")
    @GetMapping
    public List<User> getAllUsers(){return userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<Void> addUser(@RequestBody User user) throws Exception {
        userService.addUser(user);
        wishlistService.addWishlistForUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable("userId") Long id, @RequestBody User newUser) {
        Optional<User> updatedUser = userService.getUserById(id);
        if (updatedUser.isPresent()) {
            userService.updateUser(id, newUser);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Secured("ADMIN")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


}
