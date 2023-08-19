package com.fmellberg.economyapp.user;

import com.fmellberg.economyapp.exception.ResourceNotFoundException;
import com.fmellberg.economyapp.user.DTO.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO); // Transform DTO-user into model-user
        logger.debug("Creating user: {}", user);
        User createdUser = userRepository.save(user); // Save created User
        logger.info("User created: {}", createdUser);
        return UserMapper.toUserDTO(createdUser); // Return UserDTO in response
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        logger.info("Total users found: {}", users.size());

        List<UserDTO> userDTOs = new ArrayList<>();
        // Turn each user from db, into a DTO
        for (User user : users) {
            UserDTO userDTO = UserMapper.toUserDTO(user);
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    public UserDTO getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            UserDTO userDTO = UserMapper.toUserDTO(user.get());
            return userDTO;
        } else {
            logger.error("User not found with ID: {}", id);
            throw new ResourceNotFoundException("User", "id", id);
        }
    }

    @Override // Added 20230715
    public UserDTO getUserByUserName(String userName) {
        Optional<User> user = userRepository.findByUserName(userName);
        if (user.isPresent()) {
            UserDTO userDTO = UserMapper.toUserDTO(user.get());
            return userDTO;
        } else {
            logger.error("User not found with username: {}", userName);

            // Must return/throw something if not found
            // Temporary solution: return 0 as id
            // since strings (username) not accepted yet in ResourceNotFoundException class
            throw new ResourceNotFoundException("User", "id", 0);
        }
    }

    public UserDTO updateUser(UserDTO userDTO) {
        // Get User, and update by passed in UserDTO fields
        Optional<User> existingUserOptional = userRepository.findById(userDTO.getId());
        Optional<User> existingUserOptionalByEmail = userRepository.findByUserName(userDTO.getUserName());

        if (!existingUserOptional.isPresent() && !existingUserOptionalByEmail.isPresent()) {
            logger.error("User not found with ID: {}", userDTO.getId());
            throw new ResourceNotFoundException("User", "id", userDTO.getId());

        } else {
            User existingUser = existingUserOptional.isPresent() ? existingUserOptional.get() : existingUserOptionalByEmail.get();

            existingUser.setFirstName(userDTO.getFirstName());
            existingUser.setLastName(userDTO.getLastName());
            existingUser.setEmail(userDTO.getEmail());
            existingUser.setUserName(userDTO.getUserName());

            logger.debug("Updating user: {}", existingUser);
            User updatedUser = userRepository.save(existingUser); // save updated user
            logger.info("User updated: {}", updatedUser);

            return UserMapper.toUserDTO(updatedUser); // Return as DTO
        }
    }

    public void deleteUser(int id) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            logger.debug("Deleting user: {}", existingUser.get());
            userRepository.deleteById(id);
            logger.info("User with ID {} deleted", id);
        } else {
            logger.error("User not found with ID: {}", id);
            throw new ResourceNotFoundException("User", "id", id);
        }
    }

}
