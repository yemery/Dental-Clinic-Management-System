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

    private List<Long> interventions = new ArrayList<>();
    private Long certificate;
    private Long invoice;
    private Long prescription;

    public Consultation() {
        this.id=Math.abs(UUID.randomUUID().getLeastSignificantBits());
    }

    public Consultation(ConsultationType type, String note) {
        this();
        this.type = type;
        this.note = note;
        this.date = LocalDate.now();
//        this.invoice=invoice;
//        this.prescription=prescription;
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

    public Long getInvoice() {
        return invoice;
    }

    public void setInvoice(Long invoice) {
        this.invoice = invoice;
    }

    public Long getPrescription() {
        return prescription;
    }

    public void setPrescription(Long prescription) {
        this.prescription = prescription;
    }

    public Long getCertificate() {
        return certificate;
    }

    public void setCertificate(Long certificate) {
        this.certificate = certificate;
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
