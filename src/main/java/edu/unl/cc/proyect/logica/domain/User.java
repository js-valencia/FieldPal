package edu.unl.cc.proyect.logica.domain;

import java.io.Serializable;
import java.util.Objects;

public abstract class User implements Serializable {

    private String username;
    private String password;

    public User(String fullName, String username, String password, String phoneNumber, String email) {
        this.username = Objects.requireNonNull(username, "Username cannot be null");
        this.password = Objects.requireNonNull(password, "Password cannot be null");
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

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}