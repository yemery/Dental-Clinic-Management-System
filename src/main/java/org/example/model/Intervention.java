package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Intervention {
    private final Long id;
    private double price;
    private List<Act> acts = new ArrayList<Act>();

    public Intervention()  {
        this.id=Math.abs(UUID.randomUUID().getLeastSignificantBits());
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

    public List<Act> getActs() {
        return acts;
    }

    public void setActs(Act act) {
            this.acts.add(act);
    }

    @Override
    public String toString() {
        return "Intervention{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", acts=" + acts +
                '}';
    }
}
