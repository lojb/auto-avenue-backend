package com.codecool.autoavenue.model;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.codecool.autoavenue.model.enums.UserRole.USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

public class UserTest {

    private final User user = new User(99L,
            "testUser",
            "test@test.com",
            "pw123",
            USER,
            new ArrayList<>(),
            new Wishlist());

    @Test
    void getUsername() {
        assertEquals("testUser", user.getUsername());
    }

    @Test
    void getEmail() {
        assertEquals("test@test.com", user.getEmail());
    }

    @Test
    void getPassword() {
        assertEquals("pw123", user.getPassword());
    }

    @Test
    void getRole() {
        assertEquals(user.getRole(), USER);
    }

    @Test
    void getAdverts() {
        assertTrue(user.getAdverts().isEmpty());
    }

    @Test
    void getWishlist() {
        assertTrue(user.getWishlist().getWishlistItems().isEmpty());
    }

    @Test
    void getAuthorities() {
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        assertTrue(authorities.contains(new SimpleGrantedAuthority("USER")));
    }

    @Test
    void isAccountNonExpired() {
        assertTrue(user.isAccountNonExpired());
    }

    @Test
    void isAccountNonLocked() {
        assertTrue(user.isAccountNonLocked());
    }

    @Test
    void isCredentialsNonExpired() {
        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    void isEnabled() {
        assertTrue(user.isEnabled());
    }

    @Test
    void getId() {
    }

    @Test
    void testGetUsername() {
    }

    @Test
    void testGetEmail() {
    }

    @Test
    void testGetPassword() {
    }

    @Test
    void testGetRole() {
    }

    @Test
    void testGetAdverts() {
    }

    @Test
    void testGetWishlist() {
    }

    @Test
    void setId() {
    }

    @Test
    void setUsername() {
    }

    @Test
    void setEmail() {
    }

    @Test
    void setPassword() {
    }

    @Test
    void setRole() {
    }

    @Test
    void setAdverts() {
    }

    @Test
    void setWishlist() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void canEqual() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
    }

    @Test
    void builder() {
    }
}
