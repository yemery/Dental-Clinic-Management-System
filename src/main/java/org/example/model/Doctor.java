package org.example.model;

import org.example.model.enums.Gender;
import org.example.model.enums.Status;
import org.example.model.enums.UserType;

import java.time.LocalDate;

public class Doctor extends Staff {

    public Doctor() {
        super();
    }

    public Doctor(String username, String password, double salary, UserType userType) {
        super(username, password, salary, userType);
    }

    public Doctor(String firstName, String lastName, String CIN, LocalDate birthDate, String address, String phone, String email, Gender gender, String username, String password, double salary, UserType userType) {
        super(firstName, lastName, CIN, birthDate, address, phone, email, gender, username, password, salary, userType);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                super.toString() +
                '}';
    }
}
