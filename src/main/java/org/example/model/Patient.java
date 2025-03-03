package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.example.model.enums.Gender;
import org.example.model.enums.Mutuelle;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Patient extends Person {

    private String registration;
    private Mutuelle mutuelle;
    private String job;

    public Patient() {
        super();
    }

    public Patient(String firstName, String lastName, String CIN, LocalDate birthDate, String address, String phone, String email, Gender gender, String registration, Mutuelle mutuelle, String job) {
        super(firstName, lastName, CIN, birthDate, address, phone, email, gender);
        this.registration = registration;
        this.mutuelle = mutuelle;
        this.job = job;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public Mutuelle getMutuelle() {
        return mutuelle;
    }

    public void setMutuelle(Mutuelle mutuelle) {
        this.mutuelle = mutuelle;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getFullName() {
        return super.getFirstName() + " " + super.getLastName();
    }

    @Override
    public String toString() {
        return "Patient{" + super.toString()
                + mutuelle +
                ", job='" + job + '\'' +
                '}';
    }
}
