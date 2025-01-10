package org.example.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.example.utils.LocalDateDeserializer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Prescription
{

    private final Long id;

    private final LocalDate date = LocalDate.now();
    private List<PrescriptionMedicine> prescriptionsMedicine = new ArrayList<>();

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
        prescriptionsMedicine.add(pm);
    }
    public boolean removePrescriptionMedicine(PrescriptionMedicine pm) {
        return prescriptionsMedicine.remove(pm);
    }
    @Override
    public String toString() {
        return "Prescription{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", prescriptionsMedicine=" + prescriptionsMedicine +
                '}';
    }}