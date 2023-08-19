package com.fmellberg.economyapp.savingsgoal;

import com.fmellberg.economyapp.user.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

public class SavingsGoalDTO {

    private int id;
    private String goalName;
    private double currentAmountOfCash;
    private double targetAmountOfCash;
    private LocalDate startDate;
    private LocalDate endDate;
    private int userId;

    public SavingsGoalDTO() {
    }

    public SavingsGoalDTO(int id, String goalName, double currentAmountOfCash, double targetAmountOfCash, LocalDate startDate, LocalDate endDate, int userId) {
        this.id = id;
        this.goalName = goalName;
        this.currentAmountOfCash = currentAmountOfCash;
        this.targetAmountOfCash = targetAmountOfCash;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public double getCurrentAmountOfCash() {
        return currentAmountOfCash;
    }

    public void setCurrentAmountOfCash(double currentAmountOfCash) {
        this.currentAmountOfCash = currentAmountOfCash;
    }

    public double getTargetAmountOfCash() {
        return targetAmountOfCash;
    }

    public void setTargetAmountOfCash(double targetAmountOfCash) {
        this.targetAmountOfCash = targetAmountOfCash;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "SavingsGoalDTO{" +
                "id=" + id +
                ", goalName='" + goalName + '\'' +
                ", currentAmountOfCash=" + currentAmountOfCash +
                ", targetAmountOfCash=" + targetAmountOfCash +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", userId=" + userId +
                '}';
    }
}
