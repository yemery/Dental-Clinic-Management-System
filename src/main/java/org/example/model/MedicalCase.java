package org.example.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class MedicalCase {
    private Long id;
    private Long patient;
    private LocalDate creationDate = LocalDate.now();
    private List<Long> appointments = new ArrayList<>();
    private List<Long> medicalHistories = new ArrayList<>();

    public MedicalCase() {
        this.id = Math.abs(UUID.randomUUID().getLeastSignificantBits());
    }

    public MedicalCase(Long patient, List<Long> appointments, List<Long> medicalHistories) {
        this();
        this.patient = patient;
        this.appointments = appointments;
        this.medicalHistories = medicalHistories;
    }

    public MedicalCase(Long patient) {
        this();
        this.patient = patient;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalCase that = (MedicalCase) o;
        return Objects.equals(id, that.id);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatient() {
        return patient;
    }

    public void setPatient(Long patient) {
        this.patient = patient;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public List<Long> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Long> appointments) {
        this.appointments = appointments;
    }

    public List<Long> getMedicalHistories() {
        return medicalHistories;
    }

    public void setMedicalHistories(List<Long> medicalHistories) {
        this.medicalHistories = medicalHistories;
    }

    @Override
    public String toString() {
        return "MedicalCase{" +
                "id=" + id +
                ", patient=" + patient +
                ", creationDate=" + creationDate +
                ", appointments=" + appointments +
                ", medicalHistories=" + medicalHistories +
                '}';
    }

    public boolean removeAppointment(Appointment appointment) {
        return this.appointments.remove(appointment);
    }

    public boolean removeMedicalHistory(MedicalHistory md) {
        return this.medicalHistories.remove(md);
    }
}
