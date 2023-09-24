package com.osho81.economyapp.user;

// This is the App-User (compare normal user or customer),
// i.e. the user that will or will not get authenticated

// This class uses lombok, to save time

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Changed from Integer to int
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    // Enum role is strings: user, admin
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override // Should return a list of roles
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return object with the (role) enum
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    // Constructor with all fields, except id
    public User(String firstname, String lastname, String email, String password, Role role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    ///----- Implement methods from UserDetail (Interface)-----///
    @Override
    public String getPassword() {
        return password; // Return value from entity field
    }
    @Override
    public String getUsername() {
        return email; // Return value from entity field
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Note the negation
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

