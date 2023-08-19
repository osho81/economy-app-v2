package com.fmellberg.economyapp.savingsgoal;

import com.fmellberg.economyapp.user.User;

import java.util.ArrayList;
import java.util.List;

public class SavingsGoalMapper {

    public static SavingsGoalDTO toDTO(SavingsGoal savingsGoal) {
        SavingsGoalDTO dto = new SavingsGoalDTO();
        dto.setId(savingsGoal.getId());
        dto.setGoalName(savingsGoal.getGoalName());
        dto.setCurrentAmountOfCash(savingsGoal.getCurrentAmountOfCash());
        dto.setTargetAmountOfCash(savingsGoal.getTargetAmountOfCash());
        dto.setStartDate(savingsGoal.getStartDate());
        dto.setEndDate(savingsGoal.getEndDate());
        dto.setUserId(savingsGoal.getUser().getId());
        return dto;
    }

    public static SavingsGoal toEntity(SavingsGoalDTO dto, User user) {
        SavingsGoal savingsGoal = new SavingsGoal();
        savingsGoal.setId(dto.getId());
        savingsGoal.setGoalName(dto.getGoalName());
        savingsGoal.setCurrentAmountOfCash(dto.getCurrentAmountOfCash());
        savingsGoal.setTargetAmountOfCash(dto.getTargetAmountOfCash());
        savingsGoal.setStartDate(dto.getStartDate());
        savingsGoal.setEndDate(dto.getEndDate());
        savingsGoal.setUser(user);
        return savingsGoal;
    }
}
