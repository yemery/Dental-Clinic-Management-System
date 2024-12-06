package org.example.model;

import org.example.model.enums.ConsultationType;

import java.time.LocalDate;
import java.util.UUID;

public class Consultation {
    private String id;
    private ConsultationType type;
    private String note;
    private LocalDate date;
    private Intervention intervention;
    private Invoice invoice = null;
    private Prescription prescription = null;
    private Certificate certificate = null;

    public Consultation() {
        this.id = UUID.randomUUID().toString();
    }

    public Consultation(ConsultationType type, String note, LocalDate date, Intervention intervention) {
        this();
        this.type = type;
        this.note = note;
        this.date = date;
        this.intervention = intervention;
    }

    public String getId() {
        return id;
    }

    public ConsultationType getType() {
        return type;
    }

    public void setType(ConsultationType type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Intervention getIntervention() {
        return intervention;
    }

    public void setIntervention(Intervention intervention) {
        this.intervention = intervention;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    @Override
    public String toString() {
        return "Consultation{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", note='" + note + '\'' +
                ", date=" + date +
                ", intervention=" + intervention +
                ", invoice=" + invoice +
                ", prescription=" + prescription +
                '}';
    }
}
