package com.osho81.economyapp.savingsgoal;

import java.util.List;

public interface SavingsGoalService {

    SavingsGoalDTO createSavingsGoal(SavingsGoalDTO savingsGoalDTO);

    List<SavingsGoalDTO> getAllSavingsGoals();

    SavingsGoalDTO getSavingsGoalById(int id);

    SavingsGoalDTO updateSavingsGoal(SavingsGoalDTO savingsGoalDTO);

    void deleteSavingsGoal(int id);
}