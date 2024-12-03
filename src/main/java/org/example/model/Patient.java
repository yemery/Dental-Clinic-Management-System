package org.example.model;

import org.example.model.enums.Gender;
import org.example.model.enums.Mutuelle;

import java.time.LocalDate;

public class Patient extends Person {

    private String registration;
    private Mutuelle mutuelle;
    private String job;

    public Patient(String registration, Mutuelle mutuelle, String job) {
        this.registration = registration;
        this.mutuelle = mutuelle;
        this.job = job;
    }

    public Patient(String firstName, String lastName, String CIN, LocalDate birthDate, String address, String phone, String email, Gender gender, String registration, Mutuelle mutuelle, String job) {
        super(firstName, lastName, CIN, birthDate, address, phone, email, gender);
        this.registration = registration;
        this.mutuelle = mutuelle;
        this.job = job;
    }

    public Patient(Person person, String registration, Mutuelle mutuelle, String job) {
        super(person);
        this.registration = registration;
        this.mutuelle = mutuelle;
        this.job = job;
    }

    @Override
    public String getType() {
        return "Patient";
    }
}
