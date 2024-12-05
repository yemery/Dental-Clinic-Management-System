package org.example.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Prescription
{
    // ordonnance
    private String id;
    private LocalDate date = LocalDate.now();
    private List<PresciptionMedicine> prescriptionsMedicine;

    public Prescription() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<PresciptionMedicine> getPrescriptionsMedicine() {
        return prescriptionsMedicine;
    }

    public void setPrescriptionsMedicine(PresciptionMedicine pm) {
        this.prescriptionsMedicine.add(pm);
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", prescriptionsMedicine=" + prescriptionsMedicine +
                '}';
    }
}
