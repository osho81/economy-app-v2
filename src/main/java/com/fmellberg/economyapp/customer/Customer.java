package com.fmellberg.economyapp.customer;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fmellberg.economyapp.savingsgoal.SavingsGoal;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "customers")
@EntityListeners(AuditingEntityListener.class) // Needed for e.g.  @CreatedDate/ @LastModifiedDate
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "customername", nullable = false, unique = true)
    private String customerName;
    @Column(name = "password", nullable = false)
    private String password;

    // One Customer can have many savings-goal
    // If customer deleted, related savings-goal are deleted too
    @JsonManagedReference // Avoid infinite recursion
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SavingsGoal> savingGoals;
    @CreatedDate // Works on post method creations
    @Column(name = "created_at")
    private Timestamp createdAt;
    @LastModifiedDate // Both post and put activates this
    @Column(name = "last_modified")
    private Timestamp lastModified;

    public Customer() {
    }

    public Customer(int id, String firstName, String lastName, String email, String customerName, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.customerName = customerName;
        this.password = password;
    }

    public Customer(String firstName, String lastName, String email, String customerName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.customerName = customerName;
        this.password = password;
//        this.savingGoals = savingGoals;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SavingsGoal> getSavingGoals() {
        return savingGoals;
    }

    public void setSavingGoals(List<SavingsGoal> savingGoals) {
        this.savingGoals = savingGoals;
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
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", customerName='" + customerName + '\'' +
                ", password='" + password + '\'' +
                ", savingGoals=" + savingGoals +
                ", createdAt=" + createdAt +
                ", lastModified=" + lastModified +
                '}';
    }
}
