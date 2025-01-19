package org.example.model;

import org.example.model.enums.Gender;
import org.example.model.enums.Status;
import org.example.model.enums.UserType;

import java.time.LocalDate;
import java.util.Objects;

public class Staff extends User {
    protected double salary;
    protected Status status = Status.ACTIVE;

    public Staff() {
        super();
    }

    public Staff(double salary) {
        this();
        this.salary = salary;
    }

    public Staff(String username, String password, double salary, UserType userType) {

        super(username, password, userType);
        this.salary = salary;

    }

    public Staff(String firstName, String lastName, String CIN, LocalDate birthDate, String address, String phone, String email, Gender gender, String username, String password, double salary, UserType userType) {
        super(firstName, lastName, CIN, birthDate, address, phone, email, gender, username, password, userType);
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
        return super.toString() +
                ", salary=" + salary +
                ", status=" + status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(salary, status);
    }
}
