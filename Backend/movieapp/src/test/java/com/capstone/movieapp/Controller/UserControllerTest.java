package com.capstone.movieapp.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.capstone.movieapp.controller.UserController;
import com.capstone.movieapp.exceptions.EmailIdAlreadyExistsException;
import com.capstone.movieapp.model.User;
import com.capstone.movieapp.service.UserService;

public class UserControllerTest {


    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser() throws EmailIdAlreadyExistsException {
        User user = new User();
        user.setEmailId("test@test.com");
        user.setPassword("password");

        when(userService.saveUser(any(User.class))).thenReturn(user);

        ResponseEntity<String> response = userController.saveUser(user);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testSaveUser_EmailIdAlreadyExists() throws EmailIdAlreadyExistsException {
        User user = new User();
        user.setEmailId("test@test.com");
        user.setPassword("password");

        when(userService.saveUser(any(User.class))).thenThrow(EmailIdAlreadyExistsException.class);

        ResponseEntity<String> response = userController.saveUser(user);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    
}

