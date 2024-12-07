package org.example.model;

import java.util.UUID;

public class PresciptionMedicine {
    private final Long id;
    private Integer minQuantity;
    private Integer maxQuantity;
    private String description;
    private Medicine medicine;

    public PresciptionMedicine() {
        this.id = UUID.randomUUID().timestamp();
    }

    public int getMin() {
        return minQuantity;
    }

    public void setMin(int min) {
        this.minQuantity = min;
    }

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

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    @Override
    public String toString() {
        return "PresciptionMedicine{" +
                "id='" + id + '\'' +
                ", minQuantity=" + minQuantity +
                ", maxQuantity=" + maxQuantity +
                ", description='" + description + '\'' +
                ", medicine=" + medicine +
                '}';
    }
}
