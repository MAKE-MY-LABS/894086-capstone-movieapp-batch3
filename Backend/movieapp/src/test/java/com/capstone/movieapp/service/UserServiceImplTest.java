package com.capstone.movieapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capstone.movieapp.exceptions.EmailIdAlreadyExistsException;
import com.capstone.movieapp.model.User;
import com.capstone.movieapp.repository.UserRepository;

public class UserServiceImplTest {


    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser() throws EmailIdAlreadyExistsException {
        User user = new User();
        user.setEmailId("test@test.com");
        user.setPassword("password");

        when(userRepository.findById(anyString())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);

        User saved = userService.saveUser(user);
        assertEquals(user, saved);
    }

    @Test
    public void testSaveUser_EmailIdAlreadyExists() {
        User user = new User();
        user.setEmailId("test@test.com");
        user.setPassword("password");

        when(userRepository.findById(anyString())).thenReturn(Optional.of(user));

        assertThrows(EmailIdAlreadyExistsException.class, () -> userService.saveUser(user));
    }

   
}
