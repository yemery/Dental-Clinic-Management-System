package org.example.model;

import org.example.model.enums.AppointementStatus;
import org.example.model.enums.AppoitmentType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

public class Appointment {
    private final Long id;

    private LocalTime time;

    private LocalDate date;
    private AppoitmentType type;
    private AppointementStatus status;

    private Long consultation;

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

    public Long getConsultation() {
        return this.consultation;
    }

    public void setConsultations(Long consultation) {
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
