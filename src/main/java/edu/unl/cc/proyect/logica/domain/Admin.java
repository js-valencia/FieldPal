package edu.unl.cc.proyect.logica.domain;

public class Admin extends User{

    public Admin(String fullName, String username, String password, String phoneNumber, String email, RoleType role) {
        super(fullName, username, password, phoneNumber, email, role);
    }

    public void register(String username, String password){
    }

    public void login(String username, String password){
    }

}
