package com.osho81.economyapp.customer.DTO;

import com.osho81.economyapp.savingsgoal.SavingsGoal;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Data transfer object
// Fields in Customer, but not here in CustomerDTO: creates/modified date, saving goals list
public class CustomerDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String customerName;
    private String password;

    // Add savingsGoals (manyToOne) 230708
    private List<SavingsGoal> savingGoals;

    public CustomerDTO() {
    }

    public CustomerDTO(int id, String firstName, String lastName, String email, String customerName, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.customerName = customerName;
        this.password = password;
    }

    public CustomerDTO(String firstName, String lastName, String email, String customerName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.customerName = customerName;
        this.password = password;
    }

    // Add savingsGoals to constructor 230708
    public CustomerDTO(int id, String firstName, String lastName, String email, String customerName, String password, ArrayList<SavingsGoal> savingGoals) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.customerName = customerName;
        this.password = password;

        // Add savingsGoals (manyToOne) 230708
        this.savingGoals = savingGoals;
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

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", customerName='" + customerName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDTO customerDTO = (CustomerDTO) o;
        return id == customerDTO.id && firstName.equals(customerDTO.firstName) && lastName.equals(customerDTO.lastName) && email.equals(customerDTO.email) && customerName.equals(customerDTO.customerName) && password.equals(customerDTO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, customerName, password);
    }
}
