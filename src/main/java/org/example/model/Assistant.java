package org.example.model;

import org.example.model.enums.Gender;
import org.example.model.enums.Status;
import org.example.model.enums.UserType;

import java.time.LocalDate;

public class Assistant extends Staff {

    public Assistant() {
        super();
    }

    public Assistant(double salary, Double prime) {
        super(salary);
    }

    public Assistant(String username, String password, double salary, UserType userType) {
        super(username, password, salary, userType);
    }

    public Assistant(String firstName, String lastName, String CIN, LocalDate birthDate, String address, String phone, String email, Gender gender, String username, String password, double salary, Status status, Double prime, UserType userType) {
        super(firstName, lastName, CIN, birthDate, address, phone, email, gender, username, password, salary, userType);
    }

    @Override
    public String toString() {
        return "Assistant{" +
                super.toString() +
                '}';
    }
}
