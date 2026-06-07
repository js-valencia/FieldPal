package edu.unl.cc.proyect.logica.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Payment implements Serializable{
    private LocalDateTime paymentDate;
    private PaymentStatus status;

    private Reservation reservation;

    //Constructor
    public Payment() {
        this.paymentDate = LocalDateTime.now();
        this.status = PaymentStatus.PENDING;
    }

    public Payment(Reservation reservation) {
        this();
        this.reservation = Objects.requireNonNull(reservation, "El pago debe estar asociado a una reserva válida.");
    }

    //Métodos
    public BigDecimal calculateAmountToBePaid() {
        Duration duration = Duration.between(reservation.getSchedule().getStartTime(), reservation.getSchedule().getEndTime());
        long hours = duration.toHours();

        BigDecimal hoursMultiplier = BigDecimal.valueOf(hours);
        BigDecimal price = reservation.getField().getPricePerHour();

        BigDecimal total = price.multiply(hoursMultiplier);
        return total.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateSplitAmount(){
        int players = reservation.getNumberOfPlayers();
        if (players <= 0) {
            throw new IllegalArgumentException("La reserva debe tener al menos 1 jugador para dividir el pago.");
        }

        return calculateAmountToBePaid().divide(BigDecimal.valueOf(players), 2, RoundingMode.HALF_UP);
    }

    public String generatePaymentSummary(){
        return this.toString();
    }

    public BigDecimal calculateTotalIncome(List<Payment> organizationPayments) {
        BigDecimal total = BigDecimal.ZERO;
        if (organizationPayments == null || organizationPayments.isEmpty()) {
            return total;
        }

        for (Payment payment : organizationPayments) {
            if (payment.getStatus() == PaymentStatus.PAID) {
                total = total.add(payment.calculateAmountToBePaid());
            }
        }
        return total;
    }

    public String generateDailyReport(List<Payment> dailyPayments) {
        if (dailyPayments == null || dailyPayments.isEmpty()) {
            return "Fecha: " + LocalDateTime.now() +
                    "\nPagos registrados: 0" +
                    "\nIngreso total: $0.00";
        }

        return "Reporte Diario - Fecha: " + LocalDateTime.now() +
                "\nPagos registrados: " + dailyPayments.size() +
                "\nIngreso total: $" + calculateTotalIncome(dailyPayments);
    }

    //Getters and Setters
    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

   public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = Objects.requireNonNull(status, "El estado de pago no puede ser nulo");
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        if (reservation != null) {
            this.reservation = reservation;
        }
    }

    //toString
    @Override
    public String toString() {
        return "Resumen de Pago:" +
                "\nFecha: " + paymentDate +
                "\nMonto total: $" + calculateAmountToBePaid() +
                "\nNúmero de jugadores: " + reservation.getNumberOfPlayers() +
                "\nPago individual: $" + calculateSplitAmount() +
                "\nEstado del pago: " + status;
    }
}