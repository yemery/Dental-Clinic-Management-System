package org.example.model;

import java.util.UUID;

public class Medicine {
    private String id;
    private Double price;
    private String name;
    private String description;

    public Medicine() {
        this.id = UUID.randomUUID().toString();
    }

    public Medicine(Double price, String name, String description) {
        this();
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
