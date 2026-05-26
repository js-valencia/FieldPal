package edu.unl.cc.proyect.logica.view;

import edu.unl.cc.proyect.logica.domain.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReserveExecutor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        User playerUser = new User(1, 101, "juan_perez", "pass123", "0987654321", "juan@example.com", RoleType.PLAYER);
        Field field = new Field(1, 1, "Abogado", "Cancha Sintética", 20.0f, "abierto", "13:00 - 22:00");

        // Definir un horario disponible (de 18:00 a 20:00 - son 2 horas de duración)
        Schedule schedule = new Schedule(1, LocalTime.of(18, 0), LocalTime.of(20, 0), false);
        List<Schedule> requestedSchedules = new ArrayList<>();
        requestedSchedules.add(schedule);

        System.out.println("--- Creando Reserva en FieldPal ---");
        Reservation reservation = new Reservation(1, 1, playerUser, field, LocalDateTime.now(), 0f, "PENDING", false);

        boolean isBooked = reservation.makeReservation(requestedSchedules);
        if (isBooked) {
            System.out.println("¡Reserva confirmada con éxito!");
            System.out.println("Costo total calculado: $" + reservation.getTotalAmount());
            System.out.println("Estado de la reserva: " + reservation.getReservationStatus());
            System.out.println("¿El horario está bloqueado?: " + schedule.isReserved());
        } else {
            System.out.println("No se pudo realizar la reserva. El horario está ocupado.");
        }

        System.out.println("\n--- Detalle Financiero del Pago ---");
        System.out.print("Ingrese número de jugadores para dividir la cuenta: ");
        int players = scanner.nextInt();

        System.out.print("¿Está pagado? (si / no): ");
        String respuestaPago = scanner.next().trim();
        boolean paid = respuestaPago.equalsIgnoreCase("si");

        Payment payment = new Payment(
                1,                  // paymentId
                1,                  // organizationId
                LocalDateTime.now(),// paymentDate
                players,            // numberOfPlayers
                paid,               // paid
                field,              // field
                schedule            // schedule
        );

        System.out.println(payment.generatePaymentSummary());
        List<Payment> paymentsList = new ArrayList<>();
        paymentsList.add(payment);

        Report report = new Report(
                paymentsList,
                BigDecimal.ZERO,
                LocalDate.now()
        );

        System.out.println("\n--- Resumen del Reporte Diario ---");
        System.out.println(report.generateDailySummary());
    }
}