package edu.unl.cc.proyect.logica.domain;

import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalTime;

public class Payment {
    private int paymentId;
    private int organizationId;
    private LocalDateTime paymentDate;
    private int numberOfPlayers;
    private boolean paid;

    private Field field;
    private Schedule schedule;

    //Constructores


    public Payment(int paymentId, int organizationId, LocalDateTime paymentDate, int numberOfPlayers, boolean paid, Field field, Schedule schedule) {
        this.paymentId = paymentId;
        this.organizationId = organizationId;
        this.paymentDate = paymentDate;
        this.setNumberOfPlayers(numberOfPlayers);
        this.paid = paid;
        this.field = field;
        this.schedule = schedule;
    }

    public Payment(int paymentId, int organizationId, int numberOfPlayers, Field field, Schedule schedule) {
        this.paymentId = paymentId;
        this.organizationId = organizationId;
        this.setNumberOfPlayers(numberOfPlayers);
        this.field = field;
        this.schedule = schedule;
    }

    //Métodos
    public BigDecimal amountToBePaid() {
        long hours = Duration.between(schedule.getStartTime(), schedule.getEndTime()).toHours();
        return BigDecimal.valueOf(field.getPricePerHour()).multiply(BigDecimal.valueOf(hours));
    }

    public BigDecimal calculateSplitAmount(){
        return amountToBePaid().divide(BigDecimal.valueOf(numberOfPlayers), 2, RoundingMode.HALF_UP);
    }

    public boolean isPaid(){
        return paid;
    }

    @Override
    public String toString() {
        return "Pago ID: " + paymentId +
                "\nOrganización ID: " + organizationId +
                "\nMonto total: $" + amountToBePaid() +
                "\nPago individual: " + calculateSplitAmount() +
                "\nFecha: " + paymentDate +
                "\nJugadores: " + numberOfPlayers +
                "\nEstado del pago: " + paid;
    }

    public String generatePaymentSummary(){
        return toString();
    }

    //Getters and Setters
    public int getPaymentId() {
        return paymentId;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public Field getField() {
        return field;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        if(numberOfPlayers >= 8 && numberOfPlayers <=20){
            this.numberOfPlayers = numberOfPlayers;
        } else {
            throw new IllegalArgumentException("El número de jugadores debe ser de mínimo 8 y máximo 20");
        }
    }

    public boolean getPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }


}
