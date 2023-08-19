package com.fmellberg.economyapp.savingsgoal;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fmellberg.economyapp.user.User;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "savings_goals")
@EntityListeners(AuditingEntityListener.class)
public class SavingsGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "goal_name", nullable = false)
    private String goalName;

    @Column(name = "current_amount_of_cash", nullable = false)
    private double currentAmountOfCash;

    @Column(name = "target_amount_of_cash", nullable = false)
    private double targetAmountOfCash;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @JsonBackReference // Avoid infinite recursion
    @ManyToOne // Many (different) savings-goal can have one user (each)
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    @CreatedDate
    @Column(name = "createdat")
    private Timestamp createdAt;

    @LastModifiedDate
    @Column(name = "last_modified")
    private Timestamp lastModified;

    public SavingsGoal() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public String toString() {
        return "SavingGoal{" +
                "id=" + id +
                ", goalName='" + goalName + '\'' +
                ", currentAmountOfCash=" + currentAmountOfCash +
                ", targetAmountOfCash=" + targetAmountOfCash +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
//                ", user=" + user + // Not working on mock data (missing fields)
                ", createdAt=" + createdAt +
                ", updatedAt=" + lastModified +
                '}';
    }
}
