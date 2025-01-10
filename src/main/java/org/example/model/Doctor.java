package org.example.model;

import org.example.model.enums.Gender;
import org.example.model.enums.Status;

import java.time.LocalDate;

public class Doctor extends Staff {
    private String speciality;

    public Doctor() {
        super();
    }

    public Doctor(double salary, String speciality) {
        super(salary);
        this.speciality = speciality;
    }

    public Doctor(String username, String password, double salary, String speciality) {
        super(username, password, salary);
        this.speciality = speciality;
    }

    public Doctor(String firstName, String lastName, String CIN, LocalDate birthDate, String address, String phone, String email, Gender gender, String username, String password, double salary, Status status, String speciality) {
        super(firstName, lastName, CIN, birthDate, address, phone, email, gender, username, password, salary, status);
        this.speciality = speciality;
    }



    @Override
    public String getType() {
        return "Doctor";
    }

    @Override
    public String toString() {
        return "Doctor{" + super.toString() +
                "speciality='" + speciality + '\'' +
                '}';
    }
}
