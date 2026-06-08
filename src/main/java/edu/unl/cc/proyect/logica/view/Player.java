package edu.unl.cc.proyect.logica.view;

import edu.unl.cc.proyect.logica.domain.Person;
import edu.unl.cc.proyect.logica.domain.Reservation;
import edu.unl.cc.proyect.logica.domain.User;

public class Player extends Person {

    private Reservation reservation;

    public Player(String fullName, String phoneNumber, String email, User user) {
        super(fullName, phoneNumber, email, user);
    }

    public void makeReservation(){
        if (reservation == null) {
            System.out.println("No se pudo crear la reserva: la reserva no puede ser nula.");
            throw new IllegalArgumentException(
                    "La reserva no puede ser nula");
        }

        if (this.reservation != null) {
            System.out.println("No se pudo crear la reserva: el jugador ya tiene una reserva registrada.");
            throw new IllegalStateException(
                    "El jugador ya tiene una reserva registrada");
        }

        if (!reservation.validateAvailability()) {
            System.out.println("No se pudo crear la reserva: la cancha no esta disponible.");
            throw new IllegalStateException(
                    "La cancha no esta disponible");
        }

        this.reservation = reservation;
        System.out.println("Reserva creada correctamente.");
        System.out.println("Jugador: " + getFullName());
        System.out.println("Fecha: " + reservation.getDate());
        System.out.println("Numero de jugadores: " + reservation.getNumberOfPlayers());
    }

    public void cancelReservation(){
        if (this.reservation == null) {
            System.out.println("No se pudo cancelar la reserva: el jugador no tiene una reserva activa.");
            throw new IllegalStateException(
                    "El jugador no tiene una reserva activa");
        }

        if (this.reservation.getSchedule() != null) {
            this.reservation.getSchedule().setReserved(false);
        }

        this.reservation = null;
        System.out.println("Reserva cancelada correctamente.");
        System.out.println("Jugador: " + getFullName());
    }
}
