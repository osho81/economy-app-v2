package com.fmellberg.economyapp.savingsgoal;

import com.fmellberg.economyapp.exception.ResourceNotFoundException;
import com.fmellberg.economyapp.user.User;
import com.fmellberg.economyapp.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SavingsGoalServiceImpl implements SavingsGoalService {

    private static final Logger logger = LoggerFactory.getLogger(SavingsGoalServiceImpl.class);
    private final SavingsGoalRepository savingsGoalRepository;
    private final UserRepository userRepository;

    @Autowired
    public SavingsGoalServiceImpl(SavingsGoalRepository savingsGoalRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.savingsGoalRepository = savingsGoalRepository;
    }

    public SavingsGoalDTO createSavingsGoal(SavingsGoalDTO savingsGoalDTO) {
        Optional<User> user = userRepository.findById(savingsGoalDTO.getUserId());
        if (user.isPresent()) {
            User existingUser = user.get();

            SavingsGoal savingsGoal = SavingsGoalMapper.toEntity(savingsGoalDTO, existingUser);
            logger.debug("Creating Savings goal: {}", savingsGoal);
            SavingsGoal createdSavingsGoal = savingsGoalRepository.save(savingsGoal);
            logger.info("Savings goal created: {}", createdSavingsGoal);
            return SavingsGoalMapper.toDTO(createdSavingsGoal);
        } else {
            logger.error("User not found for creating savings goal");
            throw new ResourceNotFoundException("User", "id", savingsGoalDTO.getUserId());
        }
    }


    public List<SavingsGoalDTO> getAllSavingsGoals() {
        List<SavingsGoal> savingsGoals = savingsGoalRepository.findAll();
        logger.info("Total savings goals found: {}", savingsGoals.size());

        List<SavingsGoalDTO> savingsGoalDTOs = new ArrayList<>();
        for (SavingsGoal savingsGoal : savingsGoals) {
            SavingsGoalDTO savingsGoalDTO = SavingsGoalMapper.toDTO(savingsGoal);
            savingsGoalDTOs.add(savingsGoalDTO);
        }
        return savingsGoalDTOs;
    }

    public SavingsGoalDTO getSavingsGoalById(int id) {
        Optional<SavingsGoal> savingsGoalOptional = savingsGoalRepository.findById(id);

        if (savingsGoalOptional.isPresent()) {
            SavingsGoal savingsGoal = savingsGoalOptional.get();
            return SavingsGoalMapper.toDTO(savingsGoal);
        } else {
            logger.error("Savings goal not found with ID: {}", id);
            throw new ResourceNotFoundException("SavingsGoal", "id", id);
        }
    }


    public SavingsGoalDTO updateSavingsGoal(SavingsGoalDTO savingsGoalDTO) {
        Optional<SavingsGoal> existingSavingsGoalOptional = savingsGoalRepository.findById(savingsGoalDTO.getId());
        if (existingSavingsGoalOptional.isPresent()) {
            SavingsGoal existingSavingsGoal = existingSavingsGoalOptional.get();

            existingSavingsGoal.setGoalName(savingsGoalDTO.getGoalName());
            existingSavingsGoal.setCurrentAmountOfCash(savingsGoalDTO.getCurrentAmountOfCash());
            existingSavingsGoal.setTargetAmountOfCash(savingsGoalDTO.getTargetAmountOfCash());
            existingSavingsGoal.setStartDate(savingsGoalDTO.getStartDate());
            existingSavingsGoal.setEndDate(savingsGoalDTO.getEndDate());

            logger.debug("Updating Savings goal: {}", existingSavingsGoal);
            SavingsGoal updatedSavingsGoal = savingsGoalRepository.save(existingSavingsGoal);
            logger.info("Savings goal updated: {}", updatedSavingsGoal);

            return SavingsGoalMapper.toDTO(updatedSavingsGoal);
        } else {
            logger.error("Savings goal not found with ID: {}", savingsGoalDTO.getId());
            throw new ResourceNotFoundException("SavingsGoal", "id", savingsGoalDTO.getId());
        }
    }

    public void deleteSavingsGoal(int id) {
        Optional<SavingsGoal> existingSavingsGoal = savingsGoalRepository.findById(id);
        if (existingSavingsGoal.isPresent()) {
            logger.debug("Deleting Savings goal: {}", existingSavingsGoal.get());
            savingsGoalRepository.deleteById(id);
            logger.info("Savings goal with ID {} deleted", id);
        } else {
            logger.error("Savings goal not found with ID: {}", id);
            throw new ResourceNotFoundException("SavingsGoal", "id", id);
        }
    }
}
