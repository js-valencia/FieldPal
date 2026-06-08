package edu.unl.cc.proyect.logica.view;

import edu.unl.cc.proyect.logica.domain.Reservation;
import edu.unl.cc.proyect.logica.domain.Player;
import edu.unl.cc.proyect.logica.domain.Organization;
import edu.unl.cc.proyect.logica.domain.Payment;
import edu.unl.cc.proyect.logica.domain.Field;
import edu.unl.cc.proyect.logica.domain.Schedule;

import java.time.LocalDateTime;
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
            scanner.nextLine(); // Limpiar búfer

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

        // 1. Validar si ya tiene una reserva activa
        if (player.getReservation() != null) {
            System.out.println("\n[ALERTA] El jugador ya tiene una reserva registrada.");
            return;
        }

        // 2. Mostrar el catálogo de canchas disponibles usando FieldView
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
        Schedule[] horarios = canchaSeleccionada.getSchedules();

        // 3. Selección del bloque horario (Schedule)
        System.out.print("Seleccione el número de Slot (Bloque Horario) que desea: ");
        int slotIdx = scanner.nextInt() - 1;
        scanner.nextLine();

        if (slotIdx < 0 || slotIdx >= horarios.length) {
            System.out.println(">> ERROR: Slot de horario no válido.");
            return;
        }

        Schedule horarioSeleccionado = horarios[slotIdx];

        // 4. Verificar disponibilidad del bloque
        if (horarioSeleccionado.isReserved()) {
            System.out.println(">> ERROR: Ese bloque horario ya está ocupado. Intente con otro.");
            return;
        }

        // 5. Capturar número de jugadores para la reserva
        System.out.print("Ingrese el número de jugadores que asistirán: ");
        int numJugadores = scanner.nextInt();
        scanner.nextLine();

        // 6. Marcar el horario como reservado y construir el LocalDateTime de la reserva
        horarioSeleccionado.setReserved(true);
        
        // Combinamos la fecha de la cancha con la hora de inicio del bloque horario
        LocalDateTime fechaHoraReserva = LocalDateTime.of(canchaSeleccionada.getDate(), horarioSeleccionado.getStartTime());

        // 7. Instanciar la Reserva en el Dominio
        // (Nota: Ajusta los parámetros del constructor según requiera exactamente tu clase Reservation)
        Reservation nuevaReserva = new Reservation(fechaHoraReserva, numJugadores, horarioSeleccionado);
        player.setReservation(nuevaReserva);

        // 8. Informar detalladamente al usuario
        System.out.println("\n=============================================");
        System.out.println("        ¡RESERVA CREADA CORRECTAMENTE!       ");
        System.out.println("=============================================");
        System.out.println("Jugador:             " + player.getFullName());
        System.out.println("Cancha Seleccionada: " + canchaSeleccionada.getName() + " (" + canchaSeleccionada.getFieldType() + ")");
        System.out.println("Fecha y Hora (LDT):  " + fechaHoraReserva);
        System.out.println("Bloque reservado:    " + horarioSeleccionado.getStartTime() + " a " + horarioSeleccionado.getEndTime());
        System.out.println("Total Jugadores:     " + numJugadores);
        System.out.println("=============================================");
    }

    public void cancelReservation() {
        if (player == null || player.getReservation() == null) {
            System.out.println("\n[AVISO] El jugador no tiene una reserva activa que cancelar.");
            return;
        }

        // Liberamos el horario asignado
        if (player.getReservation().getSchedule() != null) {
            player.getReservation().getSchedule().setReserved(false);
        }
        
        System.out.println("\nReserva cancelada correctamente.");
        System.out.println("Se ha liberado el horario para el jugador: " + player.getFullName());
        player.clearReservation(); // O player.setReservation(null); según tu estructura
    }
}
 
