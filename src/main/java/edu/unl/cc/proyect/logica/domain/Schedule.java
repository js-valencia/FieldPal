package edu.unl.cc;
import java.time.LocalTime;

public class Schedule {
    private int slotId;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isReserved;

    public void lockSlot(){

    }

    public void releaseSlot(){

    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public Schedule(int slotId, LocalTime startTime, LocalTime endTime, boolean isReserved) {
        this.slotId = slotId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isReserved = isReserved;
    }
}