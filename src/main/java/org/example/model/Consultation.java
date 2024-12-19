package org.example.model;

import org.example.model.enums.ConsultationType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Consultation {
    private final Long id;
    private ConsultationType type;
    private String note;
    private LocalDate date;
    private Invoice invoice = null;
    private Prescription prescription = null;
    private Certificate certificate = null;
    private List<Intervention> interventions = new ArrayList<Intervention>();

    public Consultation() {
        this.id=Math.abs(UUID.randomUUID().getLeastSignificantBits());
    }

    public Consultation(ConsultationType type, String note, LocalDate date, Invoice invoice,Prescription prescription) {
        this();
        this.type = type;
        this.note = note;
        this.date = date;
        this.invoice=invoice;
        this.prescription=prescription;
    }

    public void setInterventions(Intervention intervention) {
        this.interventions.add(intervention);
    }

    public Long getId() {
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

    public Certificate getCertificate() {
        return certificate;
    }

    public List<Intervention> getInterventions() {
        return interventions;
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
                ", invoice=" + invoice +
                ", prescription=" + prescription +
                ", intervention=" + interventions +
                '}';
    }
}
