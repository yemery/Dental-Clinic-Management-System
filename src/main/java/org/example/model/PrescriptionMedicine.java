package org.example.model;

import java.util.Objects;
import java.util.UUID;

public class PrescriptionMedicine {
    private final Long id;
    private Integer minQuantity;
    private Integer maxQuantity;
    private String description;
    private Long medicine;

    public PrescriptionMedicine() {
        this.id=Math.abs(UUID.randomUUID().getLeastSignificantBits());
    }

    public PrescriptionMedicine(Integer minQuantity, Integer maxQuantity, String description, Long medicine) {
        this();
        this.minQuantity = minQuantity;
        this.maxQuantity = maxQuantity;
        this.description = description;
        this.medicine = medicine;
    }

    public Long getId() {return id;}

    public int getMin() {
        return minQuantity;
    }

    public void setMin(int min) { this.minQuantity = min; }

    public int getMax() {
        return maxQuantity;
    }

    public void setMax(int max) {
        this.maxQuantity = max;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getMedicine() {
        return medicine;
    }

    public void setMedicine(Long medicine) {
        this.medicine = medicine;
    }

    @Override
    public String toString() {
        return "PresciptionMedicine{" +
                "id='" + id + '\'' +
                ", minQuantity=" + minQuantity +
                ", maxQuantity=" + maxQuantity +
                ", description='" + description + '\'' +
//                ", medicine=" +  +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrescriptionMedicine that = (PrescriptionMedicine) o;
        return Objects.equals(id, that.id);
    }
}
