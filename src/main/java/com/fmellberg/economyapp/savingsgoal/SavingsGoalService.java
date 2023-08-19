package com.fmellberg.economyapp.savingsgoal;

import java.util.List;
import java.util.Optional;

public interface SavingsGoalService {

    SavingsGoalDTO createSavingsGoal(SavingsGoalDTO savingsGoalDTO);

    List<SavingsGoalDTO> getAllSavingsGoals();

    SavingsGoalDTO getSavingsGoalById(int id);

    SavingsGoalDTO updateSavingsGoal(SavingsGoalDTO savingsGoalDTO);

    void deleteSavingsGoal(int id);
}