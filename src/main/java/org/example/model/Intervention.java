package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Intervention {
    private final Long id;
    private double price;
    private List<Act> acts = new ArrayList<Act>();

    public Intervention()  {
        this.id=Math.abs(UUID.randomUUID().getLeastSignificantBits());
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

    public List<Act> getActs() {
        return acts;
    }



    public void setActs(List<Act> acts) {
        if (this.acts == null) {
            this.acts = new ArrayList<>();
        }
        this.acts.addAll(acts);
    }

//    @JsonIgnore
//    public void setActs(Act act) {
//        this.acts.add(act);
//    }





    public  boolean removeAct(Act act) {
        return this.acts.remove(act);
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
