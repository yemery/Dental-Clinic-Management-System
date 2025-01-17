package org.example.model;

import org.example.model.enums.Gender;
import org.example.model.enums.Status;
import org.example.model.enums.UserType;

import java.time.LocalDate;

public class Doctor extends Staff {
    private String speciality;

    public Doctor() {
        super();
    }

    public Doctor(String speciality) {
        this();
        this.speciality = speciality;
    }

    public Doctor(String username, String password, double salary, String speciality, UserType userType) {
        super(username, password, salary, userType);
        this.speciality = speciality;
    }

    public Doctor(String firstName, String lastName, String CIN, LocalDate birthDate, String address, String phone, String email, Gender gender, String username, String password, double salary, String speciality, UserType userType) {
        super(firstName, lastName, CIN, birthDate, address, phone, email, gender, username, password, salary, userType);
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    //    @Override
//    public String getType() {
//        return "Doctor";
//    }
    @Override
    public String toString() {
        return "Doctor{" +
                super.toString() +
                ", speciality='" + speciality + '\'' +
                '}';
    }


}
