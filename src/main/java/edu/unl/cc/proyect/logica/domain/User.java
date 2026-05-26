package edu.unl.cc.proyect.logica.domain;

import java.util.Objects;

public class User {

    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private String fullName;
    private RoleType role;

    // Constructor completo
    public User(String username, String password, String phoneNumber, String email, String fullName, RoleType role) {
        this.username = Objects.requireNonNull(username, "El username no puede ser nulo");
        this.password = Objects.requireNonNull(password, "El password no puede ser nulo");
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.fullName = fullName;
        this.role = Objects.requireNonNull(role, "El rol no puede ser nulo");
    }

    public boolean hasPermission(String action) {
        if (action == null || action.isBlank()) {
            return false;
        }

        if (this.role == RoleType.ADMIN) {
            return true;
        }

        return "PLAY_GAME".equalsIgnoreCase(action);
    }

    // --- GETTERS Y SETTERS (Encapsulamiento) ---

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }
}
