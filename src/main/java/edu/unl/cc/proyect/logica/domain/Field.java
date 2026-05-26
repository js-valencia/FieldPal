package edu.unl.cc.proyect.logica.domain;
import java.math.BigDecimal;
import java.util.List;

public class Field {
    private String name;
    private String type;
    private BigDecimal pricePerHour;
    private String status;
    private List<Schedule> schedules;

    public void removeField(){
    }

    // --- GETTERS and SETTERS ---
    public String getName() {
        return name;
    }

    public void setName(String fieldName) {
        this.name = fieldName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(BigDecimal pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // CONSTRUCTOR
    public Field(String name, String type, BigDecimal pricePerHour, String status) {
        this.name = name;
        this.type = type;
        this.pricePerHour = pricePerHour;
        this.status = status;
    }
}