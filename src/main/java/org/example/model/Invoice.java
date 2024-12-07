package org.example.model;

import org.example.model.enums.PaymentType;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Invoice {
    private final Long id;
    private LocalDate date;
    private Double totalAmount;
    private Double payedAmount;
    private PaymentType type;

    public Invoice() {
        this.id = System.currentTimeMillis();
    }

    public Invoice(LocalDate date, Double totalAmount, Double payedAmount, PaymentType type) {
        this();
        /**
         * Why use this()?
         * To avoid code duplication.
         * Instead of writing the logic to generate a UUID in both constructors, the no-argument constructor centralizes it.
         * The second constructor can simply call it to reuse that logic.
         **/
        this.date = date;
        this.totalAmount = totalAmount;
        this.payedAmount = payedAmount;
        this.type = type;
    }

    public Double getRemainigAmount() {
        return totalAmount - payedAmount;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getPayedAmount() {
        return payedAmount;
    }

    public void setPayedAmount(Double payedAmount) {
        this.payedAmount = payedAmount;
    }

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(id, invoice.id); // yemery personnaly does not see the point fr that equal
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", totalAmount=" + totalAmount +
                ", payedAmount=" + payedAmount +
                ", type=" + type +
                '}';
    }
}
