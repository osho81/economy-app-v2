package com.osho81.economyapp.savingsgoal;

import java.time.LocalDate;

public class SavingsGoalDTO {

    private int id;
    private String goalName;
    private double currentAmountOfCash;
    private double targetAmountOfCash;
    private LocalDate startDate;
    private LocalDate endDate;
    private int customerId;

    public SavingsGoalDTO() {
    }

    public SavingsGoalDTO(int id, String goalName, double currentAmountOfCash, double targetAmountOfCash, LocalDate startDate, LocalDate endDate, int customerId) {
        this.id = id;
        this.goalName = goalName;
        this.currentAmountOfCash = currentAmountOfCash;
        this.targetAmountOfCash = targetAmountOfCash;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customerId = customerId;
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

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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
                ", customerId=" + customerId +
                '}';
    }
}
