package edu.unl.cc.proyect.logica.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private int reservationId;
    private int organizationId;
    private User user;
    private Field field;
    private LocalDateTime date;
    private float totalAmount;
    private String reservationStatus;
    private boolean expiration;
    private List<Schedule> reservedSlots;

    public Reservation(int reservationId, int organizationId, User user, Field field, LocalDateTime date, float totalAmount, String reservationStatus, boolean expiration) {
        this.reservationId = reservationId;
        this.organizationId = organizationId;
        this.user = user;
        this.field = field;
        this.date = date;
        this.totalAmount = totalAmount;
        this.reservationStatus = reservationStatus;
        this.expiration = expiration;
        this.reservedSlots = new ArrayList<>();
    }

    public boolean validateAvailability(int idUser) {
        if (this.reservedSlots == null || this.reservedSlots.isEmpty()) {
            return true;
        }
        for (Schedule slot : this.reservedSlots) {
            if (slot.isReserved()) {
                return false;
            }
        }
        return true;
    }

    public float calculateTotal(int hours) {
        if (field != null) {
            this.totalAmount = field.getPricePerHour() * hours;
        }
        return this.totalAmount;
    }

    public boolean updateStatus(String newStatus) {
        this.reservationStatus = newStatus;
        return true;
    }

    public boolean makeReservation(List<Schedule> schedules) {
        this.reservedSlots = schedules;
        if (validateAvailability(this.user != null ? this.user.getUserID() : 0)) {
            for (Schedule slot : this.reservedSlots) {
                slot.setReserved(true);
            }
            this.reservationStatus = "CONFIRMED";
            calculateTotal(schedules.size());
            return true;
        }
        return false;
    }

    public boolean cancelReservation() {
        if (this.reservedSlots != null) {
            for (Schedule slot : this.reservedSlots) {
                slot.setReserved(false);
            }
        }
        this.reservationStatus = "CANCELLED";
        return true;
    }

    public int getReservationId() {
        return reservationId;
    }
    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }
    public int getOrganizationId() {
        return organizationId;
    }
    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
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
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public float getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getReservationStatus() {
        return reservationStatus;
    }
    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
    public boolean isExpiration() {
        return expiration;
    }
    public void setExpiration(boolean expiration) {
        this.expiration = expiration;
    }
    public List<Schedule> getReservedSlots() {
        return reservedSlots;
    }
    public void setReservedSlots(List<Schedule> reservedSlots) {
        this.reservedSlots = reservedSlots;
    }
}