package edu.unl.cc.proyect.logica.domain;

import java.time.LocalDateTime;

public class Player extends Person{

    private Reservation reservation;

    public Player(String fullName, String phoneNumber, String email, User user) {
        super(fullName, phoneNumber, email, user);
    }

    public void makeReservation(LocalDateTime date, int numberOfPlayers) {
        Reservation newReserve = new Reservation(date, numberOfPlayers);

        if (newReserve.validateAvailability()) {
            this.reservation = newReserve;
        } else {
            throw new IllegalStateException("No hay disponibilidad para esta fecha.");
        }
    }

    public void cancelReservation() {
        if (this.reservation != null) {
            this.reservation = null;
        } else {
            throw new IllegalStateException("El jugador no tiene ninguna reserva activa para cancelar.");
        }
    }

    public Reservation getReservation() {
        return this.reservation;
    }
}
