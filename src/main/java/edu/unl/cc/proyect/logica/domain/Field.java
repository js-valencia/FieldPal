package edu.unl.cc.proyect.logica.domain;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Field implements Serializable {
    private String name;
    private BigDecimal pricePerHour;
    private FieldType fieldType;

    private List<Schedule> schedules;

    public Field(){
        this.schedules = new ArrayList<>();
    }

    // CONSTRUCTOR
    public Field(String name, FieldType fieldType, BigDecimal pricePerHour) {
        this.name = name;
        this.pricePerHour = pricePerHour;
        this.schedules = new ArrayList<>();
        this.fieldType = fieldType;
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

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }
}