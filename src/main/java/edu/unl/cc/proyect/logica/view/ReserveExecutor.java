package edu.unl.cc.proyect.logica.view;

import edu.unl.cc.proyect.logica.domain.Field;
import edu.unl.cc.proyect.logica.domain.Payment;
import edu.unl.cc.proyect.logica.domain.Report;
import edu.unl.cc.proyect.logica.domain.Schedule;

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

        System.out.print("Ingrese número de jugadores: ");
        int players = scanner.nextInt();

        System.out.print("¿Está pagado? (si / no): ");
        boolean paid = scanner.nextBoolean();

        Field field = new Field(1, 1, "Abogado", "Cancha Sintética", 20, "abierto", "13:00 - 22:00");

        Schedule schedule = new Schedule(
                LocalTime.of(18, 0),
                LocalTime.of(20, 0)
        );

        Payment payment = new Payment(
                1,
                1,
                LocalDateTime.now(),
                players,
                paid,
                field,
                schedule
        );

        System.out.println(payment.generatePaymentSummary());

        List<Payment> totalPayments = new ArrayList<>();
        totalPayments.add(payment);

        Report report = new Report(
                LocalDate.now(),
                totalPayments,
                BigDecimal.ZERO
        );

        System.out.println(report.generateDailySummary());
    }
}
