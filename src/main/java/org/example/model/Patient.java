package org.example.model;

import org.example.model.enums.Gender;
import org.example.model.enums.Mutuelle;

import java.time.LocalDate;

public class Patient extends Person {

    private String registration;
    private Mutuelle mutuelle;
    private String job;
    private MedicalCase medicalCase;

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

    public MedicalCase getMedicalCase() {
        return medicalCase;
    }

    public void setMedicalCase(MedicalCase medicalCase) {
        this.medicalCase = medicalCase;
    }

    @Override
    public String getType() {
        return "Patient";
    }

    @Override
    public String toString() {
        return "Patient{" + super.toString()
                + mutuelle +
                ", job='" + job + '\'' +
                ", medicalCase=" + medicalCase +
                '}';
    }
}
