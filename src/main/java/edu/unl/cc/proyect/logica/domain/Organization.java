package edu.unl.cc.proyect.logica.domain;

import java.util.ArrayList;
import java.util.List;

public class Organization {
    private String name;
    private String address;
    private String bussinessHour;

    private List<Field> fields;

    public Organization(){
        this.fields = new ArrayList<>();
    }

    public Organization(String name, String address, String bussinessHour){
        this.name = name;
        this.address = address;
        this.bussinessHour = bussinessHour;
        this.fields = new ArrayList<>();
    }

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

    public String getBussinessHour() {
        return bussinessHour;
    }

    public void setBussinessHour(String bussinessHour) {
        this.bussinessHour = bussinessHour;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
