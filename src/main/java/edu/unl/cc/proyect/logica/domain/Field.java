package edu.unl.cc.proyect.logica.domain;
import java.time.LocalDateTime;

public class Field {
    private int fieldId;
    private int organizationId;
    private String fieldName;
    private String type;
    private float pricePerHour;
    private String status;
    private String openingHours;

    public void updatePrice(float newPrice){
        this.pricePerHour = newPrice;
    }

    public boolean searchAvailability(LocalDateTime scheduleToBeConfirmed){
        return true;
    }


    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(float pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }


    public Field(int fieldId, int organizationId, String fieldName, String type, float pricePerHour, String status, String openingHours) {
        this.fieldId = fieldId;
        this.organizationId = organizationId;
        this.fieldName = fieldName;
        this.type = type;
        this.pricePerHour = pricePerHour;
        this.status = status;
        this.openingHours = openingHours;
    }
}