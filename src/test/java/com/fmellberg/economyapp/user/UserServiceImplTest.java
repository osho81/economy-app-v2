package com.fmellberg.economyapp.user;

import com.fmellberg.economyapp.exception.ResourceNotFoundException;
import com.fmellberg.economyapp.user.DTO.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createUser_ShouldReturnCreatedUserDTO() {
        // Prepare test data
        UserDTO userDTO = new UserDTO(1,"John", "Doe", "john@example.com", "johndoe", "password");
        User createdUser = new User(1, "John", "Doe", "john@example.com", "johndoe", "password");

        // Mock the userRepository behavior
        Mockito.when(userRepository.save(any(User.class))).thenReturn(createdUser);

        // Perform the createUser operation
        UserDTO createdUserDTO = userService.createUser(userDTO);

        // Verify the userRepository save method was called with the expected User object
        Mockito.verify(userRepository, Mockito.times(1)).save(any(User.class));

        // Verify the returned UserDTO matches the expected values
        assertEquals(userDTO, createdUserDTO);
    }

    @Test
    public void getAllUsers_ShouldReturnListOfUserDTOs() {
        // Prepare test data
        List<User> users = new ArrayList<>();
        users.add(new User(1, "John", "Doe", "john@example.com", "johndoe", "password"));
        users.add(new User(2, "Jane", "Smith", "jane@example.com", "janesmith", "password"));

        // Mock the userRepository behavior
        Mockito.when(userRepository.findAll()).thenReturn(users);

        // Perform the getAllUsers operation
        List<UserDTO> userDTOs = userService.getAllUsers();

        // Verify the userRepository findAll method was called
        Mockito.verify(userRepository, Mockito.times(1)).findAll();

        // Verify the returned UserDTO list size and contents
        assertEquals(2, userDTOs.size());
        assertEquals(UserMapper.toUserDTO(users.get(0)), userDTOs.get(0));
        assertEquals(UserMapper.toUserDTO(users.get(1)), userDTOs.get(1));
    }

    @Test
    public void getUserById_ShouldReturnUserDTO() {
        // Prepare test data
        int userId = 1;
        User user = new User(userId, "John", "Doe", "john@example.com", "johndoe", "password");
        UserDTO expectedUserDTO = UserMapper.toUserDTO(user);

        // Mock the userRepository behavior
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Perform the getUserById operation
        UserDTO retrievedUserDTO = userService.getUserById(userId);

        // Verify the userRepository findById method was called
        Mockito.verify(userRepository, Mockito.times(1)).findById(userId);

        // Verify the returned UserDTO matches the expected UserDTO
        assertEquals(expectedUserDTO, retrievedUserDTO);
    }

    @Test
    public void getUserById_UserNotFound_ShouldThrowResourceNotFoundException() {
        // Prepare test data
        int userId = 1;

        // Mock the userRepository behavior
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Perform the getUserById operation and assert that it throws ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(userId));

        // Verify the userRepository findById method was called
        Mockito.verify(userRepository, Mockito.times(1)).findById(userId);
    }

    @Test
    public void updateUser_ShouldReturnUpdatedUserDTO() {
        // Prepare test data
        UserDTO userDTO = new UserDTO(1, "John", "Doe", "john@example.com", "johndoe", "password");
        User existingUser = new User(1, "Existing", "User", "existing@example.com", "existinguser", "password");
        User updatedUser = new User(1, "John", "Doe", "john@example.com", "johndoe", "password");

        // Mock the userRepository behavior
        Mockito.when(userRepository.findById(userDTO.getId())).thenReturn(Optional.of(existingUser));
        Mockito.when(userRepository.save(existingUser)).thenReturn(updatedUser);

        // Perform the updateUser operation
        UserDTO updatedUserDTO = userService.updateUser(userDTO);

        // Verify the userRepository findById method was called
        Mockito.verify(userRepository, Mockito.times(1)).findById(userDTO.getId());

        // Verify the userRepository save method was called
        Mockito.verify(userRepository, Mockito.times(1)).save(existingUser);

        // Verify the returned UserDTO matches the expected values
        assertEquals(userDTO, updatedUserDTO);
    }

    @Test
    public void updateUser_UserNotFound_ShouldThrowResourceNotFoundException() {
        // Prepare test data
        UserDTO userDTO = new UserDTO(1, "John", "Doe", "john@example.com", "johndoe", "password");

        // Mock the userRepository behavior
        Mockito.when(userRepository.findById(userDTO.getId())).thenReturn(Optional.empty());

        // Perform the updateUser operation and assert that it throws ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> userService.updateUser(userDTO));

        // Verify the userRepository findById method was called
        Mockito.verify(userRepository, Mockito.times(1)).findById(userDTO.getId());
    }

    @Test
    public void deleteUser_ShouldDeleteUser()  {
        // Prepare test data
        int userId = 1;
        User existingUser = new User(userId, "John", "Doe", "john@example.com", "johndoe", "password");

        // Mock the userRepository behavior
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));

        // Perform the deleteUser operation
        userService.deleteUser(userId);

        // Verify the userRepository findById method was called
        Mockito.verify(userRepository, Mockito.times(1)).findById(userId);

        // Verify the userRepository deleteById method was called
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(userId);
    }

    @Test
    public void deleteUser_UserNotFound_ShouldThrowResourceNotFoundException() {
        // Prepare test data
        int userId = 1;

        // Mock the userRepository behavior
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Perform the deleteUser operation and assert that it throws ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(userId));

        // Verify the userRepository findById method was called
        Mockito.verify(userRepository, Mockito.times(1)).findById(userId);
    }
}