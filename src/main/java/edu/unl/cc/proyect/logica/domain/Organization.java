package edu.unl.cc.proyect.logica.domain;

import java.util.ArrayList;
import java.util.List;

public class Organization {
    private int idOrganization;
    private String name;
    private String address;
    private String phoneNumber;

    //Relacion de composición con Field
    private List<Field> fields;

    //Constructores

    //inicializa lista vacia de fields
    public Organization(){
        this.fields = new ArrayList<>();
    }

    public Organization(int idOrganization, String nameOrg, String address, String phoneNumberOrg){
        this.idOrganization = idOrganization;
        this.name = nameOrg;
        this.address = address;
        this.phoneNumber = phoneNumberOrg;
        this.fields = new ArrayList<>();
    }

    //metodo para añadir canchas individualmente
    public void addField(Field field){
        this.fields.add(field);
    }

    //getters and setters


    public int getIdOrganization() {
        return idOrganization;
    }

    public void setIdOrganization(int idOrganization) {
        this.idOrganization = idOrganization;
    }

    public String getNameOrg() {
        return name;
    }

    public void setNameOrg(String nameOrg) {
        this.name = nameOrg;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumberOrg() {
        return phoneNumber;
    }

    public void setPhoneNumberOrg(String phoneNumberOrg) {
        this.phoneNumber = phoneNumberOrg;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public void updateProfile(String name, String address, String phoneNumber){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
