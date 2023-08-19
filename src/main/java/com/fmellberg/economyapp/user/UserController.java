package com.fmellberg.economyapp.user;

import com.fmellberg.economyapp.user.DTO.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:5173") // Allow VUE frontend
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        logger.info("Received a request to create a user: {}", userDTO);
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        logger.info("Received a request to retrieve all users");
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users); // Returns userDTOs
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id) {
        logger.info("Received a request to retrieve user by ID: {}", id);
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/username/{userName}") // Added 20230715
    public ResponseEntity<UserDTO> getUserById(@PathVariable String userName) {
        logger.info("Received a request to retrieve user by username: {}", userName);
        UserDTO user = userService.getUserByUserName(userName);
        return ResponseEntity.ok(user);
    }

    @PutMapping() // Removed redundant path var {id} 230625
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) { // Uses req body
//        logger.info("Received a request to update user with ID: {}", userDTO.getId());
        logger.info("Received a request to update user with userName: {}", userDTO.getUserName());
        UserDTO updatedUser = userService.updateUser(userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        logger.info("Received a request to delete user with ID: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
