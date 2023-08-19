package com.fmellberg.economyapp.user.DTO;

import com.fmellberg.economyapp.savingsgoal.SavingsGoal;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Data transfer object
// Fields in User, but not here in UserDTO: creates/modified date, saving goals list
public class UserDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;

    // Add savingsGoals (manyToOne) 230708
    private List<SavingsGoal> savingGoals;

    public UserDTO() {
    }

    public UserDTO(int id, String firstName, String lastName, String email, String userName, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public UserDTO(String firstName, String lastName, String email, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    // Add savingsGoals to constructor 230708
    public UserDTO(int id, String firstName, String lastName, String email, String userName, String password, ArrayList<SavingsGoal> savingGoals) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
        return "UserDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return id == userDTO.id && firstName.equals(userDTO.firstName) && lastName.equals(userDTO.lastName) && email.equals(userDTO.email) && userName.equals(userDTO.userName) && password.equals(userDTO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, userName, password);
    }
}
