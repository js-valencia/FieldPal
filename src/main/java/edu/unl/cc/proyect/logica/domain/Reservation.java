package edu.unl.cc.proyect.logica.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reservation {

    private LocalDateTime date;
    private int numberOfPlayers;
    private float totalAmount;
    private List<Schedule> reservedSlots;
    private ReservationStatus status;
    private User user;
    private Field field;

    public Reservation() {
        this.reservedSlots = new ArrayList<>();
    }

    public Reservation(User user, Field field, int numberOfPlayers, List<Schedule> reservedSlots) {
        this.user = user;
        this.field = field;
        this.numberOfPlayers = numberOfPlayers;
        this.reservedSlots = reservedSlots != null ? reservedSlots : new ArrayList<>();
        this.date = LocalDateTime.now();
        this.status = ReservationStatus.CONFIRMED;
        // Calcular total inicial asumiendo la cantidad de slots como horas
        calculateTotal(this.reservedSlots.size());
    }

    public float calculateTotal(int hours) {
        if (this.field != null && this.field.getPricePerHour() != null) {
            this.totalAmount = this.field.getPricePerHour().floatValue() * hours;
        } else {
            this.totalAmount = 0.0f;
        }
        return this.totalAmount;
    }

    public boolean validateAvailability() {
        if (this.field == null) {
            return false;
        }
        if ("MAINTENANCE".equalsIgnoreCase(this.field.getStatus())) {
            return false;
        }
        if (this.reservedSlots == null || this.reservedSlots.isEmpty()) {
            return false;
        }
        for (Schedule slot : this.reservedSlots) {
            if (slot.isReserved()) {
                return false;
            }
        }
        return true;
    }

    public void cancelReservation() {
        if (this.reservedSlots != null) {
            for (Schedule slot : this.reservedSlots) {
                slot.setReserved(false);
            }
        }
        this.status = ReservationStatus.CANCELLED;

        String clientName = (this.user != null) ? this.user.getFullName() : "Cliente Desconocido";
        System.out.println("[SISTEMA] Reserva cancelada con éxito para el usuario: " + clientName);
    }

    // --- GETTERS y SETTERS ---
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<Schedule> getReservedSlots() {
        return reservedSlots;
    }

    public void setReservedSlots(List<Schedule> reservedSlots) {
        this.reservedSlots = reservedSlots;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}