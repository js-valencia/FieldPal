package com.fieldPal.controller;

import com.fieldpal.model.Court;
import com.fieldpal.model.Organization;
import com.fieldpal.model.TimeSlot;
import com.fieldpal.model.enums.CourtType;
import com.fieldpal.model.enums.Zone;
import com.fieldpal.service.CourtService;
import com.fieldpal.service.OrganizationService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Managed Bean para la página de horarios/disponibilidad.
 * Datos quemados - editar después para conectar a BD real.
 */
@Named
@ViewScoped
public class HorariosBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private OrganizationService organizationService;

    @Inject
    private CourtService courtService;

    // Filtros
    private Zone selectedZone;
    private String selectedOrgId = "";
    private String selectedCourtId = "";
    private String selectedDate = "2026-07-14";
    private CourtType selectedType;

    // Horarios generados
    private List<TimeSlot> schedule = new ArrayList<>();

    public List<Organization> getFilteredOrgs() {
        if (selectedZone != null) {
            return organizationService.getByZone(selectedZone);
        }
        return organizationService.getAll();
    }

    public List<Court> getFilteredCourts() {
        List<Court> result = new ArrayList<>();
        for (Court c : courtService.getAll()) {
            if (selectedOrgId != null && !selectedOrgId.isEmpty()
                    && !c.getOrgId().equals(selectedOrgId)) continue;
            if (selectedType != null && c.getType() != selectedType) continue;
            result.add(c);
        }
        return result;
    }

    public List<Court> getAllCourts() {
        return courtService.getAll();
    }

    public void loadSchedule() {
        if (selectedCourtId != null && !selectedCourtId.isEmpty()) {
            schedule = courtService.getSchedule(selectedCourtId, selectedDate);
        } else {
            schedule = new ArrayList<>();
        }
    }

    public void onZoneChange() {
        selectedOrgId = "";
        selectedCourtId = "";
        schedule = new ArrayList<>();
    }

    public void onOrgChange() {
        selectedCourtId = "";
        schedule = new ArrayList<>();
    }

    public void onCourtChange() {
        loadSchedule();
    }

    public void onDateChange() {
        loadSchedule();
    }

    // Getters y Setters
    public Zone getSelectedZone() { return selectedZone; }
    public void setSelectedZone(Zone selectedZone) { this.selectedZone = selectedZone; }

    public String getSelectedOrgId() { return selectedOrgId; }
    public void setSelectedOrgId(String selectedOrgId) { this.selectedOrgId = selectedOrgId; }

    public String getSelectedCourtId() { return selectedCourtId; }
    public void setSelectedCourtId(String selectedCourtId) { this.selectedCourtId = selectedCourtId; }

    public String getSelectedDate() { return selectedDate; }
    public void setSelectedDate(String selectedDate) { this.selectedDate = selectedDate; }

    public CourtType getSelectedType() { return selectedType; }
    public void setSelectedType(CourtType selectedType) { this.selectedType = selectedType; }

    public List<TimeSlot> getSchedule() { return schedule; }
    public void setSchedule(List<TimeSlot> schedule) { this.schedule = schedule; }
}

