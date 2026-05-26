package edu.unl.cc.proyect.logica.domain;
import java.time.LocalDateTime;

public class Schedule {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isReserved;

    public void lockSlot(){

    }

    public void releaseSlot(){

    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public Schedule(int slotId, LocalDateTime startTime, LocalDateTime endTime, boolean isReserved) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.isReserved = isReserved;
    }
}