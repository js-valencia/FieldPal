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
    private String reservationStatus;
    private boolean expiration;
    private List<Schedule> schedules;

    // Constructor completo
    public Reservation(int reservationId, int organizationId, User user, Field field, LocalDateTime date, String reservationStatus, boolean expiration) {
        this.reservationId = reservationId;
        this.organizationId = organizationId;
        this.user = user;
        this.field = field;
        this.date = date;
        this.reservationStatus = reservationStatus;
        this.expiration = expiration;
        this.schedules = new ArrayList<>();
    }

    public boolean validateAvailability(List<Schedule> requestedSchedules) {
        for (Schedule slot : requestedSchedules) {
            if (slot.isReserved()) {
                return false;
            }
        }
        return true;
    }

    public boolean updateStatus(String newStatus) {
        this.reservationStatus = newStatus;
        return true;
    }

    public boolean makeReservation(List<Schedule> schedulesToBook) {
        if (validateAvailability(schedulesToBook)) {
            this.schedules = schedulesToBook;
            for (Schedule slot : this.schedules) {
                slot.setReserved(true);
            }
            this.reservationStatus = "CONFIRMED";
            return true;
        }
        return false; // Horario ocupado
    }

    public boolean cancelReservation() {
        if (this.schedules != null) {
            for (Schedule slot : this.schedules) {
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
    public List<Schedule> getSchedules() {
        return schedules;
    }
    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}

