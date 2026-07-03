package edu.unl.cc.proyect.logica.view;

import edu.unl.cc.proyect.logica.domain.Reservation;
import edu.unl.cc.proyect.logica.domain.Player;
import edu.unl.cc.proyect.logica.domain.Organization;
import edu.unl.cc.proyect.logica.domain.Payment;
import edu.unl.cc.proyect.logica.domain.PaymentStatus;
import edu.unl.cc.proyect.logica.domain.Field;
import edu.unl.cc.proyect.logica.domain.Schedule;

import java.util.List;
import java.util.Scanner;

public class PlayerView {

    private final Scanner scanner = new Scanner(System.in);
    private Player player;
    private Organization organization;
    private List<Payment> paymentsDay;
    private Reservation activeReservation;

    public void menuReservation(Player loggedPlayer, Organization organization, List<Payment> paymentsDay) {
        this.player = loggedPlayer;
        this.organization = organization;
        this.paymentsDay = paymentsDay;
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- MENU DE RESERVAS ---");
            System.out.println("1. Reservar cancha");
            System.out.println("2. Cancelar reserva");
            System.out.println("3. Ver resumen de pago (Recibo)");
            System.out.println("4. Salir");
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
                    viewPaymentSummary();
                    break;

                case 4:
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

        if (activeReservation != null) {
            System.out.println("\n[ALERTA] El jugador ya tiene una reserva registrada.");
            return;
        }

        FieldView.displayCatalogConsola();
        Field[] catalogo = FieldView.getFieldsCatalog();

        System.out.print("\nSeleccione el número de Cancha ID que desea reservar: ");
        int canchaIdx = scanner.nextInt() - 1;
        scanner.nextLine();

        if (canchaIdx < 0 || canchaIdx >= catalogo.length) {
            System.out.println(">> ERROR: ID de cancha no válido.");
            return;
        }

        Field canchaSeleccionada = catalogo[canchaIdx];
        List<Schedule> horarios = canchaSeleccionada.getSchedules();

        System.out.print("Seleccione el número de Slot (Bloque Horario) que desea: ");
        int slotIdx = scanner.nextInt() - 1;
        scanner.nextLine();

        if (slotIdx < 0 || slotIdx >= horarios.size()) {
            System.out.println(">> ERROR: Slot de horario no válido.");
            return;
        }

        Schedule horarioSeleccionado = horarios.get(slotIdx);

        if (horarioSeleccionado.isReserved()) {
            System.out.println(">> ERROR: Ese bloque horario ya está ocupado. Intente con otro.");
            return;
        }

        System.out.print("Ingrese el número de jugadores que asistirán: ");
        int numJugadores = scanner.nextInt();
        scanner.nextLine();

        horarioSeleccionado.setReserved(true);

        activeReservation = new Reservation(player, canchaSeleccionada, horarioSeleccionado, numJugadores);

        // Aquí usamos el constructor que tú creaste
        Payment nuevoPago = new Payment(activeReservation);
        nuevoPago.setStatus(PaymentStatus.PAID);
        paymentsDay.add(nuevoPago);

        System.out.println("\n=============================================");
        System.out.println("        ¡RESERVA CREADA CORRECTAMENTE!       ");
        System.out.println("=============================================");
        System.out.println("Jugador:             " + player.getFullName());
        System.out.println("Cancha Seleccionada: " + activeReservation.getField().getName() + " (" + activeReservation.getField().getFieldType() + ")");
        System.out.println("Fecha de Registro:   " + activeReservation.getDate());
        System.out.println("Bloque reservado:    " + activeReservation.getSchedule().getStartTime() + " a " + activeReservation.getSchedule().getEndTime());
        System.out.println("Total Jugadores:     " + activeReservation.getNumberOfPlayers());
        System.out.println("Monto a pagar:       $" + nuevoPago.calculateAmountToBePaid());
        System.out.println("=============================================");
    }

    public void cancelReservation() {
        if (player == null || activeReservation == null) {
            System.out.println("\n[AVISO] El jugador no tiene una reserva activa que cancelar.");
            return;
        }

        if (activeReservation.getSchedule() != null) {
            activeReservation.getSchedule().setReserved(false);
        }

        // Removemos el pago asociado a esta reserva
        for (int i = 0; i < paymentsDay.size(); i++) {
            if (paymentsDay.get(i).getReservation() == activeReservation) {
                paymentsDay.remove(i);
                break;
            }
        }

        System.out.println("\nReserva cancelada correctamente.");
        System.out.println("Se ha liberado el horario para el jugador: " + player.getFullName());
        activeReservation = null;
    }

    public void viewPaymentSummary() {
        if (activeReservation == null) {
            System.out.println("\n[AVISO] No tienes ninguna reserva activa.");
            return;
        }

        Payment pagoUsuario = null;
        for (Payment p : paymentsDay) {
            if (p.getReservation() == activeReservation) {
                pagoUsuario = p;
                break;
            }
        }

        if (pagoUsuario != null) {
            System.out.println("\n=============================================");
            // Aquí llamamos a tu método que genera el resumen
            System.out.println(pagoUsuario.generatePaymentSummary());
            System.out.println("=============================================");
        } else {
            System.out.println("\n[AVISO] No se encontró el recibo de pago para la reserva actual.");
        }
    }
}