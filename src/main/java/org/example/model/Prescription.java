package org.example.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Prescription
{
    private final Long id;
    private final LocalDate date = LocalDate.now();
    private List<Long> prescriptionsMedicine = new ArrayList<>();

    public Prescription() {
        this.id=Math.abs(UUID.randomUUID().getLeastSignificantBits());
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<Long> getPrescriptionsMedicine() {
        return prescriptionsMedicine;
    }

    public void setPrescriptionsMedicine(List<Long> prescriptionsMedicine) {
        this.prescriptionsMedicine = prescriptionsMedicine;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", prescriptionsMedicine=" + prescriptionsMedicine +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prescription that = (Prescription) o;
        return Objects.equals(id, that.id);
    }
}