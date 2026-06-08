package edu.unl.cc.proyect.logica.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Reservation implements Serializable {

    private LocalDateTime date;
    private int numberOfPlayers;
    private Player player;
    private Field field;
    private Schedule schedule;

    public Reservation() {}

    public Reservation(Player player, Field field, Schedule schedule, int numberOfPlayers) {
        this.player = player;
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
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