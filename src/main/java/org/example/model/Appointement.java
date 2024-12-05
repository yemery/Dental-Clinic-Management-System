package org.example.model;

import org.example.model.enums.AppoitmentType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Appointement {
    private String id;
    private LocalTime time;
    private LocalDate date;
    private AppoitmentType type;
    private List<Consultation> consultations = new ArrayList<>();

    public Appointement() {
        this.id = UUID.randomUUID().toString();
    }

    public Appointement(LocalTime time, LocalDate date, AppoitmentType type) {
        this();
        this.time = time;
        this.date = date;
        this.type = type;
    }

    public String getId() {
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

    @Override
    public String toString() {
        return "Appointement{" +
                "id='" + id + '\'' +
                ", time=" + time +
                ", date=" + date +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointement that = (Appointement) o;
        return Objects.equals(id, that.id);
    }
}
