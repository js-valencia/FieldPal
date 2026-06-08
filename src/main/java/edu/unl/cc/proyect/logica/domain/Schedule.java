package edu.unl.cc.proyect.logica.domain;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Schedule implements Serializable {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isReserved;


    public Schedule(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.isReserved = false;
    }


    public void lockSlot(){
    }

    public void releaseSlot(){
    }


    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public boolean isReserved() { return isReserved; }
    public void setReserved(boolean reserved) { isReserved = reserved; }


    @Override
    public String toString() {
        String estado = isReserved ? "[OCUPADO]" : "[DISPONIBLE]";
        return startTime + " - " + endTime + " " + estado;
    }
}