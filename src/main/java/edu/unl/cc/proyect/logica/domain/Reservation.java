package edu.unl.cc.proyect.logica.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Reservation implements Serializable {

    private LocalDateTime date;
    private int numberOfPlayers;
    private User user;
    private Field field;
    private Schedule schedule;

    public Reservation() {}

    public Reservation(User user, Field field, Schedule schedule, int numberOfPlayers) {
        this.user = user;
        this.field = field;
        this.schedule = schedule;
        this.numberOfPlayers = numberOfPlayers;
        this.date = LocalDateTime.now();
    }

    public boolean validateAvailability() {
        if (this.field == null || this.schedule == null) {
            return false;
        }
        return !this.schedule.isReserved();
    }

    public void cancelReservation() {
        if (this.schedule != null) {
            this.schedule.setReserved(false);
        }

        String clientName = (this.user != null) ? this.user.getFullName() : "Cliente Desconocido";
        System.out.println("[SISTEMA] Reserva cancelada con éxito para el usuario: " + clientName);
    }

    // --- GETTERS y SETTERS ---
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

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}