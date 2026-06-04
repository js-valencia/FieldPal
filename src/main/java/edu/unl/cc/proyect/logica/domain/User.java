package edu.unl.cc.proyect.logica.domain;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    private String fullName;
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private RoleType role;

    public User(String fullName, String username, String password, String phoneNumber, String email, RoleType role) {
        this.fullName = Objects.requireNonNull(fullName, "Full name cannot be null");
        this.username = Objects.requireNonNull(username, "Username cannot be null");
        this.password = Objects.requireNonNull(password, "Password cannot be null");
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.role = Objects.requireNonNull(role, "Role cannot be null");
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleType getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'';
    }
}