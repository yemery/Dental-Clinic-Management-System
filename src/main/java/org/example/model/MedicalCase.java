package org.example.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class MedicalCase {
    private String id;
    private Patient patient; // remove medical case attr within patient when you re conviced
    private LocalDate creationDate = LocalDate.now();
    private List<Appointement> appointments;
    private List<MedicalHistory> medicalHistories;
//    private Doctor doctor; 1 doctor

    public MedicalCase() {
        this.id = UUID.randomUUID().toString();

    // this.medicalHistories = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public List<Appointement> getAppointments() {
        return appointments;
    }

    public void setAppointments(Appointement appointment) {

        this.appointments.add(appointment);
    }

//    to get consultations map over appointment we should use this method in the medialCase service
//    public ArrayList<Consultation> getConsultations() {
//        
//    }

    // no need to make an invoice array list attribute we can do a getter in the medicalCaseService to get appointments then consultations then invoices within them

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalCase that = (MedicalCase) o;
        return Objects.equals(id, that.id);
    }

    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public List<MedicalHistory> getMedicalHistories() {
        return medicalHistories;
    }

    public void setMedicalHistories(MedicalHistory md) {
        medicalHistories.add(md);
    }
}
