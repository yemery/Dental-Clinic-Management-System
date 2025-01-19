package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Intervention {
    private final Long id;
    private double price;
    private List<Long> acts = new ArrayList<>();

    public Intervention() {
        this.id = Math.abs(UUID.randomUUID().getLeastSignificantBits());
        this.acts = new ArrayList<>();
    }

    public Intervention(double price) {
        this();
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Long> getActs() {
        return acts;
    }

    public void setActs(List<Long> acts) {
        this.acts = acts;
    }

    @Override
    public String toString() {
        return "Intervention{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", acts=" + acts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Intervention that = (Intervention) o;
        return Objects.equals(id, that.id);
    }
}
