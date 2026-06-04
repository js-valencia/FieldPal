package edu.unl.cc.proyect.logica.domain;

public class Player extends User{

    public Player(String fullName, String username, String password, String phoneNumber, String email, RoleType role) {
        super(fullName, username, password, phoneNumber, email, role);
    }
}
