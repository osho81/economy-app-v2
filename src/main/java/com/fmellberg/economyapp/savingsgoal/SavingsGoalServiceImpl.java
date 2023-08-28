package com.fmellberg.economyapp.savingsgoal;

import com.fmellberg.economyapp.customer.CustomerRepository;
import com.fmellberg.economyapp.exception.ResourceNotFoundException;
import com.fmellberg.economyapp.customer.Customer;
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
    private final CustomerRepository customerRepository;

    @Autowired
    public SavingsGoalServiceImpl(SavingsGoalRepository savingsGoalRepository, CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.savingsGoalRepository = savingsGoalRepository;
    }

    public SavingsGoalDTO createSavingsGoal(SavingsGoalDTO savingsGoalDTO) {
        Optional<Customer> customer = customerRepository.findById(savingsGoalDTO.getCustomerId());
        if (customer.isPresent()) {
            Customer existingCustomer = customer.get();

            SavingsGoal savingsGoal = SavingsGoalMapper.toEntity(savingsGoalDTO, existingCustomer);
            logger.debug("Creating Savings goal: {}", savingsGoal);
            SavingsGoal createdSavingsGoal = savingsGoalRepository.save(savingsGoal);
            logger.info("Savings goal created: {}", createdSavingsGoal);
            return SavingsGoalMapper.toDTO(createdSavingsGoal);
        } else {
            logger.error("Customer not found for creating savings goal");
            throw new ResourceNotFoundException("Customer", "id", savingsGoalDTO.getCustomerId());
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
