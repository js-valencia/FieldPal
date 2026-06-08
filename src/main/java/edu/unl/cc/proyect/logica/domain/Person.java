package edu.unl.cc.proyect.logica.domain;

public class Person {

    private String fullName;
    private String phoneNumber;
    private String email;

    private User user;

    public Person(String fullName, String phoneNumber, String email, User user) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.user = user;
    }

    public void register(String username, String password) {
        if (this.user != null) {
            this.user.setUsername(username);
            this.user.setPassword(password);
        } else {
            throw new IllegalStateException("No se puede registrar porque la persona no tiene un dato asignado.");
        }
    }

    public boolean login(String username, String password) {
        if (this.user != null) {

            boolean usuarioCorrecto = this.user.getUsername().equals(username);
            boolean contrasenaCorrecta = this.user.getPassword().equals(password);

            return usuarioCorrecto && contrasenaCorrecta;
        }

        return false;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    @Override
    public String toString() {
        return "Person{" +
                "fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
