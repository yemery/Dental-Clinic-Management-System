package org.example.model;

import org.example.model.enums.CategoryMedicalHistory;
import org.example.model.enums.Risk;

import java.util.Objects;
import java.util.UUID;

public class MedicalHistory {
    private final Long id;
    private String label;
    private CategoryMedicalHistory category;
    private String description;
    private Risk risk;

    public MedicalHistory() {
        this.id= UUID.randomUUID().timestamp();
    }
    public MedicalHistory(String label, String description, CategoryMedicalHistory category, Risk risk) {
        this();
        this.label = label;
        this.description = description;
        this.category = category;
        this.risk = risk;
    }

    public Long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public CategoryMedicalHistory getCategory() {
        return category;
    }

    public void setCategory(CategoryMedicalHistory category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Risk getRisk() {
        return risk;
    }

    public void setRisk(Risk risk) {
        this.risk = risk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalHistory that = (MedicalHistory) o;
        return Objects.equals(id, that.id) && Objects.equals(label, that.label) && category == that.category && Objects.equals(description, that.description) && risk == that.risk;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, category, description, risk);
    }

    @Override
    public String toString() {
        return "MedicalHistory{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", risk=" + risk +
                '}';
    }
}
