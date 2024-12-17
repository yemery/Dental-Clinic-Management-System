package org.example.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Prescription
{

    private final Long id;
    private final LocalDate date = LocalDate.now();
    private List<PrescriptionMedicine> prescriptionsMedicine;

    public Prescription() {
        this.id=Math.abs(UUID.randomUUID().getLeastSignificantBits());
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<PrescriptionMedicine> getPrescriptionsMedicine() {
        return prescriptionsMedicine;
    }

    public void setPrescriptionsMedicine(PrescriptionMedicine pm) {
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
