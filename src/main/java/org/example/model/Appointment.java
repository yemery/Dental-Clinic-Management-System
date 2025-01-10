package org.example.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.example.model.enums.AppointementStatus;
import org.example.model.enums.AppoitmentType;
import org.example.utils.LocalDateDeserializer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Appointment {
    private final Long id;

    private LocalTime time;

    private LocalDate date;
    private AppoitmentType type;
    private AppointementStatus status;
    private Consultation consultation;

    public Appointment() {
        this.id = Math.abs(UUID.randomUUID().getLeastSignificantBits());
    }

    public Appointment(LocalTime time, LocalDate date, AppoitmentType type, AppointementStatus status) {
        this();
        this.time = time;
        this.date = date;
        this.type = type;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public AppoitmentType getType() {
        return type;
    }

    public void setType(AppoitmentType type) {
        this.type = type;
    }

    public AppointementStatus getStatus() {
        return status;
    }

    public void setStatus(AppointementStatus status) {
        this.status = status;
    }

    public Consultation getConsultation() {
        return this.consultation;
    }

    public void setConsultations(Consultation consultation) {
        this.consultation= consultation;
    }

    @Override
    public String toString() {
        return "Appointement{" +
                "id='" + id + '\'' +
                ", time=" + time +
                ", date=" + date +
                ", type=" + type +
                "consultation=" + consultation +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(id, that.id);
    }
}
