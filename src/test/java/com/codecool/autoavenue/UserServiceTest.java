package com.codecool.autoavenue;

import com.codecool.autoavenue.model.User;
import com.codecool.autoavenue.service.DAO.UserDAO;
import com.codecool.autoavenue.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserService userService;

    @Mock
    private UserDAO userDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService();
        userService.setUserDAO(userDAO);
    }

    @Test
    void testGetAllUsers() {
        List<User> expectedUsers = new ArrayList<>();
        when(userDAO.findAll()).thenReturn(expectedUsers);

        List<User> actualUsers = userService.getAllUsers();

        assertSame(expectedUsers, actualUsers);
        verify(userDAO, times(1)).findAll();
    }

    @Test
    void testGetUserById() {
        Long userId = 1L;
        User expectedUser = new User();
        when(userDAO.findById(userId)).thenReturn(Optional.of(expectedUser));

        Optional<User> actualUser = userService.getUserById(userId);

        assertTrue(actualUser.isPresent());
        assertSame(expectedUser, actualUser.get());
        verify(userDAO, times(1)).findById(userId);
    }

    @Test
    void testGetUserByUsername() {
        String username = "johnDoe";
        User expectedUser = new User();
        when(userDAO.findUserByUsername(username)).thenReturn(Optional.of(expectedUser));

        Optional<User> actualUser = userService.getUserByUsername(username);

        assertTrue(actualUser.isPresent());
        assertSame(expectedUser, actualUser.get());
        verify(userDAO, times(1)).findUserByUsername(username);
    }

    @Test
    void testAddUser() throws Exception {
        User newUser = new User();
        newUser.setUsername("johnDoe");

        when(userDAO.findAll()).thenReturn(new ArrayList<>());

        userService.addUser(newUser);

        // Assert
        verify(userDAO).save(newUser);
    }

    @Test
    void testAddUserWithExistingUsername() {
        User newUser = new User();
        newUser.setUsername("johnDoe");

        User existingUser = new User();
        existingUser.setUsername("johnDoe");

        List<User> users = new ArrayList<>();
        users.add(existingUser);

        when(userDAO.findAll()).thenReturn(users);

        assertThrows(Exception.class, () -> userService.addUser(newUser));
        verify(userDAO, times(1)).findAll();
        verify(userDAO, never()).save(newUser);
    }

    @Test
    void testUpdateUser() {
        Long userId = 1L;
        User existingUser = new User();
        existingUser.setId(userId);

        User newUser = new User();
        newUser.setUsername("newUsername");
        newUser.setPassword("newPassword");

        when(userDAO.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userDAO.save(existingUser)).thenReturn(existingUser);

        userService.updateUser(userId, newUser);

        assertEquals(newUser.getUsername(), existingUser.getUsername());
        assertEquals(newUser.getPassword(), existingUser.getPassword());
        verify(userDAO, times(1)).findById(userId);
        verify(userDAO, times(1)).save(existingUser);
    }

    @Test
    void testUpdateUserWithNonExistingId() {
        Long userId = 1L;
        User newUser = new User();
        newUser.setUsername("newUsername");

        when(userDAO.findById(userId)).thenReturn(Optional.empty());

        userService.updateUser(userId, newUser);

        verify(userDAO, times(1)).findById(userId);
        verify(userDAO, never()).save(any());
    }

    @Test
    void testDeleteUser() {
        Long userId = 1L;
        doNothing().when(userDAO).deleteById(userId);

        userService.deleteUser(userId);

        verify(userDAO, times(1)).deleteById(userId);
    }
}