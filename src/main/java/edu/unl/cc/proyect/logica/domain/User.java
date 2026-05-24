package edu.unl.cc.proyect.logica.domain;

public class User {

    private int organizationId;
    private int userID;
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private RoleType role;

    // --- Constructor ---
    public User(int organizationId, int userID, String username, String password, String phoneNumber, String email, RoleType role) {
        this.organizationId = organizationId;
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.role = role;
    }

    public boolean login() {
        System.out.println("Intentando iniciar sesión para: " + this.username);
        return false; // Por defecto retorna false hasta que se implemente
    }

    public boolean register() {
        System.out.println("Registrando al usuario: " + this.username);
        return false;
    }

    public boolean hasPermission(String action) {
        if (this.role == RoleType.ADMIN) {
            return true;
        }
        return false;
    }

    // --- Getters y Setters ---

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public void setRole(RoleType role) {
        this.role = role;
    }
}
