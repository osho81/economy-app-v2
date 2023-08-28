package com.osho81.economyapp.savingsgoal;

import com.osho81.economyapp.customer.Customer;

public class SavingsGoalMapper {

    public static SavingsGoalDTO toDTO(SavingsGoal savingsGoal) {
        SavingsGoalDTO dto = new SavingsGoalDTO();
        dto.setId(savingsGoal.getId());
        dto.setGoalName(savingsGoal.getGoalName());
        dto.setCurrentAmountOfCash(savingsGoal.getCurrentAmountOfCash());
        dto.setTargetAmountOfCash(savingsGoal.getTargetAmountOfCash());
        dto.setStartDate(savingsGoal.getStartDate());
        dto.setEndDate(savingsGoal.getEndDate());
        dto.setCustomerId(savingsGoal.getCustomer().getId());
        return dto;
    }

    public static SavingsGoal toEntity(SavingsGoalDTO dto, Customer customer) {
        SavingsGoal savingsGoal = new SavingsGoal();
        savingsGoal.setId(dto.getId());
        savingsGoal.setGoalName(dto.getGoalName());
        savingsGoal.setCurrentAmountOfCash(dto.getCurrentAmountOfCash());
        savingsGoal.setTargetAmountOfCash(dto.getTargetAmountOfCash());
        savingsGoal.setStartDate(dto.getStartDate());
        savingsGoal.setEndDate(dto.getEndDate());
        savingsGoal.setCustomer(customer);
        return savingsGoal;
    }
}
