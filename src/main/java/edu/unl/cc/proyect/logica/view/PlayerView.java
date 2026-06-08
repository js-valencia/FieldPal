package edu.unl.cc.proyect.logica.view;

import edu.unl.cc.proyect.logica.domain.Person;
import edu.unl.cc.proyect.logica.domain.Reservation;
import edu.unl.cc.proyect.logica.domain.User;
import edu.unl.cc.proyect.logica.domain.Player;

import javax.swing.*;
import java.util.Scanner;

public class PlayerView {

    Player player;
    Menu menu = new Menu();
    Scanner scanner = new Scanner(System.in);

    public void menuReservation(){

        System.out.println("\n---MENU DE RESERVAS---\n");
        System.out.println("1. Reservar cancha");
        System.out.println("2. Cancelar reserva");
        System.out.println("3. Salir");
        System.out.print("Ingrese una opcion: ");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                makeReservation();
                break;
            case 2:
                cancelReservation();
                break;
            case 3:
                System.out.println("Regresando al menu principal...");
                menu.playerMenu();
                break;
            default:
                System.out.println("Opcion Invalida, eliga otra opcion.");
                break;
        }
    }

    public void makeReservation(){
        if (player.getReservation() == null) {
            throw new IllegalArgumentException(
                    "La reserva no puede ser nula");
        }

        if (player.getReservation() != null) {
            throw new IllegalStateException(
                    "El jugador ya tiene una reserva registrada");
        }

        if (!player.getReservation().validateAvailability()) {
            throw new IllegalStateException(
                    "La cancha no esta disponible");
        }

        Reservation reservation = player.getReservation();
        reservation.setSchedule(player.getReservation().getSchedule());

        System.out.println("Reserva creada correctamente.");
        System.out.println("Jugador: " + player.getFullName());
        System.out.println("Fecha: " + reservation.getDate());
        System.out.println("Numero de jugadores: " + reservation.getNumberOfPlayers());
    }

    public void cancelReservation(){
        if (player.getReservation() == null) {
            throw new IllegalStateException(
                    "El jugador no tiene una reserva activa");
        }

        if (player.getReservation().getSchedule() != null) {
            player.getReservation().getSchedule().setReserved(false);
        }
        Reservation reservation = player.getReservation();
        reservation = null;
        System.out.println("Reserva cancelada correctamente.");
        System.out.println("Jugador: " + player.getFullName());
    }
}
