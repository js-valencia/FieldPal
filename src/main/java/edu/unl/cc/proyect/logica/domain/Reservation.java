package edu.unl.cc.proyect.logica.domain;
import java.util.Date;
import java.util.List;

public class Reservation {
    private int reservationId;
    private int organizationId;
    private Date date;
    private String reservationStatus;
    private boolean expiration;

    public Reservation(int organizationId, Date date, String reservationStatus, boolean expiration){
        this.organizationId = organizationId;
        this.date = date;
        this.reservationStatus = reservationStatus;
        this.expiration = expiration;
    }

    public boolean validateAvailability(int IDUser){
        return true;
    }
    public boolean updateStatus (String newStatus){
        return true;
    }
    public boolean makeReservation(List<Schedule> schedules){
        return true;
    }
    public boolean cancelReservation(){
        return true;
    }
}
