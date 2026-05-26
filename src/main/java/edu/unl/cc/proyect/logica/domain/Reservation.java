package edu.unl.cc.proyect.logica.domain;

import java.time.LocalDateTime;

public class Reservation {

    private LocalDateTime date;
    private int numberOfPlayers;
    private User user;
    private Field field;

    public Reservation() {}

    public Reservation(User user, Field field, int numberOfPlayers) {
        this.user = user;
        this.field = field;
        this.numberOfPlayers = numberOfPlayers;
        this.date = LocalDateTime.now();
    }

    public boolean validateAvailability() {
        if (this.field == null) {
            return false;
        }
        if ("MAINTENANCE".equalsIgnoreCase(this.field.getStatus())) {
            return false;
        }
        boolean isFieldAvailable = this.field.searchAvailability(this.date);
        return isFieldAvailable;
    }

    public void cancelReservation() {
        if (this.field != null) {
            this.field.setStatus("AVAILABLE");
        }

        String clientName = (this.user != null) ? this.user.getFullName() : "Cliente Desconocido";
        System.out.println("[SISTEMA] Reserva cancelada con éxito para el usuario: " + clientName);
    }

    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Field getField() {
        return field;
    }
    public void setField(Field field) {
        this.field = field;
    }
}