package org.example.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.enums.ActCategory;

import java.util.Objects;
import java.util.UUID;

public class Act {
    private final Long id;
    private String name;
    private double basePrice;
    private ActCategory category;


    public Act() {
        this.id=System.currentTimeMillis();
    }
    public Act( String name, double basePrice, ActCategory category) {
        this();
        this.name = name;
        this.basePrice = basePrice;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public ActCategory getCategory() {
        return category;
    }

    public void setCategory(ActCategory category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Act act = (Act) o;
        return Objects.equals(id, act.id) || Objects.equals(name, act.name); // we donno where and how to use it (might change the equals criteria)
    }

//    @Override
//    public String toString() {
//        return "Act{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", basePrice=" + basePrice +
//                ", category=" + category +
//                '}';
//    }
@Override
public String toString() {
    ObjectMapper mapper = new ObjectMapper();
    try {
        return mapper.writeValueAsString(this);
    } catch (JsonProcessingException e) {
        e.printStackTrace();
        return super.toString(); // fallback
    }
}
}
