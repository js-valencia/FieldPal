package edu.unl.cc.proyect.logica.domain;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Organization {
    private String name;
    private String address;
    private LocalTime oppeningHour;
    private LocalTime closingHour;

    //Relacion de composicion:
    private List<Field> fields;

    //Constructores
    public Organization(){
        this.fields = new ArrayList<>();
    }

    public Organization(String name, String address, LocalTime oppeningHour, LocalTime closingHour){
        this.name = name;
        this.address = address;
        this.oppeningHour = oppeningHour;
        this.closingHour = closingHour;
        this.fields = new ArrayList<>();
    }

    //metodos
    public void addField(Field field){
        this.fields.add(field);
    }

    public void deleteField(Field field){
        if (this.fields.size() <= 1) {
            throw new IllegalStateException("La Organizacion no se puede quedar sin canchas");
        }
        this.fields.remove(field);
    }

    public Duration calculateBussinessHours(){
        return Duration.between(oppeningHour, closingHour);
    }

    // getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalTime getOppeningHour() {
        return oppeningHour;
    }

    public void setOppeningHour(LocalTime oppeningHour) {
        this.oppeningHour = oppeningHour;
    }

    public LocalTime getClosingHour() {
        return closingHour;
    }

    public void setClosingHour(LocalTime closingHour) {
        this.closingHour = closingHour;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}