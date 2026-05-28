package edu.unl.cc.proyect.logica.domain;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Field implements Serializable {
    private String name;
    private String type;
    private BigDecimal pricePerHour;

    private List<Schedule> schedules;

    public Field(){
        this.schedules = new ArrayList<>();
    }

    // CONSTRUCTOR
    public Field(String name, String type, BigDecimal pricePerHour, String status) {
        this.name = name;
        this.type = type;
        this.pricePerHour = pricePerHour;
        this.schedules = new ArrayList<>();
    }

    //METODO
    public void removeField(){
    }

    // GETTERS and SETTERS
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

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}