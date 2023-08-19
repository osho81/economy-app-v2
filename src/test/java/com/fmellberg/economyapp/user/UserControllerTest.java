package com.fmellberg.economyapp.user;


import com.fmellberg.economyapp.user.DTO.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createUser_ShouldReturnCreatedUserAndResponseCREATED() {
        // Prepare test data
        int id = 1;
        String firstName = "John";
        String lastName = "Doe";
        String email = "johndoe@example.com";
        String userName = "johndoe";
        String password = "password";

        // Create UserDTO with constructor
        UserDTO userDTO = new UserDTO(id, firstName, lastName, email, userName, password);

        // Mock the UserService behavior
        UserDTO createdUserDTO = new UserDTO(id, firstName, lastName, email, userName, password);
        Mockito.when(userService.createUser(userDTO)).thenReturn(createdUserDTO);

        // Perform the request to the UserController
        ResponseEntity<UserDTO> response = userController.createUser(userDTO);

        // Verify the UserService method was called
        Mockito.verify(userService, Mockito.times(1)).createUser(userDTO);

        // Verify the response status code and body
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdUserDTO, response.getBody());
    }



    @Test
    void getAllUsers_ShouldReturnListOfUsersAndResponseOK() {
        // Prepare test data
        UserDTO user1 = new UserDTO(1, "John", "Doe", "john@example.com", "johndoe", "password");
        UserDTO user2 = new UserDTO(2, "Jane", "Smith", "jane@example.com", "janesmith", "password");
        List<UserDTO> expectedUsers = Arrays.asList(user1, user2);

        // Mock the UserService behavior
        Mockito.when(userService.getAllUsers()).thenReturn(expectedUsers);

        // Perform the request to the UserController
        ResponseEntity<List<UserDTO>> response = userController.getAllUsers();

        // Verify the UserService method was called
        Mockito.verify(userService, Mockito.times(1)).getAllUsers();

        // Verify the response status code and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUsers, response.getBody());
    }

    @Test
    void getUserById_ShouldReturnUserAndResponseOK() {
        // Prepare test data
        int userId = 1;
        UserDTO expectedUser = new UserDTO(userId, "John", "Doe", "john@example.com", "johndoe", "password");

        // Mock the UserService behavior
        Mockito.when(userService.getUserById(userId)).thenReturn(expectedUser);

        // Perform the request to the UserController
        ResponseEntity<UserDTO> response = userController.getUserById(userId);

        // Verify the UserService method was called
        Mockito.verify(userService, Mockito.times(1)).getUserById(userId);

        // Verify the response status code and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUser, response.getBody());
    }

    @Test
    void updateUser_ShouldReturnUpdatedUserAndResponseOK() {
        // Prepare test data
        int userId = 1;
        UserDTO userDTO = new UserDTO(userId, "John", "Doe", "john@example.com", "johndoe", "password");
        UserDTO updatedUser = new UserDTO(userId, "John", "Doe", "updated@example.com", "johndoe", "password");

        // Mock the UserService behavior
        Mockito.when(userService.updateUser(userDTO)).thenReturn(updatedUser);

        // Perform the request to the UserController
        ResponseEntity<UserDTO> response = userController.updateUser(userDTO);

        // Verify the UserService method was called
        Mockito.verify(userService, Mockito.times(1)).updateUser(userDTO);

        // Verify the response status code and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUser, response.getBody());
    }

    @Test
    void deleteUser_ShouldReturnResponseNoContent() {
        // Prepare test data
        int userId = 1;
        //hejhej
        // Perform the request to the UserController
        ResponseEntity<Void> response = userController.deleteUser(userId);

        // Verify the UserService method was called
        Mockito.verify(userService, Mockito.times(1)).deleteUser(userId);

        // Verify the response status code
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }
}