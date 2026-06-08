package edu.unl.cc.proyect.logica.view;

import edu.unl.cc.proyect.logica.domain.Reservation;
import edu.unl.cc.proyect.logica.domain.Player;
import edu.unl.cc.proyect.logica.domain.Organization;
import edu.unl.cc.proyect.logica.domain.Payment;
import java.util.List;
import java.util.Scanner;

public class PlayerView {

    private final Scanner scanner = new Scanner(System.in);
    private Player player;
    private Organization organization;

    public void menuReservation(Player loggedPlayer, Organization organization, List<Payment> paymentsDay) {
        this.player = loggedPlayer;
        this.organization = organization;
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- MENU DE RESERVAS ---");
            System.out.println("1. Reservar cancha");
            System.out.println("2. Cancelar reserva");
            System.out.println("3. Salir");
            System.out.print("Ingrese una opción: ");
            
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    makeReservation();
                    break;

                case 2:
                    cancelReservation();
                    break;

                case 3:
                    System.out.println("Regresando al menú de cuentas...");
                    salir = true;
                    break;

                default:
                    System.out.println(">> Opción no válida.");
                    break;
            }
        }
    }

    public void makeReservation() {
        if (player == null) {
            System.out.println("[ERROR] No hay un jugador autenticado.");
            return;
        }

        if (player.getReservation() != null) {
            System.out.println("\n[AVISO] El jugador ya tiene una reserva registrada.");
            return;
        }

        if (organization.getFields().isEmpty()) {
            System.out.println("\n[AVISO] No hay canchas disponibles en este momento.");
            return;
        }

        System.out.println("Reserva creada correctamente.");
        System.out.println("Jugador: " + player.getFullName());
    }

    public void cancelReservation() {
        if (player == null || player.getReservation() == null) {
            System.out.println("\n[AVISO] El jugador no tiene una reserva activa.");
            return;
        }

        if (player.getReservation().getSchedule() != null) {
            player.getReservation().getSchedule().setReserved(false);
        }
        
        System.out.println("Reserva cancelada correctamente.");
        System.out.println("Jugador: " + player.getFullName());
    }
}
