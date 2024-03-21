package com.capstone.movieapp.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capstone.movieapp.model.User;

public class UserRepositoryTest {



    @Mock
    private UserRepository userRepositoryMock;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByEmailId() {
        User user = new User();
        user.setEmailId("test@test.com");
        user.setPassword("password");

        when(userRepositoryMock.findById(anyString())).thenReturn(Optional.of(user));

        Optional<User> found = userRepositoryMock.findById("test@test.com");
        assertEquals("test@test.com", found.get().getEmailId());
    }

    @Test
    public void testFindByEmailIdAndPassword() {
        User user = new User();
        user.setEmailId("test@test.com");
        user.setPassword("password");

        when(userRepositoryMock.findByEmailIdAndPassword(anyString(), anyString())).thenReturn(Optional.of(user));

        Optional<User> found = userRepositoryMock.findByEmailIdAndPassword("test@test.com", "password");
        assertEquals("test@test.com", found.get().getEmailId());
        assertEquals("password", found.get().getPassword());
    }
}

