package org.example.model;

import org.example.model.enums.Gender;
import org.example.model.enums.Status;

import java.time.LocalDate;

public abstract class Staff extends User {
    protected double salary;
    protected Status status = Status.ACTIVE;

    public Staff() {
        super();
    }

    public Staff(double salary) {
        this();
        this.salary = salary;
    }

    public Staff(String username, String password, double salary) {

        super(username, password);
        this.salary = salary;

    }

    public Staff(String firstName, String lastName, String CIN, LocalDate birthDate, String address, String phone, String email, Gender gender, String username, String password, double salary, Status status) {
        super(firstName, lastName, CIN, birthDate, address, phone, email, gender, username, password);
        this.salary = salary;
    }

    public Staff(Person person, String username, String password, double salary, Status status) {
        super(person, username, password);
        this.salary = salary;
    }

    public final double getSalary() {
        return salary;
    }

    public final void setSalary(double salary) {
        this.salary = salary;
    }

    public final Status getStatus() {
        return status;
    }

    public final void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Staff{"
                + super.toString()
                +
                "salary=" + salary +
                ", status=" + status +
                '}';
    }
}
