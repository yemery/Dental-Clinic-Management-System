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

    public MedicalCase() {
        this.id = UUID.randomUUID().toString();
        this.appointments = new ArrayList<Appointement>();
    }

    public String getId() {
        return id;
    }

    public List<Appointement> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointement> appointments) {
        this.appointments = appointments;
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
}
