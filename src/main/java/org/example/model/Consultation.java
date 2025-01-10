package org.example.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.example.model.enums.ConsultationType;
import org.example.utils.LocalDateDeserializer;

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
    private List<Long> interventions = new ArrayList<>();

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

    public List<Long> getInterventions() {
        return interventions;
    }

    public void setInterventions(List<Long> interventions) {
        this.interventions = interventions;
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

    public boolean removeIntervention(Intervention intervention) {
        return interventions.remove(intervention);
    }

}
