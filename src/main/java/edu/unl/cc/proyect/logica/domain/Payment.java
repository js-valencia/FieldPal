package edu.unl.cc.proyect.logica.domain;

import java.io.Serializable;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.math.BigDecimal;

public class Payment implements Serializable{
    private LocalDateTime paymentDate;
    private boolean isPaid;

    private Field field;
    private Schedule schedule;
    private Reservation reservation;

    //Constructor
    public Payment(LocalDateTime paymentDate, boolean isPaid, Field field, Schedule schedule, Reservation reservation) {
        this.paymentDate = paymentDate;
        this.isPaid = isPaid;
        this.field = field;
        this.schedule = schedule;
        this.reservation = reservation;
    }

    //Métodos
    public BigDecimal calculateAmountToBePaid() {
        long hours = Duration.between(schedule.getStartTime(), schedule.getEndTime()).toHours();
        return field.getPricePerHour().multiply(BigDecimal.valueOf(hours));
    }

    public BigDecimal calculateSplitAmount(){
        return calculateAmountToBePaid().divide(BigDecimal.valueOf(reservation.getNumberOfPlayers()), 2, RoundingMode.HALF_UP);
    }

    public String generatePaymentSummary(){
        return toString();
    }

    @Override
    public String toString() {
        return "Monto total: $" + calculateAmountToBePaid() +
                "\nPago individual: " + calculateSplitAmount() +
                "\nFecha: " + paymentDate +
                "\nJugadores: " + reservation.getNumberOfPlayers() +
                "\nEstado del pago: " + isPaid;
    }


    //Getters and Setters
    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public Field getField() {
        return field;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }
}
