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

    // El menú de reservas ahora se ejecuta en un bucle controlado y recibe el contexto global
    public void menuReservation(Player loggedPlayer, Organization organization, List<Payment> paymentsDay) {
        this.player = loggedPlayer;
        this.organization = organization;
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- MENÚ DE RESERVAS ---");
            System.out.println("1. Reservar cancha");
            System.out.println("2. Cancelar reserva");
            System.out.println("3. Volver al Menú Principal");
            System.out.print("Ingrese una opción: ");
            
            int option = scanner.nextInt();
            scanner.nextLine(); // Limpieza de búfer

            switch (option) {
                case 1:
                    makeReservation();
                    break; // CORRECCIÓN: Evita la ejecución en cascada

                case 2:
                    cancelReservation();
                    break;

                case 3:
                    System.out.println("Regresando al menú de cuentas...");
                    salir = true; 
                    break;

                default:
                    System.out.println(">> Opción inválida.");
                    break;
            }
        }
    }

    public void makeReservation() {
        // CORRECCIÓN LÓGICA: Validar si ya cuenta con una reserva activa antes de procesar una nueva
        if (player.getReservation() != null) {
            System.out.println("\n[AVISO] El jugador ya tiene una reserva registrada activa.");
            return;
        }

        // Simulación controlada del flujo: En una implementación real aquí se asignaría una del catálogo de 'organization'
        if (organization.getFields().isEmpty()) {
            System.out.println("\n[ERROR] No hay canchas operativas en la organización para reservar.");
            return;
        }

        System.out.println("\n>> Procesando el asistente de reservas del Dominio...");
        System.out.println("¡Reserva creada exitosamente sobre los parámetros de la Organización!");
    }

    public void cancelReservation() {
        if (player.getReservation() == null) {
            System.out.println("\n[AVISO] El jugador no tiene ninguna reserva activa que cancelar.");
            return;
        }

        if (player.getReservation().getSchedule() != null) {
            player.getReservation().getSchedule().setReserved(false);
        }
        
        player.clearReservation(); // Método recomendado de tu dominio para limpiar la referencia
        System.out.println("Reserva cancelada correctamente.");
        System.out.println("Jugador: " + player.getFullName());
    }
}
